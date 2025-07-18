package com.projetolpoo.database;

import com.projetolpoo.entities.Meta;
import com.projetolpoo.entities.Transacao;
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
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public int contaMeta(String email){
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM meta WHERE fk_user_email = ?");
            ResultSet result=stmt.executeQuery();
            
            int total=0;
            if (result.next()){
                total = result.getInt(1);
            }
            return total;
        }catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
    public void inserirTransacao(Transacao transacao, String data, String email){
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transacao (descricao, valor, data, eh_fixo, fk_user_email) VALUES (?,?,?,?,?)");
            stmt.setString(1, transacao.getDescricao());
            stmt.setInt(2, transacao.getValor());
            stmt.setString(3, data);
            stmt.setBoolean(4, transacao.isFixo());
            stmt.setString(5, email);
            stmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet selecionarTransacao(String email){
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transacao WHERE fk_user_email = ?");
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            return result;
        }catch(Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
}
