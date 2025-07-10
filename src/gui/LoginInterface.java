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
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class LoginInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameLoginField;
    private JPasswordField passwordLoginField;
    private JCheckBox manterConectadoCheckBox;

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
        
        JLabel olaLabel = new JLabel("Olá!");
        olaLabel.setBounds(0, 60, 422, 40);
        olaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        olaLabel.setForeground(Color.BLACK);
        olaLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 30));
        loginPanel.add(olaLabel);
        
        JLabel bemVindoLabel = new JLabel("Seja bem-vindo!");
        bemVindoLabel.setBounds(0, 100, 422, 30);
        bemVindoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bemVindoLabel.setForeground(Color.BLACK);
        bemVindoLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 22));
        loginPanel.add(bemVindoLabel);
        
        JLabel usernameLoginLabel = new JLabel("Username");
        usernameLoginLabel.setBounds(111, 160, 200, 14);
        usernameLoginLabel.setForeground(Color.BLACK);
        loginPanel.add(usernameLoginLabel);
        
        usernameLoginField = new JTextField();
        usernameLoginField.setBounds(111, 180, 200, 30);
        loginPanel.add(usernameLoginField);
        
        JLabel passwordLoginLabel = new JLabel("Password");
        passwordLoginLabel.setBounds(111, 230, 200, 14);
        passwordLoginLabel.setForeground(Color.BLACK);
        loginPanel.add(passwordLoginLabel);
        
        passwordLoginField = new JPasswordField();
        passwordLoginField.setBounds(111, 250, 200, 30);
        loginPanel.add(passwordLoginField);
        
        manterConectadoCheckBox = new JCheckBox("Continuar conectado");
        manterConectadoCheckBox.setBounds(111, 290, 200, 20);
        manterConectadoCheckBox.setBackground(Color.WHITE);
        manterConectadoCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        loginPanel.add(manterConectadoCheckBox);
        
        JButton confirmarLoginBtn = new JButton("CONFIRMAR");
        confirmarLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = usernameLoginField.getText();
                char[] senhaChars = passwordLoginField.getPassword();
                String senha = new String(senhaChars);
                boolean manterConectado = manterConectadoCheckBox.isSelected();

                try {
                    UserController controller = new UserController();
                    controller.confirmaUsuario(email, senha);
                    
                    if(manterConectado) {
                    }
                    
                    JOptionPane.showMessageDialog(LoginInterface.this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (BusinessException be) {
                    JOptionPane.showMessageDialog(LoginInterface.this, be.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SystemException se) {
                    JOptionPane.showMessageDialog(LoginInterface.this, se.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Arrays.fill(senhaChars, ' ');
                }
                dispose();
            }
        });
        confirmarLoginBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        confirmarLoginBtn.setMargin(new Insets(2, 2, 2, 2));
        confirmarLoginBtn.setBounds(141, 330, 140, 35);
        confirmarLoginBtn.setBackground(new Color(70, 70, 70));
        confirmarLoginBtn.setForeground(Color.WHITE);
        loginPanel.add(confirmarLoginBtn);
        
        JButton recuperarSenhaBtn = new JButton("Esqueceu a senha?");
        recuperarSenhaBtn.setBounds(111, 380, 200, 20);
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
        
        JButton cadastrarBtn = new JButton("CADASTRE-SE");
        cadastrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterInterface registerFrame = new RegisterInterface();
                registerFrame.setVisible(true);
                dispose();
            }
        });
        cadastrarBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
        cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
        cadastrarBtn.setForeground(Color.BLACK);
        cadastrarBtn.setContentAreaFilled(false);
        cadastrarBtn.setBorderPainted(false);
        cadastrarBtn.setBounds(111, 420, 200, 24);
        loginPanel.add(cadastrarBtn);
    }
}