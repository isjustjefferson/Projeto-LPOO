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
            //PreparedStatement stmt = conn.prepareStatement("DROP TABLE meta");
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS meta("
                    + "id_meta INTEGER AUTO_INCREMENT PRIMARY KEY,"
                    + "nome TEXT NOT NULL,"
                    + "valor_alvo INTEGER NOT NULL,"
                    + "fk_user_email TEXT NOT NULL,"
                    + "FOREIGN KEY (fk_user_email) REFERENCES user(email)"
                    + ");");
            stmt.execute();
            System.out.println("TABELA CRIADA!");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}


