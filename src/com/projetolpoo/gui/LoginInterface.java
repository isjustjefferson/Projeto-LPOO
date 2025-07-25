package com.projetolpoo.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.SystemException;
import javax.swing.ImageIcon;

public class LoginInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameLoginField;
    private JPasswordField passwordLoginField;
    private ImageIcon olhoAbertoIcon;
	private ImageIcon olhoFechadoIcon;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginInterface frame = new LoginInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginInterface() {
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
        
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(658, 0, 422, 720);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(null);
        contentPane.add(loginPanel);
        
        JLabel juliusLabel = new JLabel("");
        juliusLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/julius_principal.png")));
        juliusLabel.setBounds(0, 0, 658, 695);
        contentPane.add(juliusLabel);
        
        JLabel bemVindoLabel = new JLabel("");
        bemVindoLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/Title.png")));
        bemVindoLabel.setBounds(86, 59, 195, 89);
        loginPanel.add(bemVindoLabel);

        JLabel iconeLabel = new JLabel("");
        iconeLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/BonecoGrande.png")));
        iconeLabel.setBounds(147, 162, 74, 81);
        loginPanel.add(iconeLabel);

        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(91, 263, 45, 13);
        loginPanel.add(emailLabel);
        
        usernameLoginField = new JTextField("Digite seu email");
        usernameLoginField.setForeground(Color.GRAY);
        usernameLoginField.setBounds(91, 282, 200, 30);
        loginPanel.add(usernameLoginField);

        usernameLoginField.addFocusListener(new FocusAdapter() {
           
            public void focusGained(FocusEvent e) {
                if (usernameLoginField.getText().equals("Digite seu email")) {
                    usernameLoginField.setText("");
                    usernameLoginField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (usernameLoginField.getText().isEmpty()) {
                    usernameLoginField.setForeground(Color.GRAY);
                    usernameLoginField.setText("Digite seu email");
                }
            }
        });
        
        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setBounds(91, 322, 45, 13);
        loginPanel.add(senhaLabel);
        
        passwordLoginField = new JPasswordField();
        passwordLoginField.setText("Digite sua senha");
        passwordLoginField.setForeground(Color.GRAY);
        passwordLoginField.setEchoChar((char) 0);
        passwordLoginField.setBounds(91, 341, 200, 30);
        loginPanel.add(passwordLoginField);

        passwordLoginField.setBounds(91, 341, 200, 30);
        loginPanel.add(passwordLoginField);

        passwordLoginField.addFocusListener(new FocusAdapter() {
        
            public void focusGained(FocusEvent e) {
                String pwd = new String(passwordLoginField.getPassword());
                if (pwd.equals("Digite sua senha")) {
                    passwordLoginField.setText("");
                    passwordLoginField.setForeground(Color.BLACK);
                    passwordLoginField.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                String pwd = new String(passwordLoginField.getPassword());
                if (pwd.isEmpty()) {
                    passwordLoginField.setText("Digite sua senha");
                    passwordLoginField.setForeground(Color.GRAY);
                    passwordLoginField.setEchoChar((char) 0);
                }
            }
        });
        
        
        JButton confirmarLoginBtn = new JButton("CONFIRMAR");
        confirmarLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = usernameLoginField.getText();
                char[] senhaChars = passwordLoginField.getPassword();
                String senha = new String(senhaChars);

                try {
                    UserController userController = UserController.getInstanceUserController();
                    userController.confirmaUsuario(email, senha);
                    
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    DashboardInterface dashboardFrame = new DashboardInterface();
                    dashboardFrame.setVisible(true);
                    dispose();
                } catch (BusinessException | SystemException ee) {
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        ee.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Arrays.fill(senhaChars, ' ');
                }
            }
        });
        confirmarLoginBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        confirmarLoginBtn.setMargin(new Insets(2, 2, 2, 2));
        confirmarLoginBtn.setBounds(123, 404, 140, 35);
        confirmarLoginBtn.setBackground(new Color(70, 70, 70));
        confirmarLoginBtn.setForeground(Color.WHITE);
        loginPanel.add(confirmarLoginBtn);
        
        olhoAbertoIcon = new ImageIcon(getClass().getResource("/imagens/olhoAberto.png"));
        olhoFechadoIcon = new ImageIcon(getClass().getResource("/imagens/olhoFechado.png"));
        
        JToggleButton verSenhaToggleButton = new JToggleButton(olhoFechadoIcon);
        verSenhaToggleButton.setBounds(301, 346, 20, 20);
        verSenhaToggleButton.setBorderPainted(false);
        verSenhaToggleButton.setContentAreaFilled(false);
        verSenhaToggleButton.setSelected(false);
        loginPanel.add(verSenhaToggleButton);
        
        verSenhaToggleButton.addActionListener(e -> {
            if (verSenhaToggleButton.isSelected()) {
                passwordLoginField.setEchoChar((char) 0);
                verSenhaToggleButton.setIcon(olhoAbertoIcon);
            } else {
                passwordLoginField.setEchoChar('•');
                verSenhaToggleButton.setIcon(olhoFechadoIcon);
               
            }
        });
        
        JButton recuperarSenhaBtn = new JButton("Esqueceu a senha?");
        recuperarSenhaBtn.setBounds(123, 478, 140, 20);
        recuperarSenhaBtn.setBorderPainted(false);
        recuperarSenhaBtn.setContentAreaFilled(false);
        recuperarSenhaBtn.setForeground(new Color(0, 100, 200));
        recuperarSenhaBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
        recuperarSenhaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EsqueceuSenhaEmail esqueceuSenhaEmail = new EsqueceuSenhaEmail();
                esqueceuSenhaEmail.setVisible(true);
                dispose();
            }
        });
        loginPanel.add(recuperarSenhaBtn);
        
        JButton cadastrarBtn = new JButton("Cadastre-se");
        cadastrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterInterface registerFrame = new RegisterInterface();
                registerFrame.setVisible(true);
                dispose();
            }
        });
        cadastrarBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
        cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
        cadastrarBtn.setForeground(new Color(0, 100, 200));
        cadastrarBtn.setContentAreaFilled(false);
        cadastrarBtn.setBorderPainted(false);
        cadastrarBtn.setBounds(127, 535, 136, 24);
        loginPanel.add(cadastrarBtn);
        
        JLabel ouLabel = new JLabel("Ou");
        ouLabel.setBounds(188, 508, 20, 20);
        loginPanel.add(ouLabel);

    }
}