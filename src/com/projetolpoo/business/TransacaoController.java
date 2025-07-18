package com.projetolpoo.business;

import java.util.Comparator;
import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Transacao;
import com.projetolpoo.exception.BusinessException;

public class TransacaoController {
    
    private Account account; // A conta financeira cujas transações este controller gerencia

    public TransacaoController(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account não pode ser nula para o TransacaoController.");
        }
        this.account = account;
    }

    public void addTransacao(Transacao transaction) {
        if (transaction == null) {
            throw new BusinessException("Transação não pode ser nula.");
        }
        this.account.getTransacoes().add(transaction);
        // *** PONTO DE PERSISTÊNCIA ***
        // Chamar seu TransactionRepository para salvar esta transação no DB.
        // Ex: transactionRepository.save(transaction, this.account.getId());
    }

    /**
     * Remove uma transação da conta gerenciada.
     * @param transaction A Transacao a ser removida.
     * @throws BusinessException Se a transação for nula ou não encontrada na conta.
     */
    public void removeTransacao(Transacao transaction) {
        if (transaction == null) {
            throw new BusinessException("Transação não pode ser nula para remoção.");
        }
        if (!this.account.getTransacoes().remove(transaction)) {
            throw new BusinessException("Transação não encontrada na conta para remoção.");
        }
        // *** PONTO DE PERSISTÊNCIA ***
        // Chamar seu TransactionRepository para remover esta transação do DB.
        // Ex: transactionRepository.delete(transaction.getId());
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