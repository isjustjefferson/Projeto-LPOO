package gui;

import business.UserController;
import exception.BusinessException;
import exception.SystemException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class RegisterInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nomeRegisterField;
    private JTextField emailRegisterField;
    private JPasswordField senhaRegisterField;
    private JPasswordField confirmacaoSenhaRegisterField;

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
        
        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setBounds(110, 260, 80, 14);
        senhaLabel.setForeground(Color.BLACK);
        registerPanel.add(senhaLabel);
        
        senhaRegisterField = new JPasswordField();
        senhaRegisterField.setBounds(110, 280, 200, 30);
        registerPanel.add(senhaRegisterField);
        
        JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha");
        confirmarSenhaLabel.setBounds(110, 320, 120, 14);
        confirmarSenhaLabel.setForeground(Color.BLACK);
        registerPanel.add(confirmarSenhaLabel);
        
        confirmacaoSenhaRegisterField = new JPasswordField();
        confirmacaoSenhaRegisterField.setBounds(110, 340, 200, 30);
        registerPanel.add(confirmacaoSenhaRegisterField);
        
        JButton cadastrarBtn = new JButton("CADASTRAR");
        cadastrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeRegisterField.getText();
                String email = emailRegisterField.getText();
                char[] senhaChars = senhaRegisterField.getPassword();
                char[] confirmacaoChars = confirmacaoSenhaRegisterField.getPassword();
                String senha = new String(senhaChars);
                String confirmacaoSenha = new String(confirmacaoChars);

                try {
                    UserController controller = new UserController();
                    controller.registraUsuario(nome, email, senha, confirmacaoSenha);
                    JOptionPane.showMessageDialog(RegisterInterface.this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
                    LoginInterface loginFrame = new LoginInterface();
                    loginFrame.setVisible(true);
                    dispose();
                } catch (BusinessException be) {
                    JOptionPane.showMessageDialog(RegisterInterface.this, be.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SystemException se) {
                    JOptionPane.showMessageDialog(RegisterInterface.this, se.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Arrays.fill(senhaChars, ' ');
                    Arrays.fill(confirmacaoChars, ' ');
                }
            }
        });
        cadastrarBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
        cadastrarBtn.setBounds(140, 400, 140, 35);
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
        voltarBtn.setBounds(160, 450, 100, 24);
        registerPanel.add(voltarBtn);
    }
}