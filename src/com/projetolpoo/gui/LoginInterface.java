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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.SystemException;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class LoginInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JPanel contentPane;
    private JTextField usernameLoginField;
    private JPasswordField passwordLoginField;
    private JCheckBox manterConectadoCheckBox;
	private ImageIcon olhoAbertoIcon;
	private ImageIcon olhoFechadoIcon;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginInterface frame = new LoginInterface();
                    frame.setVisible(true);
                    contentPane.requestFocusInWindow();
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
        
        usernameLoginField = new JTextField("Digite seu email");
        usernameLoginField.setForeground(Color.GRAY);
        usernameLoginField.setBounds(91, 271, 200, 30);
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
        
        manterConectadoCheckBox = new JCheckBox("Continuar conectado");
        manterConectadoCheckBox.setBounds(111, 394, 136, 20);
        manterConectadoCheckBox.setBackground(Color.WHITE);
        manterConectadoCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
        loginPanel.add(manterConectadoCheckBox);
        
        JButton confirmarLoginBtn = new JButton("");
        confirmarLoginBtn.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/BotãoDeLogin.png")));
        confirmarLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = usernameLoginField.getText();
                char[] senhaChars = passwordLoginField.getPassword();
                String senha = new String(senhaChars);
                boolean manterConectado = manterConectadoCheckBox.isSelected();

                if(email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    Arrays.fill(senhaChars, ' ');
                    return;
                }

                try {
                    UserController controller = new UserController();
                    controller.confirmaUsuario(email, senha);
                    
                    if(manterConectado) {
                    }
                    
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (BusinessException be) {
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        be.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SystemException se) {
                    JOptionPane.showMessageDialog(LoginInterface.this, 
                        se.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Arrays.fill(senhaChars, ' ');
                }
            }
        });
        confirmarLoginBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        confirmarLoginBtn.setMargin(new Insets(2, 2, 2, 2));
        confirmarLoginBtn.setBounds(116, 433, 140, 35);
        confirmarLoginBtn.setBackground(new Color(70, 70, 70));
        confirmarLoginBtn.setForeground(Color.WHITE);
        loginPanel.add(confirmarLoginBtn);
        
        JButton recuperarSenhaBtn = new JButton("Esqueceu a senha?");
        recuperarSenhaBtn.setBounds(107, 478, 156, 20);
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
        cadastrarBtn.setBounds(86, 539, 194, 24);
        loginPanel.add(cadastrarBtn);
        
        JLabel iconeLabel_1 = new JLabel("");
        iconeLabel_1.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/BonecoGrande.png")));
        iconeLabel_1.setBounds(141, 161, 74, 81);
        loginPanel.add(iconeLabel_1);
        
        JLabel bemVindoLabel = new JLabel("");
        bemVindoLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/Title.png")));
        bemVindoLabel.setBounds(91, 66, 223, 63);
        loginPanel.add(bemVindoLabel);
        
        JLabel label = new JLabel("New label");
        label.setBounds(111, 161, -42, 6);
        loginPanel.add(label);
        
        JLabel lblNewLabel_3 = new JLabel("Ou");
        lblNewLabel_3.setBounds(180, 508, 20, 20);
        loginPanel.add(lblNewLabel_3);
        
        olhoAbertoIcon = new ImageIcon(getClass().getResource("/imagens/olhoAberto.png"));
        olhoFechadoIcon = new ImageIcon(getClass().getResource("/imagens/olhoFechado.png"));
        
        JToggleButton verSenhaToggleButton = new JToggleButton(olhoFechadoIcon);
        verSenhaToggleButton.setBounds(301, 351, 15, 15);
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
        
        JLabel juliusLabel = new JLabel("");
        juliusLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/imagens/julius_principal.png")));
        juliusLabel.setBounds(0, 0, 658, 695);
        contentPane.add(juliusLabel);
    }
}