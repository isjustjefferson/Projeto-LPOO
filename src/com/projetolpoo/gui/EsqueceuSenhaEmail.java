package com.projetolpoo.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EsqueceuSenhaEmail extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField emailField;

    public EsqueceuSenhaEmail() {
        setBackground(new Color(40, 40, 40));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 40, 40));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(658, 0, 422, 720);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        contentPane.add(mainPanel);
        
        JLabel tituloLabel = new JLabel("Recuperar Senha");
        tituloLabel.setBounds(0, 60, 422, 40);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 30));
        mainPanel.add(tituloLabel);
        
        JLabel instrucaoLabel = new JLabel("Digite seu email cadastrado");
        instrucaoLabel.setBounds(0, 120, 422, 30);
        instrucaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instrucaoLabel.setForeground(Color.BLACK);
        instrucaoLabel.setFont(new Font("Roboto Condensed", Font.PLAIN, 16));
        mainPanel.add(instrucaoLabel);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(111, 170, 200, 14);
        emailLabel.setForeground(Color.BLACK);
        mainPanel.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(111, 190, 200, 30);
        mainPanel.add(emailField);
        
        JButton continuarBtn = new JButton("CONTINUAR");
        continuarBtn.setBounds(141, 250, 140, 35);
        continuarBtn.setBackground(new Color(70, 70, 70));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        continuarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                
                if (email.isEmpty() || !email.contains("@")) {
                    JOptionPane.showMessageDialog(EsqueceuSenhaEmail.this, 
                            "Por favor, digite um email válido", 
                            "Atenção", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                RecuperacaoSenhaInterface recuperacaoFrame = new RecuperacaoSenhaInterface(email);
                recuperacaoFrame.setVisible(true);
                dispose();
            }
        });
        mainPanel.add(continuarBtn);
        
        JButton voltarBtn = new JButton("Voltar ao login");
        voltarBtn.setBounds(111, 300, 200, 20);
        voltarBtn.setBorderPainted(false);
        voltarBtn.setContentAreaFilled(false);
        voltarBtn.setForeground(new Color(0, 100, 200));
        voltarBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
        voltarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginInterface loginFrame = new LoginInterface();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        mainPanel.add(voltarBtn);
    }
}