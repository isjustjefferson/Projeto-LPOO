package com.projetolpoo.database;

import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Meta;
import com.projetolpoo.entities.User;
import com.projetolpoo.exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountRepository extends DataBaseConnection{

    public void inserirMeta(Meta meta, String email) {
        try{ 
            Connection conn=connect();
            
            Statement pragmaStmt = conn.createStatement();
            pragmaStmt.execute("PRAGMA journal_mode=WAL;");
            pragmaStmt.close();
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO meta(nome, valor_alvo, fk_user_email) VALUES (?,?,?)");
            stmt.setString(1,meta.getNome());
            stmt.setInt(2,meta.getValorAlvo());
            stmt.setString(3, email);
            stmt.execute();
        } catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public void deletarMeta(Meta meta){
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM meta WHERE nome = ?");
            stmt.setString(1,meta.getNome());
            stmt.execute();
        }catch(Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }

    public ResultSet selecionarMeta(String email){
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM meta WHERE fk_user_email = ?");
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            //throw new SystemException("Erro no sistema", e);
            return null;
        }
    }
    
    public boolean confirm(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
