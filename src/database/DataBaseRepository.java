package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseRepository {
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/dbgerenciador";
    private final String USER = "root";
    private final String PASS = "Projeto.LPOO123";
 
    public Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);     
    }
}

