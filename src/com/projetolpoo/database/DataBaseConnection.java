package com.projetolpoo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    
    private final String URL = "jdbc:sqlite:./dbgerenciadorsqlite.db?journal_mode=WAL";
   
    public Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);     
    }
}

