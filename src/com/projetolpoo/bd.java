package com.projetolpoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bd {
    private static final String URL = "jdbc:sqlite:./dbgerenciadorsqlite.db";
    
    private static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);     
    }
    
    public static void main(String[] args){
        Connection conn;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS user("
                    + "id_user INTEGER AUTO_INCREMENT UNIQUE,"
                    + "nome TEXT NOT NULL,"
                    + "email TEXT NOT NULL PRIMARY KEY,"
                    + "senha TEXT NOT NULL,"
                    + "saldo INTEGER, "
                    + "imagem BLOB"
                    + ");");
            stmt.execute();
            System.out.println("TABELA CRIADA!");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}


