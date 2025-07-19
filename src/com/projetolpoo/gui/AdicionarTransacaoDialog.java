package com.projetolpoo.gui;

import com.projetolpoo.business.TransacaoController;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Transacao;
import java.time.LocalDate;
import com.projetolpoo.gui.DashboardInterface;
import java.awt.Dimension;

public class AdicionarTransacaoDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField descricaoField;
    private JTextField valorField;
    private Transacao novaTransacao = null;
    private boolean isReceita;
    private String tipoTransacao;

    public AdicionarTransacaoDialog(JFrame owner, boolean isReceita, Account conta, String tipoPadrao) {
        super(owner, "Adicionar " + (isReceita ? "Receita " : "Despesa ") + tipoPadrao, true);
        this.isReceita = isReceita;
        this.tipoTransacao = tipoPadrao;
        
        setBounds(100, 100, 450, 200);
        setLocationRelativeTo(owner);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        // ... (código do layout do formulário, similar ao de Metas)

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(38, 32, 38, 14);
        contentPanel.add(nomeLabel);
        descricaoField = new JTextField();
        descricaoField.setBounds(81, 30, 354, 18);
        contentPanel.add(descricaoField);
        
        JLabel valorLabel = new JLabel("Valor (R$):");
        valorLabel.setBounds(15, 55, 61, 14);
        contentPanel.add(valorLabel);
        valorField = new JTextField();
        valorField.setBounds(81, 53, 354, 18);
        contentPanel.add(valorField);
        
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> onSalvar());
        buttonPane.add(salvarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> onCancelar());
        buttonPane.add(cancelarButton);
    }

    private void onSalvar() {
        String descricao = descricaoField.getText();
        String valorStr = valorField.getText().replace(",", ".");

        if (descricao.trim().isEmpty() || valorStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ambos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int valor = Integer.parseInt(valorStr);
            if (valor <= 0) {
                 JOptionPane.showMessageDialog(this, "O valor deve ser um número positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            
            // Se for uma despesa, torna o valor negativo
            if (!this.isReceita) {
                valor *= -1;
            }

            // AQUI ESTÁ A LÓGICA DA DATA: Pega a data atual do sistema. Simples e garantido.
            LocalDate dataDaTransacao = LocalDate.now();

            boolean isFixo = this.tipoTransacao.equals("Fixo");

            this.novaTransacao = new Transacao(descricao, valor, dataDaTransacao, isFixo);
            TransacaoController transacaoController = new TransacaoController();
            transacaoController.adicionarTransacao(novaTransacao);
            dispose();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O valor deve ser um número válido (ex: 150.75).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancelar() {
        this.novaTransacao = null;
        dispose();
    }
    
    public Transacao getNovaTransacao() {
        return this.novaTransacao;
    }

	public Transacao getNovaReceita() {
		// TODO Auto-generated method stub
		return null;
	}
}