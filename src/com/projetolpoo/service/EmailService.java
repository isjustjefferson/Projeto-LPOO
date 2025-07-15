package com.projetolpoo.service;

import java.util.Properties;

import com.projetolpoo.exception.EmailException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

    private final String remetenteEmail = "juliusfinance710@gmail.com";
    private final String remetenteSenha = "pprh wfvz dcpw yziz";

    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";

    public String enviarEmail(String destinatario, String assunto, String mensagem) {

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetenteEmail, remetenteSenha);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetenteEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);

            Transport.send(message);

            return "Email enviado";

        } catch (MessagingException me) {
            me.printStackTrace();
            throw new EmailException("Erro ao enviar mensagem: " + me.getMessage(), me);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailException("Erro inesperado ao enviar mensagem: " + e.getMessage(), e);
        }
    }
}