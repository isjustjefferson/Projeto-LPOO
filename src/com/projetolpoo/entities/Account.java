package com.projetolpoo.entities;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable { 

    private static final long serialVersionUID = 1L; 
    
    private double balance; 
    
    
    private List<Meta> metas = new ArrayList<>();
    private List<Transacao> transacoes = new ArrayList<>();

    
    public Account() {
        this.balance = 0.0; 
    }

   
    public void adicionarTransacao(Transacao transacao) {
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


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Meta> getMetas() {
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