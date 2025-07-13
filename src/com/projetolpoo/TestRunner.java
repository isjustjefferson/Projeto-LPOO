package com.projetolpoo;

import com.projetolpoo.business.RecuperacaoSenhaController;
import com.projetolpoo.exception.EmailException;

public class TestRunner {

    public static void main(String[] args) {

        System.out.println("\n--- Testando RecuperacaoSenhaController ---");
        RecuperacaoSenhaController controlador = new RecuperacaoSenhaController();
        String emailDoUsuarioParaRecuperacao = "enderson.s.backup@gmail.com";

        try {
            boolean sucessoDoFluxo = controlador.iniciarProcessoDeRecuperacao(emailDoUsuarioParaRecuperacao);
            System.out.println("Fluxo do RecuperacaoSenhaController Concluído com Sucesso: " + sucessoDoFluxo);
        } catch (EmailException e) {
            System.err.println("Erro no Fluxo do RecuperacaoSenhaController: " + e.getMessage());
            e.printStackTrace();
        }
    }
}