package com.projetolpoo.business;

import com.projetolpoo.database.Repository;
import com.projetolpoo.database.UserRepository;
import com.projetolpoo.entities.User;
import com.projetolpoo.exception.BusinessException;

public class UserController {
    
    public void registraUsuario(String nome, String email, String senha, String confirmacaoSenha){
        
        if (nome.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (nome.length()<4){
            throw new BusinessException("O nome deve ter 4 caracteres ou mais.");
        }
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.length()<8){
            throw new BusinessException("A senha deve ter 8 caracteres ou mais.");
        }
        
        if (!senha.equals(confirmacaoSenha)){
            throw new BusinessException("A senha precisa ser igual a confirmação.");
        }
   
        User user = new User (nome, email, senha);
        Repository repository = new UserRepository();
        boolean exists = repository.confirm(email);
        
        if (exists){
            throw new BusinessException ("Já existe um usuário com esse email.");
        }
        
        repository.insert(user);
    }
    
    public void confirmaUsuario(String email, String senha){
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        if (senha.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        UserRepository repository = new UserRepository();
        boolean exists = repository.login(email, senha);
        
        if (!exists){
            throw new BusinessException ("Usuário não encontrado.");
        }
    }
}
