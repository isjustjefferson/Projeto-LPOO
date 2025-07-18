package com.projetolpoo.entities;

import com.projetolpoo.business.UserController;
import java.io.Serializable;

public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static String emailUser;
    private String nome; // Vai guardar o nome da meta
    private int valorAlvo; // Vai guardar o valor da meta

    public Meta() { // Aí é o construtor de uma "Meta". 
    }

    public Meta(String nome, int valorAlvo) {
        this.nome = nome;
        this.valorAlvo = valorAlvo;
        setUserEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValorAlvo() {
        return valorAlvo;
    }

    public void setValorAlvo(int valorAlvo) {
        this.valorAlvo = valorAlvo;
    }
    
    public void setUserEmail(){
        UserController userController=UserController.getInstanceUserController();
        emailUser = userController.getUserInstance().getEmail(); 
    }
    
    public String getUserEmail(){
        return emailUser;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}