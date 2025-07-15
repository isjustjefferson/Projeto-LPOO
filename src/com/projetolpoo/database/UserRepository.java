package com.projetolpoo.database;

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
    
    public boolean login(String email, String senha){
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT email, senha FROM user WHERE email = ? AND senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
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
            stmt.setString(1, novaSenha);
            stmt.setString(2, email);
            stmt.execute();
        } catch (Exception e){
            throw new SystemException("Erro no sistema",e);
        }
    }
}
