package com.projetolpoo.entities;
import com.projetolpoo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class User {
    
    private String nome;
    private String email;
    private String senha;
    private byte[] imagem;
    
    @Autowired
    private EmailService emailService;

    public User (String nome, String email, String senha){
        this.nome=nome;
        this.email=email;
        this.senha=senha;
        
        if (emailService != null) {
            emailService.enviarEmail(email, "Teste", "Teste");
        }
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
        
}