package com.projetolpoo.gui;

import com.projetolpoo.business.EmailController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.EmailException;
import com.projetolpoo.exception.SystemException;
import java.awt.EventQueue;

public class RegisterInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private JTextField nomeRegisterField;
    private JTextField emailRegisterField;
    private JPasswordField senhaRegisterField;
    private JPasswordField confirmacaoSenhaRegisterField;
    private JTextField confirmacaoEmailRegisterField;
    private JDialog verificationDialog;
    private JTextField verificationCodeField;
    private String tempNome;
    private String tempEmail;
    private String tempCEmail;
    private String tempSenha;
    private String tempConfirmacaoSenha;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterInterface frame = new RegisterInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegisterInterface() {
        setBackground(new Color(40, 40, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 40, 40));
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(658, 0, 422, 720);
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setLayout(null);
        contentPane.add(registerPanel);
        
        JLabel juliusLabel = new JLabel("");
        juliusLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/julius_principal.png")));
        juliusLabel.setBounds(0, 0, 658, 695);
        contentPane.add(juliusLabel);
        
        JLabel registerLabel = new JLabel("CADASTRO");
        registerLabel.setBounds(140, 80, 140, 36);
        registerLabel.setForeground(Color.BLACK);
        registerLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 24));
        registerPanel.add(registerLabel);
        
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(110, 140, 80, 14);
        nomeLabel.setForeground(Color.BLACK);
        registerPanel.add(nomeLabel);
        
        nomeRegisterField = new JTextField();
        nomeRegisterField.setBounds(110, 160, 200, 30);
        registerPanel.add(nomeRegisterField);
        nomeRegisterField.setColumns(10);
        
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(110, 200, 80, 14);
        emailLabel.setForeground(Color.BLACK);
        registerPanel.add(emailLabel);
        
        emailRegisterField = new JTextField();
        emailRegisterField.setBounds(110, 220, 200, 30);
        registerPanel.add(emailRegisterField);
        emailRegisterField.setColumns(10);
        
        JLabel confirmacaoEmailLabel = new JLabel("Confirmar E-mail");
        confirmacaoEmailLabel.setBounds(110, 260, 120, 14);
        confirmacaoEmailLabel.setForeground(Color.BLACK);
        registerPanel.add(confirmacaoEmailLabel);
        
        confirmacaoEmailRegisterField = new JTextField();
        confirmacaoEmailRegisterField.setBounds(110, 280, 200, 30);
        registerPanel.add(confirmacaoEmailRegisterField);
        confirmacaoEmailRegisterField.setColumns(10);
        
        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setBounds(110, 320, 80, 14);
        senhaLabel.setForeground(Color.BLACK);
        registerPanel.add(senhaLabel);
        
        senhaRegisterField = new JPasswordField();
        senhaRegisterField.setBounds(110, 340, 200, 30);
        registerPanel.add(senhaRegisterField);
        
        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha");
        confirmarSenhaLabel.setBounds(110, 380, 120, 14);
        confirmarSenhaLabel.setForeground(Color.BLACK);
        registerPanel.add(confirmarSenhaLabel);
        
        confirmacaoSenhaRegisterField = new JPasswordField();
        confirmacaoSenhaRegisterField.setBounds(110, 400, 200, 30);
        registerPanel.add(confirmacaoSenhaRegisterField);
        
        JButton cadastrarBtn = new JButton("CADASTRAR");
        cadastrarBtn.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                try{
                    tempNome = nomeRegisterField.getText();
                    tempEmail = emailRegisterField.getText();
                    String confirmacaoEmail = confirmacaoEmailRegisterField.getText();
                    char[] senhaChars = senhaRegisterField.getPassword();
                    char[] confirmacaoChars = confirmacaoSenhaRegisterField.getPassword();
                    tempCEmail = new String(confirmacaoEmail);
                    tempSenha = new String(senhaChars);
                    tempConfirmacaoSenha = new String(confirmacaoChars);
                    
                    Arrays.fill(senhaChars, ' ');
                    Arrays.fill(confirmacaoChars, ' ');
                    
                    EmailController.getInstance().enviaEmailRegistro(tempEmail);
                    
                    showVerificationDialog(tempEmail);
                }catch (BusinessException | EmailException | SystemException ee){
                    JOptionPane.showMessageDialog(RegisterInterface.this, 
                        ee.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cadastrarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
        cadastrarBtn.setBounds(140, 460, 140, 35);
        cadastrarBtn.setBackground(new Color(70, 70, 70));
        cadastrarBtn.setForeground(Color.WHITE);
        registerPanel.add(cadastrarBtn);
        
        JButton voltarBtn = new JButton("VOLTAR");
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
        voltarBtn.setBounds(160, 510, 100, 24);
        registerPanel.add(voltarBtn);
    }
    
    private void showVerificationDialog(String email) {
        verificationDialog = new JDialog(this, "Verificação de Email", true);
        verificationDialog.setSize(350, 250); 
        verificationDialog.setLayout(new BorderLayout());
        verificationDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel instructionLabel = new JLabel("Digite o código enviado para:");
        instructionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(instructionLabel);

        JLabel emailLabel = new JLabel(tempEmail);
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD));
        emailLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(emailLabel);

        mainPanel.add(Box.createVerticalStrut(15));

        verificationCodeField = new JTextField();
        verificationCodeField.setMaximumSize(new Dimension(200, 30));
        verificationCodeField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        verificationCodeField.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(verificationCodeField);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0)); 
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JButton resendButton = new JButton("Reenviar Código");
        resendButton.addActionListener(e -> {
            try{
                EmailController.getInstance().enviaEmailRegistro(tempEmail);        
            
                JOptionPane.showMessageDialog(verificationDialog, 
                    "Novo código enviado!", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (BusinessException | EmailException | SystemException ee){
                JOptionPane.showMessageDialog(RegisterInterface.this, 
                    ee.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }        
        });
        buttonPanel.add(resendButton);

        JButton verifyButton = new JButton("Verificar");
        verifyButton.addActionListener(e -> {
            try{
                String codigoInserido=verificationCodeField.getText().trim();
                if(EmailController.getInstance().confirmaCodigo(codigoInserido)){
                    UserController userController = UserController.getInstanceUserController();
                    userController.registraUsuario(tempNome, tempEmail, tempSenha, tempConfirmacaoSenha);
                    JOptionPane.showMessageDialog(verificationDialog, 
                        "Cadastro realizado com sucesso!", 
                        "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE);
                    verificationDialog.dispose();
                
                    LoginInterface loginFrame = new LoginInterface();
                    loginFrame.setVisible(true);
                    dispose();
                }
            } catch (BusinessException | SystemException | EmailException ex){
                JOptionPane.showMessageDialog(verificationDialog, 
                    ex.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);     
                }
        });
        buttonPanel.add(verifyButton);

        mainPanel.add(buttonPanel);

        verificationDialog.add(mainPanel, BorderLayout.CENTER);
        verificationDialog.setVisible(true);
    }
}