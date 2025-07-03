package business;

import database.Repository;
import database.UserRepository;
import entities.User;
import exception.BusinessException;

public class UserController {
    
    public void registerUser(String name, String email, String password, String passwordConfirmation){
        
        if (name.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (name.length()<4){
            throw new BusinessException("O nome deve ter 4 caracteres ou mais.");
        }
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (password.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (password.length()<8){
            throw new BusinessException("A senha deve ter 8 caracteres ou mais.");
        }
        
        if (!password.equals(passwordConfirmation)){
            throw new BusinessException("A senha precisa ser igual a confirmação.");
        }
   
        User user = new User (name, email, password);
        Repository repository = new UserRepository();
        boolean exists = repository.confirm(email);
        
        if (exists){
            throw new BusinessException ("Já existe um usuário com esse email.");
        }
        
        repository.insert(user);
    }
}
