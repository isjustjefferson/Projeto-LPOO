package com.projetolpoo;

import com.projetolpoo.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TesteEmailApp {

    public static void main(String[] args) {
        SpringApplication.run(TesteEmailApp.class, args);
    }

    @Bean
    CommandLineRunner run(EmailService emailService) {
        return args -> {
            String r = emailService.enviarEmail("enderson.s.backup@gmail.com", "Assunto", "JEFF LINDO GOSTOSO CASA COMIGO");
            System.out.println(r);
        };
    }
}