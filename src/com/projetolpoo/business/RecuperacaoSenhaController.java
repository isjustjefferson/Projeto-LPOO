package com.projetolpoo.business;

import java.util.Random;

import javax.swing.JOptionPane;

import com.projetolpoo.exception.EmailException;
import com.projetolpoo.service.EmailService;

public class RecuperacaoSenhaController {

    private EmailService emailService;
    private String codigoAutenticacao;
    private String email;
    
    public RecuperacaoSenhaController(){
        this.emailService = new EmailService();
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
    
    public boolean iniciarProcessoDeRecuperacao(String userEmail) {
        this.setEmail(userEmail);
        this.setCodigoAutenticacao(gerarCodigo());

        try {
            emailService.enviarEmail(
                this.getEmail(),
                "Recuperação de Senha",
                "Seu código de autenticação é: " + this.getCodigoAutenticacao() + "\nVálido por 5 minutos."
            );

            String codigoInserido = JOptionPane.showInputDialog(
                null,
                "Enviamos um código para " + this.getEmail() + "\nDigite o código recebido:",
                "Verificação de Código",
                JOptionPane.PLAIN_MESSAGE
            );

            if (codigoInserido != null && codigoInserido.equals(this.getCodigoAutenticacao())) {
                JOptionPane.showMessageDialog(null, "Código correto! Prossiga para redefinir sua senha.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Código inválido ou expirado",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (EmailException ee) {
            JOptionPane.showMessageDialog(null,
                "Erro ao enviar e-mail de recuperação: " + ee.getMessage(),
                "Erro de E-mail",
                JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Um erro inesperado ocorreu: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }
}
