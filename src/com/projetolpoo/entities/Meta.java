package com.projetolpoo.entities;

import java.io.Serializable;

public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private double valorAlvo;

    public Meta() {
    }

    public Meta(String nome, double valorAlvo) {
        this.nome = nome;
        this.valorAlvo = valorAlvo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorAlvo() {
        return valorAlvo;
    }

    public void setValorAlvo(double valorAlvo) {
        this.valorAlvo = valorAlvo;
    }
    @Override
    public String toString() {
        return nome;
    }
}