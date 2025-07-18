package com.projetolpoo.business;

import com.projetolpoo.database.AccountRepository;
import java.util.List;

import com.projetolpoo.entities.Meta;
import com.projetolpoo.exception.BusinessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetaController extends AccountController{

    //private static Meta metaInstance;
    private final UserController userController = UserController.getInstanceUserController();
    
    
// A conta financeira cujas metas este controller gerencia
    public void adicionarMeta(Meta meta) {
            
        AccountRepository accountRepository = new AccountRepository();
            
        if (meta == null) {
            throw new BusinessException("A meta não pode ser nula.");
        }
        if (accountRepository.contaMeta(userController.getUserInstance().getEmail()) > 5) { // Regra de negócio: limite de 5 metas
            throw new BusinessException("Você atingiu o limite máximo de 5 metas.");
        }
        
        accountRepository.inserirMeta(meta, userController.getUserInstance().getEmail());
        
    }
    
    public void removeMeta(Meta meta) {
        if (meta == null) {
            throw new BusinessException("A meta não pode ser nula para remoção.");
        }

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.deletarMeta(meta);
        AccountController.getAccountInstance().getMetas().remove(meta);
    }

    public List<Meta> getAllMetas(String email) {
        List<Meta> metas = new ArrayList<>();
        try {
            AccountRepository accountRepository = new AccountRepository();
            ResultSet result=accountRepository.selecionarMeta(email);
            while (result.next()){
                String nome=result.getString("nome"); 
                int valorAlvo=result.getInt("valor_alvo");
                
                Meta metaInstance = new Meta(nome, valorAlvo);
                metas.add(metaInstance);
            }
        } catch (SQLException ex) {
            throw new BusinessException("Erro ao carregar metas.");
        }
        return metas;
    }
    
}