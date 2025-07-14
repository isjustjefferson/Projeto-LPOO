package com.projetolpoo.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class RecuperacaoSenhaInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private String email;

    public RecuperacaoSenhaInterface() {
        this("");
    }

    public RecuperacaoSenhaInterface(String email) {
        this.email = email;
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecuperacaoSenhaInterface frame = new RecuperacaoSenhaInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        setBackground(new Color(40, 40, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 40, 40));
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel recuperacaoPanel = new JPanel();
        recuperacaoPanel.setBounds(658, 0, 422, 720);
        recuperacaoPanel.setBackground(Color.WHITE);
        recuperacaoPanel.setLayout(null);
        contentPane.add(recuperacaoPanel);
        
        JPanel tituloPanel = new JPanel();
        tituloPanel.setBounds(80, 80, 260, 60);
        tituloPanel.setBackground(Color.WHITE);
        tituloPanel.setLayout(null);
        recuperacaoPanel.add(tituloPanel);
        
        JLabel linha1Label = new JLabel("RECUPERAÇÃO DE");
        linha1Label.setBounds(0, 0, 260, 30);
        linha1Label.setForeground(Color.BLACK);
        linha1Label.setFont(new Font("Roboto Condensed", Font.BOLD, 24));
        linha1Label.setHorizontalAlignment(JLabel.CENTER);
        tituloPanel.add(linha1Label);
        
        JLabel linha2Label = new JLabel("SENHA");
        linha2Label.setBounds(0, 30, 260, 30);
        linha2Label.setForeground(Color.BLACK);
        linha2Label.setFont(new Font("Roboto Condensed", Font.BOLD, 24));
        linha2Label.setHorizontalAlignment(JLabel.CENTER);
        tituloPanel.add(linha2Label);
        
        JLabel novaSenhaLabel = new JLabel("Nova senha");
        novaSenhaLabel.setBounds(110, 160, 100, 14);
        novaSenhaLabel.setForeground(Color.BLACK);
        novaSenhaLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        recuperacaoPanel.add(novaSenhaLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 180, 200, 30);
        passwordField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        recuperacaoPanel.add(passwordField);
        
        JLabel confirmarSenhaLabel = new JLabel("Confirmar senha");
        confirmarSenhaLabel.setBounds(110, 230, 120, 14);
        confirmarSenhaLabel.setForeground(Color.BLACK);
        confirmarSenhaLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        recuperacaoPanel.add(confirmarSenhaLabel);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(110, 250, 200, 30);
        passwordField_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        recuperacaoPanel.add(passwordField_1);
        
        JButton confirmarBtn = new JButton("CONFIRMAR");
        confirmarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String novaSenha = new String(passwordField.getPassword());
                String confirmarSenha = new String(passwordField_1.getPassword());
                
                if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(RecuperacaoSenhaInterface.this, 
                            "Por favor, preencha todos os campos obrigatórios.", 
                            "Erro", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (!novaSenha.equals(confirmarSenha)) {
                    JOptionPane.showMessageDialog(RecuperacaoSenhaInterface.this, 
                            "As senhas não coincidem. Por favor, verifique.", 
                            "Senhas diferentes", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                LoginInterface loginFrame = new LoginInterface();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        confirmarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        confirmarBtn.setMargin(new Insets(2, 2, 2, 2));
        confirmarBtn.setBounds(140, 320, 140, 35);
        confirmarBtn.setBackground(new Color(70, 70, 70));
        confirmarBtn.setForeground(Color.WHITE);
        recuperacaoPanel.add(confirmarBtn);
        
        JButton voltarBtn = new JButton("Voltar");
        voltarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginInterface loginFrame = new LoginInterface();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        voltarBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
        voltarBtn.setMargin(new Insets(2, 2, 2, 2));
        voltarBtn.setForeground(Color.BLACK);
        voltarBtn.setContentAreaFilled(false);
        voltarBtn.setBorderPainted(false);
        voltarBtn.setBounds(160, 380, 100, 24);
        recuperacaoPanel.add(voltarBtn);
    }
}