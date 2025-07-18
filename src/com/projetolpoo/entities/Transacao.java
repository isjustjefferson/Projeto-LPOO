package com.projetolpoo.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descricao; //Descreve o que é a transação. Ex: "Salário", "Aluguel"....
    private double valor; // é o valor, Se for positivo será Receita, se for nagativo será DESPESA. 
    private LocalDate data; // Guarda a Data de entrada do valor.
    private boolean isFixo;

    public Transacao() {
    }

    public Transacao(String descricao, double valor, LocalDate data, boolean isFixo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.isFixo = isFixo;
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

	public boolean isFixo() {
		return this.isFixo;
	}
}