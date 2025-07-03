package database;

import entities.User;
import exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends DataBaseConnection implements Repository<User>{

    @Override
    public void insert(User user) {
        try{
            Connection conn=connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(name, email, password) VALUES (?,?,?)");
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getEmail());
            stmt.setString(3, user.getPassword());
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
        }catch (Exception e){
            throw new SystemException("Erro no sistema", e);
        }
    }
    
}
