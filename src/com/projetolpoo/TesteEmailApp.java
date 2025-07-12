package com.projetolpoo;

import com.projetolpoo.business.RecuperacaoSenhaController;
import com.projetolpoo.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TesteEmailApp {

    public void enviarEmail(String[] args) {
        SpringApplication.run(TesteEmailApp.class, args);
    }

    @Bean
    CommandLineRunner run(EmailService emailService) {
        return args -> {
            RecuperacaoSenhaController controlador = new RecuperacaoSenhaController();
            String r = emailService.enviarEmail(controlador.getEmail(), "Recuperação de Senha", "Seu códgio de autenticação é "+ controlador.getCodigoAutenticacao());
            System.out.println(r);
        };
    }
}