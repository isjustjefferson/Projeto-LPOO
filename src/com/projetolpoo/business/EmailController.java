package com.projetolpoo.business;

import com.projetolpoo.database.UserRepository;
import com.projetolpoo.exception.BusinessException;

import java.util.Random;

import com.projetolpoo.service.EmailService;

import java.time.LocalDateTime;

public class EmailController {
    
    private static EmailController instance;
    private final EmailService emailService;
    private String codigoAutenticacao;
    private String email;
    private LocalDateTime horaEnvio;
    
    private EmailController(){
        this.emailService = new EmailService();
    }
    
    public static EmailController getInstance(){
        if (instance == null){
            instance = new EmailController();
        }
        return instance;
    }
    
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
        System.out.println(codigo);
        return codigo;
    }
    
    public boolean confirmaCodigo(String codigo){
        /*Duration duracao = Duration.between(horaEnvio, LocalDateTime.now());
        
        if (duracao.toMinutes()<5){
            throw new BusinessException("Código expirado.");
        }*/
        
        if (codigo.isBlank()){
            throw new BusinessException("Os campos nãos podem estar em branco.");
        }
        
        if (!codigo.equals(codigoAutenticacao)) {
            throw new BusinessException("Código inválido.");
        } else {
            return true;
        }
    }
    
    public void enviaEmailRecuperacao(String email) {
        setEmail(email);
        setCodigoAutenticacao(gerarCodigo());

        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        UserRepository repository = new UserRepository();
        boolean exists = repository.confirm(email);
        
        if (!exists){
            throw new BusinessException("Email não cadastrado.");
        }
        
        emailService.enviarEmail(
            this.getEmail(),
            "Recuperação de Senha",
            "Seu código de autenticação é: " + this.getCodigoAutenticacao() + "\nVálido por 5 minutos."
        );
        
        horaEnvio=LocalDateTime.now();
    }
    
    public void enviaEmailRegistro(String email){
        
        if (email.isBlank()){
            throw new BusinessException("Os campos não podem estar vazios.");
        }
        
        this.setEmail(email);
        setCodigoAutenticacao(gerarCodigo());

        UserRepository repository = new UserRepository();
        boolean exists = repository.confirm(email);
        
        if (!exists){
            emailService.enviarEmail(
                this.getEmail(),
                "Confirmação de Email",
                "Seu código de autenticação é: " + this.getCodigoAutenticacao() + "\nVálido por 5 minutos."
            );
        
            horaEnvio=LocalDateTime.now();
        } else {
            throw new BusinessException("O email já está sendo usado.");
        }
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
    
    public LocalDateTime getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(LocalDateTime horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

}