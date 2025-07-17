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
import javax.swing.JRadioButton;

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
        contentPanel.setLayout(null);
        // ... (código do layout do formulário, similar ao de Metas)

        JLabel label = new JLabel("Nome:");
        label.setBounds(38, 32, 38, 14);
        contentPanel.add(label);
        descricaoField = new JTextField();
        descricaoField.setBounds(81, 30, 354, 18);
        contentPanel.add(descricaoField);
        
        JLabel label_1 = new JLabel("Valor (R$):");
        label_1.setBounds(15, 55, 61, 14);
        contentPanel.add(label_1);
        valorField = new JTextField();
        valorField.setBounds(81, 53, 354, 18);
        contentPanel.add(valorField);
        
        JRadioButton rdbtnFixo = new JRadioButton("Fixo");
        rdbtnFixo.setBounds(157, 79, 49, 22);
        contentPanel.add(rdbtnFixo);
        
        JRadioButton rdbtnVar = new JRadioButton("Variavel");
        rdbtnVar.setActionCommand("Variavel");
        rdbtnVar.setBounds(224, 79, 72, 22);
        contentPanel.add(rdbtnVar);

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