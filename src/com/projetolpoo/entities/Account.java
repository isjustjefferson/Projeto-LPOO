package com.projetolpoo.entities;

import com.projetolpoo.business.MetaController;
import com.projetolpoo.business.UserController;
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable { 

    private static final long serialVersionUID = 1L; 
    
    private int balance; //Salva o saldo.
    
    
    private List<Meta> metas = new ArrayList<>(); //Lista que salva todos os objetos "META", ela sempre vai iniciar como uma lista vazia. 
    private List<Transacao> transacoes = new ArrayList<>(); //Lista que salva todas as transações (Receitas e Despesas) da conta. 

    
    public Account() { //Garante que uma Conta sempre comece com o saldo zerado (Calma, Elton!)
        this.balance = 0; 
    }
   
    public void adicionarTransacao(Transacao transacao) { //Adicionar e atualiza o "Balance"
        if (transacao != null) {
            this.transacoes.add(transacao);
            this.balance += transacao.getValor();
        }
    }

    public void adicionarMeta(Meta meta) {
        if (meta != null) {
            this.metas.add(meta);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Meta> getMetas() {
        MetaController metaController = new MetaController();
        UserController userController = UserController.getInstanceUserController();
        String email = userController.getUserInstance().getEmail();
        metas = metaController.getAllMetas(email);
        return metas;
    }
    
    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}