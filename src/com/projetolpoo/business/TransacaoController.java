package com.projetolpoo.business;

import com.projetolpoo.database.AccountRepository;
import java.util.Comparator;
import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Transacao;
import com.projetolpoo.exception.BusinessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransacaoController {
    
    private Account account; // A conta financeira cujas transações este controller gerencia
    private final UserController userController = UserController.getInstanceUserController();
    
    public void adicionarTransacao(Transacao transacao) {
        if (transacao == null) {
            throw new BusinessException("Transação não pode ser nula.");
        }
        
        if (transacao.getValor()==0){
            throw new BusinessException("Os valores da transação não podem ser iguais a zero.");
        }
        AccountRepository accountRepository = new AccountRepository();
        String dataString=transacao.getData().toString();
        
        accountRepository.inserirTransacao(transacao, dataString, userController.getUserInstance().getEmail());

    }
    
    public List<Transacao> getAllTransacoes(String email){
        List<Transacao> transacoes = new ArrayList();
        try{
            AccountRepository accountRepository = new AccountRepository();
            ResultSet result=accountRepository.selecionarTransacao(email);
            while (result.next()){
                String nome=result.getString("descricao"); 
                int valor=result.getInt("valor");
                String dataString=result.getString("data");
                LocalDate data = LocalDate.parse(dataString);
                boolean isFixo=result.getBoolean("eh_fixo");
                
            Transacao transacaoInstance = new Transacao(nome, valor, data, isFixo);
            transacoes.add(transacaoInstance);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return transacoes;
    }
    
    public void removeTransacao(Transacao transaction) {
        if (transaction == null) {
            throw new BusinessException("Transação não pode ser nula para remoção.");
        }
        if (!this.account.getTransacoes().remove(transaction)) {
            throw new BusinessException("Transação não encontrada na conta para remoção.");
        }
    }

    public double calculateCurrentBalance() {
        double balance = 0;
        for (Transacao t : account.getTransacoes()) {
            balance += t.getValor();
        }
        return balance;
    }

    public double calculaReceitasFixas() {
        double receitasFixas = 0;
        for (Transacao t : account.getTransacoes()) {
            if (t.getValor() > 0 && t.isFixo()) { // Se for receita (valor > 0) E for fixa
                receitasFixas += t.getValor();
            }
        }
        return receitasFixas;
    }

    public double calculaReceitasVariaveis() {
        double variableRevenues = 0;
        for (Transacao t : account.getTransacoes()) {
            if (t.getValor() > 0 && !t.isFixo()) { // Se for receita (valor > 0) E NÃO for fixa
                variableRevenues += t.getValor();
            }
        }
        return variableRevenues;
    }

    public double calculaDespesasFixas() {
        double fixedExpenses = 0;
        for (Transacao t : account.getTransacoes()) {
            if (t.getValor() < 0 && t.isFixo()) { // Se for despesa (valor < 0) E for fixa
                fixedExpenses += t.getValor();
            }
        }
        return fixedExpenses;
    }

    public double calculaDespesasVariaveis() {
        double variableExpenses = 0;
        for (Transacao t : account.getTransacoes()) {
            if (t.getValor() < 0 && !t.isFixo()) { // Se for despesa (valor < 0) E NÃO for fixa
                variableExpenses += t.getValor();
            }
        }
        return variableExpenses;
    }

    public TimeSeries generateBalanceTimeSeries() {
        TimeSeries series = new TimeSeries("Evolução do Saldo");
        List<Transacao> transactions = this.account.getTransacoes();
        if (!transactions.isEmpty()) {
            // Ordena as transações por data para garantir a soma cumulativa correta
            transactions.sort(Comparator.comparing(Transacao::getData));
            double cumulativeBalance = 0.0;
            for (Transacao t : transactions) {
                cumulativeBalance += t.getValor();
                series.addOrUpdate(new Day(t.getData().getDayOfMonth(), t.getData().getMonthValue(), t.getData().getYear()), cumulativeBalance);
            }
        }
        return series;
    }

    public List<Transacao> getAllTransactions() {
        return this.account.getTransacoes();
    }
}