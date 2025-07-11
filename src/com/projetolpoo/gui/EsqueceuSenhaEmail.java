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
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.util.Random;

public class EsqueceuSenhaEmail extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField emailField;
    private JTextField usuarioField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EsqueceuSenhaEmail frame = new EsqueceuSenhaEmail();
            frame.setVisible(true);
        });
    }

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
        
        JLabel instrucaoLabel = new JLabel("Digite seu usuário e e-mail cadastrado");
        instrucaoLabel.setBounds(0, 120, 422, 30);
        instrucaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instrucaoLabel.setForeground(Color.BLACK);
        instrucaoLabel.setFont(new Font("Roboto Condensed", Font.PLAIN, 16));
        mainPanel.add(instrucaoLabel);
        
        JLabel usuarioLabel = new JLabel("Usuário");
        usuarioLabel.setBounds(111, 170, 200, 14);
        usuarioLabel.setForeground(Color.BLACK);
        mainPanel.add(usuarioLabel);
        
        usuarioField = new JTextField();
        usuarioField.setBounds(111, 190, 200, 30);
        mainPanel.add(usuarioField);
        
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(111, 230, 200, 14);
        emailLabel.setForeground(Color.BLACK);
        mainPanel.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(111, 250, 200, 30);
        mainPanel.add(emailField);
        
        JButton continuarBtn = new JButton("CONTINUAR");
        continuarBtn.setBounds(141, 310, 140, 35);
        continuarBtn.setBackground(new Color(70, 70, 70));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        continuarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText().trim();
                String email = emailField.getText().trim();
                
                if (usuario.isEmpty() || email.isEmpty() || !email.contains("@")) {
                    JOptionPane.showMessageDialog(EsqueceuSenhaEmail.this, 
                            "Por favor, digite um usuário e e-mail válidos", 
                            "Atenção", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String codigoAutenticacao = gerarCodigoAutenticacao();
                String codigoInserido = JOptionPane.showInputDialog(
                    EsqueceuSenhaEmail.this,
                    "Enviamos um código para " + email + "\nDigite o código recebido:",
                    "Verificação de Código",
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (codigoInserido != null && codigoInserido.equals(codigoAutenticacao)) {
                    
                    mostrarDialogoNovaSenha();
                } else {
                    JOptionPane.showMessageDialog(EsqueceuSenhaEmail.this,
                            "Código inválido ou expirado",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(continuarBtn);
        
        JButton voltarBtn = new JButton("Voltar ao login");
        voltarBtn.setBounds(111, 360, 200, 20);
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
    
    private String gerarCodigoAutenticacao() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(999999);
        return String.valueOf(codigo);
    }
    
    private void mostrarDialogoNovaSenha() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPasswordField senhaField = new JPasswordField();
        JPasswordField confirmaSenhaField = new JPasswordField();
        
        panel.add(new JLabel("Nova Senha:"));
        panel.add(senhaField);
        panel.add(new JLabel("Confirmar Senha:"));
        panel.add(confirmaSenhaField);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Definir Nova Senha",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String novaSenha = new String(senhaField.getPassword());
            String confirmaSenha = new String(confirmaSenhaField.getPassword());
            
            if (novaSenha.isEmpty() || confirmaSenha.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha ambos os campos",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!novaSenha.equals(confirmaSenha)) {
                JOptionPane.showMessageDialog(this,
                        "As senhas não coincidem",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this,
                    "Senha alterada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            LoginInterface loginFrame = new LoginInterface();
            loginFrame.setVisible(true);
            dispose();
        }
    }
}