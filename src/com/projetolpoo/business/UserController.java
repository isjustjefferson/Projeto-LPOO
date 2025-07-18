package com.projetolpoo.business;

import com.projetolpoo.database.Repository;
import com.projetolpoo.database.UserRepository;
import com.projetolpoo.entities.User;
import com.projetolpoo.exception.BusinessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class UserController {
    
    private static UserController userControllerInstance;
    
    private UserController(){
        
    }
    
    public static UserController getInstanceUserController(){
        if (userControllerInstance == null){
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }
    
    private static User userInstance;
    
    public void registraUsuario(String nome, String email, String senha, String confirmacaoSenha){
        
        if (nome.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (nome.length()<4){
            throw new BusinessException("O nome deve ter 4 caracteres ou mais.");
        }
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.length()<8){
            throw new BusinessException("A senha deve ter 8 caracteres ou mais.");
        }
        
        if (!senha.equals(confirmacaoSenha)){
            throw new BusinessException("A senha precisa ser igual a confirmação.");
        }
        
        User user = new User(nome, email, senha);
        UserRepository repository = new UserRepository();
        boolean existe = repository.confirm(email);
        
        if (existe){
            throw new BusinessException ("Já existe um usuário com esse email.");
        }
        
        repository.insert(user);
    }
    
    public void confirmaUsuario(String email, String senha){
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        instanciaUserController(email);
        UserRepository repository = new UserRepository();
        boolean exists = repository.login(userInstance);
        
        if (!exists){
            throw new BusinessException ("Usuário não encontrado.");
        }
    }
    
    public void trocaSenhaController(String email, String novaSenha, String confirmaNovaSenha){
        
        if (novaSenha.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (confirmaNovaSenha.isBlank()){
            throw new BusinessException("Os campos não estar vazios.");
        }
        
        if (!novaSenha.equals(confirmaNovaSenha)){
            throw new BusinessException("A senha precisa ser igual a confirmação.");
        }
        
        if (novaSenha.length()<8){
            throw new BusinessException("A senha precisa ter 8 dígitos ou mais.");
        }
        
        if (confirmaNovaSenha.length()<8){
            throw new BusinessException("A senha precisa ter 8 dígitos ou mais.");
        }
        
        UserRepository userRepository = new UserRepository();
        userRepository.trocaSenhaRepository(email, novaSenha);
    }
    
    public byte[] imageIconToBytes(ImageIcon icon, String format) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(
            icon.getIconWidth(),
            icon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        bufferedImage.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, baos);
        return baos.toByteArray();
    }
    
    public void registraImagem(ImageIcon imagem){
        if (userInstance == null) {
            throw new BusinessException("Nenhum usuário logado. Não é possível salvar a imagem.");
        }
        try {
            byte[] imagemBytes=imageIconToBytes(imagem, "jpg");
            
            UserRepository userRepository = new UserRepository();
            userRepository.insereImagem(imagemBytes, userInstance.getEmail()); 
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BusinessException("Não foi possível registrar a imagem.");
        }
    }
    
    public ImageIcon selecionaImagemController(){
        if (userInstance == null) {
            return null;
        }
        try{
            UserRepository userRepository = new UserRepository();
            byte[] imagemBytes = userRepository.selecionaImagemRepository(userInstance);
            if (imagemBytes == null || imagemBytes.length == 0) {
                return null;
            }
            ImageIcon imagem = new ImageIcon(imagemBytes); 
            return imagem;
        }catch (Exception e){
            throw new BusinessException("Não foi possível selecionar a imagem.");
        }
    }

    public void instanciaUserController(String email) {
        try{
            UserRepository userRepository = new UserRepository();
            userRepository.instanciaUserRepository(email);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("Não foi possível conectar a sua conta.");
        }
    }

    public User getUserInstance() {
        return userInstance;
    }

    public void setUserInstance(User userInstance) {
        UserController.userInstance = userInstance;
    }
}