package com.projetolpoo.entities;

import java.time.LocalDate;

public class Receita {

    private double valor;
    private String nome;
    private LocalDate data;
    
    public Receita(double valor, String nome, LocalDate data){
        this.valor = valor;
        this.nome = nome;
        this.data = data;
    }
    
    public double getValor(){
        return valor;
    }
    
    public String getNome(){
        return nome;
    }
    
    public LocalDate data(){
        return data;
    }
}