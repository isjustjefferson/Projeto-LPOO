package com.projetolpoo.gui;

import com.projetolpoo.business.EmailController;
import com.projetolpoo.business.UserController;
import com.projetolpoo.database.Repository;
import com.projetolpoo.database.UserRepository;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.EmailException;
import com.projetolpoo.exception.SystemException;
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

public class EsqueceuSenhaEmail extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField emailField;

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
        setResizable(false);
        
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
        
        JLabel instrucaoLabel = new JLabel("Digite seu e-mail cadastrado");
        instrucaoLabel.setBounds(0, 120, 422, 30);
        instrucaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instrucaoLabel.setForeground(Color.BLACK);
        instrucaoLabel.setFont(new Font("Roboto Condensed", Font.PLAIN, 16));
        mainPanel.add(instrucaoLabel);
        
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(111, 200, 200, 14);
        emailLabel.setForeground(Color.BLACK);
        mainPanel.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(111, 220, 200, 30);
        mainPanel.add(emailField);
        
        JButton continuarBtn = new JButton("CONTINUAR");
        continuarBtn.setBounds(141, 280, 140, 35);
        continuarBtn.setBackground(new Color(70, 70, 70));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        continuarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    String email = emailField.getText().trim();
                    EmailController emailController = new EmailController();
                    emailController.enviaEmailRecuperacao(email);
                
                    String codigoInserido = JOptionPane.showInputDialog(
                        EsqueceuSenhaEmail.this,
                        "Enviamos um código para " + email + "\nDigite o código recebido:",
                        "Verificação de Código",
                        JOptionPane.PLAIN_MESSAGE
                    );
                                        
                    if (emailController.confirmaCodigo(codigoInserido)){
                        mostrarDialogoNovaSenha(email);
                    }
                } catch (EmailException | BusinessException | SystemException ee){
                    JOptionPane.showMessageDialog(EsqueceuSenhaEmail.this,
                        ee.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(continuarBtn);
        
        JButton voltarBtn = new JButton("Voltar ao login");
        voltarBtn.setBounds(111, 330, 200, 20);
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
    
    private void mostrarDialogoNovaSenha(String email) {
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
            
            try{
                UserController userController = new UserController();
                userController.trocaSenhaController(email, novaSenha, confirmaSenha);
                
                JOptionPane.showMessageDialog(this,
                    "Senha alterada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch(BusinessException | EmailException | SystemException ee){
                JOptionPane.showMessageDialog(EsqueceuSenhaEmail.this,
                        ee.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
  
            LoginInterface loginFrame = new LoginInterface();
            loginFrame.setVisible(true);
            dispose();
        }
    }
}