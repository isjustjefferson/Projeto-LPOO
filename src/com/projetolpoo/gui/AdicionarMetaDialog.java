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

import com.projetolpoo.entities.Meta;

public class AdicionarMetaDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField valorField;
    private Meta novaMeta = null;

    public AdicionarMetaDialog(JFrame owner) {
        super(owner, "Adicionar Nova Meta", true);
        
        setBounds(100, 100, 450, 200);
        setLocationRelativeTo(owner);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        contentPanel.setLayout(gbl_contentPanel);

        contentPanel.add(new JLabel("Nome da Meta:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, 0, new Insets(0, 0, 5, 5), 0, 0));
        nomeField = new JTextField();
        contentPanel.add(nomeField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
        
        contentPanel.add(new JLabel("Valor Alvo (R$):"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, 0, new Insets(0, 0, 0, 5), 0, 0));
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
        String nome = nomeField.getText();
        String valorStr = valorField.getText().replace(",", ".");

        if (nome.trim().isEmpty() || valorStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ambos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int valor = Integer.parseInt(valorStr);
            if (valor <= 0) {
                 JOptionPane.showMessageDialog(this, "O valor deve ser um número positivo.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            this.novaMeta = new Meta(nome, valor);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O valor deve ser um número válido (ex: 5000).", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancelar() {
        this.novaMeta = null;
        dispose();
    }
    
    public Meta getNovaMeta() {
        return this.novaMeta;
    }
}