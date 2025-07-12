package com.projetolpoo.business;

import java.util.Random;

public class RecuperacaoSenhaController {

    private String codigoAutenticacao;
    private String email;
    
    public String gerarCodigo(){
        String caracteres="abcdefghijqlmnopqrstuvwxyz0123456789";
        int tamanho=5;
        StringBuilder codigoSB=new StringBuilder();
        Random random = new Random();
        
        for (int i=0;i<tamanho;i++){
            int indice = random.nextInt(caracteres.length());
            codigoSB.append(caracteres.charAt(indice));
        }
      
        String codigo=codigoSB.toString(); 
        
        return codigo;
    }
    
    public String getCodigoAutenticacao() {
        return codigoAutenticacao;
    }

    public void setCodigoAutenticacao(String codigoAutenticacao) {
        this.codigoAutenticacao = codigoAutenticacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
