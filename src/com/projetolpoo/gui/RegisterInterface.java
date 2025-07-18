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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
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
    private ImageIcon olhoAbertoIcon;
	private ImageIcon olhoFechadoIcon;
    private String tempNome;
    private String tempEmail;
    private String tempSenha;
    private String tempConfirmacaoSenha;
    private static EmailController instance;
    
    public static EmailController getInstance(){
        if (instance ==null){
            instance=new EmailController();
        }
        return instance;
    }
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
        
        JLabel foco = new JLabel();
        foco.setFocusable(true);
        contentPane.add(foco);
        
        olhoAbertoIcon = new ImageIcon(getClass().getResource("/imagens/olhoAberto.png"));
        olhoFechadoIcon = new ImageIcon(getClass().getResource("/imagens/olhoFechado.png"));
        
        JToggleButton verSenhaToggleButton = new JToggleButton(olhoFechadoIcon);
        verSenhaToggleButton.setBounds(315, 341, 20, 30);
        verSenhaToggleButton.setBorderPainted(false);
        verSenhaToggleButton.setContentAreaFilled(false);
        verSenhaToggleButton.setSelected(false);
        registerPanel.add(verSenhaToggleButton);
        
        verSenhaToggleButton.addActionListener(e -> {
            if (verSenhaToggleButton.isSelected()) {
            	senhaRegisterField.setEchoChar((char) 0);
                verSenhaToggleButton.setIcon(olhoAbertoIcon);
            } else {
            	senhaRegisterField.setEchoChar('•');
                verSenhaToggleButton.setIcon(olhoFechadoIcon);
               
            }
        });
        
        JToggleButton verSenhacToggleButton = new JToggleButton(olhoFechadoIcon);
        verSenhacToggleButton.setBounds(315, 400, 20, 30);
        verSenhacToggleButton.setBorderPainted(false);
        verSenhacToggleButton.setContentAreaFilled(false);
        verSenhacToggleButton.setSelected(false);
        registerPanel.add(verSenhacToggleButton);
        
        verSenhacToggleButton.addActionListener(e -> {
            if (verSenhacToggleButton.isSelected()) {
            	confirmacaoSenhaRegisterField.setEchoChar((char) 0);
                verSenhacToggleButton.setIcon(olhoAbertoIcon);
            } else {
            	confirmacaoSenhaRegisterField.setEchoChar('•');
                verSenhacToggleButton.setIcon(olhoFechadoIcon);
               
            }
        });
        
        nomeRegisterField = new JTextField("Digite seu nome");
        nomeRegisterField.setForeground(Color.GRAY);
        nomeRegisterField.setBounds(110, 160, 200, 30);
        registerPanel.add(nomeRegisterField);

        nomeRegisterField.addFocusListener(new FocusAdapter() {
           
            public void focusGained(FocusEvent e) {
                if (nomeRegisterField.getText().equals("Digite seu nome")) {
                	nomeRegisterField.setText("");
                	nomeRegisterField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (nomeRegisterField.getText().isEmpty()) {
                	nomeRegisterField.setForeground(Color.GRAY);
                	nomeRegisterField.setText("Digite seu nome");
                }
            }
        });
        
        emailRegisterField = new JTextField("Digite seu email");
        emailRegisterField.setForeground(Color.GRAY);
        emailRegisterField.setBounds(110, 224, 200, 30);
        registerPanel.add(emailRegisterField);

        emailRegisterField.addFocusListener(new FocusAdapter() {
           
            public void focusGained(FocusEvent e) {
                if (emailRegisterField.getText().equals("Digite seu email")) {
                	emailRegisterField.setText("");
                	emailRegisterField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (emailRegisterField.getText().isEmpty()) {
                	emailRegisterField.setForeground(Color.GRAY);
                	emailRegisterField.setText("Digite seu email");
                }
            }
        });
        
        confirmacaoEmailRegisterField = new JTextField("Digite seu email");
        confirmacaoEmailRegisterField.setForeground(Color.GRAY);
        confirmacaoEmailRegisterField.setBounds(110, 284, 200, 30);
        registerPanel.add(confirmacaoEmailRegisterField);

        confirmacaoEmailRegisterField.addFocusListener(new FocusAdapter() {
           
            public void focusGained(FocusEvent e) {
                if (confirmacaoEmailRegisterField.getText().equals("Digite seu email")) {
                	confirmacaoEmailRegisterField.setText("");
                	confirmacaoEmailRegisterField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (confirmacaoEmailRegisterField.getText().isEmpty()) {
                	confirmacaoEmailRegisterField.setForeground(Color.GRAY);
                	confirmacaoEmailRegisterField.setText("Digite seu email");
                }
            }
        });
        
        senhaRegisterField = new JPasswordField();
        senhaRegisterField.setText("Digite sua senha");
        senhaRegisterField.setForeground(Color.GRAY);
        senhaRegisterField.setEchoChar((char) 0);
        senhaRegisterField.setBounds(110, 284, 200, 30);
        registerPanel.add(senhaRegisterField);

        senhaRegisterField.setBounds(110, 341, 200, 30);
        registerPanel.add(senhaRegisterField);

        senhaRegisterField.addFocusListener(new FocusAdapter() {
        
            public void focusGained(FocusEvent e) {
                String pwd = new String(senhaRegisterField.getPassword());
                if (pwd.equals("Digite sua senha")) {
                	senhaRegisterField.setText("");
                	senhaRegisterField.setForeground(Color.BLACK);
                	senhaRegisterField.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                String pwd = new String(senhaRegisterField.getPassword());
                if (pwd.isEmpty()) {
                	senhaRegisterField.setText("Digite sua senha");
                	senhaRegisterField.setForeground(Color.GRAY);
                	senhaRegisterField.setEchoChar((char) 0);
                }
            }
        });
        
        confirmacaoSenhaRegisterField = new JPasswordField();
        confirmacaoSenhaRegisterField.setText("Digite sua senha");
        confirmacaoSenhaRegisterField.setForeground(Color.GRAY);
        confirmacaoSenhaRegisterField.setEchoChar((char) 0);
        confirmacaoSenhaRegisterField.setBounds(110, 400, 200, 30);
        registerPanel.add(confirmacaoSenhaRegisterField);

        senhaRegisterField.setBounds(110, 341, 200, 30);
        registerPanel.add(confirmacaoSenhaRegisterField);

        confirmacaoSenhaRegisterField.addFocusListener(new FocusAdapter() {
        
            public void focusGained(FocusEvent e) {
                String pwd = new String(confirmacaoSenhaRegisterField.getPassword());
                if (pwd.equals("Digite sua senha")) {
                	confirmacaoSenhaRegisterField.setText("");
                	confirmacaoSenhaRegisterField.setForeground(Color.BLACK);
                	confirmacaoSenhaRegisterField.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                String pwd = new String(confirmacaoSenhaRegisterField.getPassword());
                if (pwd.isEmpty()) {
                	confirmacaoSenhaRegisterField.setText("Digite sua senha");
                	confirmacaoSenhaRegisterField.setForeground(Color.GRAY);
                	confirmacaoSenhaRegisterField.setEchoChar((char) 0);
                }
            }
        });
        
        
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
        
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(110, 200, 80, 14);
        emailLabel.setForeground(Color.BLACK);
        registerPanel.add(emailLabel);
        
        JLabel confirmacaoEmailLabel = new JLabel("Confirmar E-mail");
        confirmacaoEmailLabel.setBounds(110, 260, 120, 14);
        confirmacaoEmailLabel.setForeground(Color.BLACK);
        registerPanel.add(confirmacaoEmailLabel);
        
        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setBounds(110, 320, 80, 14);
        senhaLabel.setForeground(Color.BLACK);
        registerPanel.add(senhaLabel);
        
        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha");
        confirmarSenhaLabel.setBounds(110, 380, 120, 14);
        confirmarSenhaLabel.setForeground(Color.BLACK);
        registerPanel.add(confirmarSenhaLabel);
        
        JLabel cadastrarLabel = new JLabel("");
        cadastrarLabel.setIcon(new ImageIcon(RegisterInterface.class.getResource("/imagens/BotãoDeCadastro.png")));
        cadastrarLabel.setBounds(140, 457, 140, 35);
        registerPanel.add(cadastrarLabel);
        
        JButton cadastrarBtn = new JButton("");
        cadastrarBtn.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                try{
                    tempNome = nomeRegisterField.getText();
                    tempEmail = emailRegisterField.getText();
                    String confirmacaoEmail = confirmacaoEmailRegisterField.getText();
                    char[] senhaChars = senhaRegisterField.getPassword();
                    char[] confirmacaoChars = confirmacaoSenhaRegisterField.getPassword();
                    tempSenha = new String(senhaChars);
                    tempConfirmacaoSenha = new String(confirmacaoChars);
                    
                    Arrays.fill(senhaChars, ' ');
                    Arrays.fill(confirmacaoChars, ' ');
            
                    getInstance().enviaEmailRegistro(tempEmail);
                    
                    showVerificationDialog(tempEmail);
                }catch (BusinessException | EmailException | SystemException ee){
                    JOptionPane.showMessageDialog(RegisterInterface.this, 
                        ee.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
        cadastrarBtn.setBounds(140, 457, 140, 35);
        cadastrarBtn.setContentAreaFilled(false);  
        cadastrarBtn.setBorderPainted(false);      
        cadastrarBtn.setFocusPainted(false);       
        cadastrarBtn.setText("");                 
        cadastrarBtn.setIcon(null);                
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
        verificationDialog.getContentPane().setLayout(new BorderLayout());
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
                getInstance().enviaEmailRegistro(tempEmail);        
            
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
                if(getInstance().confirmaCodigo(codigoInserido)){
                    UserController userController = new UserController();
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

        verificationDialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
        verificationDialog.setVisible(true);
       
        
    }
}
