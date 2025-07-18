package com.projetolpoo.business;

import com.projetolpoo.entities.Account;

public class AccountController {
    
    private static Account accountInstance; 
    
    public AccountController(){
        AccountController.accountInstance=new Account();
    }
    
    public static Account getAccountInstance(){
        return accountInstance;
    }
    
}
