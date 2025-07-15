package com.projetolpoo.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.projetolpoo.entities.Transacao;
import java.time.LocalDate;

public class AdicionarTransacaoDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField descricaoField;
    private JTextField valorField;
    private Transacao novaTransacao = null;
    private boolean isReceita;

    public AdicionarTransacaoDialog(JFrame owner, boolean isReceita) {
        super(owner, "Adicionar Nova " + (isReceita ? "Receita" : "Despesa"), true);
        this.isReceita = isReceita;
        
        setBounds(100, 100, 450, 200);
        setLocationRelativeTo(owner);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        contentPanel.setLayout(new GridBagLayout());
        // ... (código do layout do formulário, similar ao de Metas)

        contentPanel.add(new JLabel("Nome:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, 0, new Insets(0, 0, 5, 5), 0, 0));
        descricaoField = new JTextField();
        contentPanel.add(descricaoField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
        
        contentPanel.add(new JLabel("Valor (R$):"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, 0, new Insets(0, 0, 0, 5), 0, 0));
        valorField = new JTextField();
        contentPanel.add(valorField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

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

            this.novaTransacao = new Transacao(descricao, valor, dataDaTransacao);
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