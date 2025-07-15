package com.projetolpoo.business;

import com.projetolpoo.database.Repository;
import com.projetolpoo.database.UserRepository;
import com.projetolpoo.entities.User;
import com.projetolpoo.exception.BusinessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class UserController {
    
    private User userInstance;
    
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
        Repository repository = new UserRepository();
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
        boolean exists = repository.login(getUserInstance());
        
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
    
    public void registraImagem(String email, ImageIcon imagem){
        try {
            byte[] imagemBytes=imageIconToBytes(imagem, "jpg");
            
            UserRepository userRepository = new UserRepository();
            userRepository.insereImagem(imagemBytes, email); 
        } catch (Exception ex) {
            throw new BusinessException("Não foi possível registrar a imagem.");
        }
    }
    
    public byte[] selecionaImagemController(){
        try{
            UserRepository userRepository = new UserRepository();
            return userRepository.selecionaImagemRepository(getUserInstance());
        }catch (Exception e){
            throw new BusinessException("Não foi possível selecionar a imagem.");
        }
    }

    public void instanciaUserController(String email) {
        try{
            UserRepository userRepository = new UserRepository();
            setUserInstance(userRepository.instanciaUserRepository(email));
        }catch (Exception e){
            throw new BusinessException("Não foi possível conectar a sua conta.");
        }
    }

    public User getUserInstance() {
        return userInstance;
    }

    public void setUserInstance(User userInstance) {
        this.userInstance = userInstance;
    }
}