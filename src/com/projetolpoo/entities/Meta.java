package com.projetolpoo.entities;

import java.io.Serializable;

public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome; // Vai guardar o nome da meta
    private int valorAlvo; // Vai guardar o valor da meta

    public Meta() { // Aí é o construtor de uma "Meta". 
    }

    public Meta(String nome, int valorAlvo) {
        this.nome = nome;
        this.valorAlvo = valorAlvo;
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
    @Override
    public String toString() {
        return nome;
    }
}