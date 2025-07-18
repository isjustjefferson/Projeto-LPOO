package com.projetolpoo.database;

import com.projetolpoo.business.UserController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.projetolpoo.entities.User;
import com.projetolpoo.exception.SystemException;

public class UserRepository extends DataBaseConnection implements Repository<User>{

    @Override
    public void insert(User user) {
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(nome, email, senha) VALUES (?,?,?)");
            stmt.setString(1,user.getNome());
            stmt.setString(2,user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.execute();
        } catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }

    @Override
    public boolean confirm(String email) {
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT email FROM user WHERE email=?;");
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public boolean login(User user){
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT email, senha FROM user WHERE email = ? AND senha = ?");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSenha());
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public void trocaSenhaRepository(String email, String novaSenha){
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET senha = ? WHERE email = ?");
            //stmt.execute("PRAGMA journal_mode=WAL;");
            stmt.setString(1, novaSenha);
            stmt.setString(2, email);
            stmt.execute();
        } catch (Exception e){
            e.printStackTrace();
            //throw new SystemException("Erro no sistema",e);
        }
    }
    
    public void insereImagem(byte[] imagemBytes, String email){
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET imagem = ? WHERE email = ?");
            stmt.setBytes(1, imagemBytes);
            stmt.setString(2, email);

            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public byte[] selecionaImagemRepository(User user){
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT imagem FROM user WHERE email = ?");
            stmt.setString(1, user.getEmail());

        try (ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                byte[] imagemBytes = result.getBytes("imagem");
                if (imagemBytes != null) {
                    return imagemBytes;
                }
            }
        }

        } catch (Exception e) {
            throw new SystemException("Erro ao selecionar imagem!", e);
        }

        return null;
    }
    
    public void instanciaUserRepository(String email){
        ResultSet result = null;
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT nome, email, senha FROM user WHERE email=?");
            stmt.setString(1, email);
            
            result = stmt.executeQuery();
            if (result.next()){
                UserController userController = new UserController();
                User user = new User(result.getString("nome"), result.getString("email"), result.getString("senha"));
                userController.setUserInstance(user);
            }
        }catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
}
        
