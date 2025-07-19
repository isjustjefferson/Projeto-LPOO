package com.projetolpoo;

import com.projetolpoo.database.UserRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bd {
   
    public static void main(String[] args){
        try {
            UserRepository userRepository=new UserRepository();
            ResultSet result = userRepository.selecionarSaldo("boarhat2018@gmail.com");
            int valor = result.getInt("saldo");
            System.out.println(valor);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /* private static final String URL = "jdbc:sqlite:./dbgerenciadorsqlite.db";
    
    private static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);     
    }
    
    public static void main(String[] args){
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET saldo = ? WHERE email = ?");
            stmt.setInt(1, 0);
            stmt.setString(2, "boarhat2018@gmail.com");
            System.out.println("TABELA CRIADA!");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }*/
    
}


