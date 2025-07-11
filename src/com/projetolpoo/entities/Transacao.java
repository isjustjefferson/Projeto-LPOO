package com.projetolpoo.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descricao;
    private double valor;
    private LocalDate data;

    public Transacao() {
    }

    public Transacao(String descricao, double valor, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}