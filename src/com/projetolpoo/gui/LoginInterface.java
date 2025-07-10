package com.projetolpoo.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;

import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.SystemException;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class LoginInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameLoginField;
	private JPasswordField passwordLoginField;

	/**
	 * Launch the application.
	 */
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
    private Component frame;

	/**
	 * Create the frame.
	 */
	public LoginInterface() {
		setBackground(new Color(119, 118, 123));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(186, 10, 77, 36);
		loginLabel.setForeground(new Color(255, 255, 255));
		loginLabel.setFont(new Font("Roboto Condensed", Font.BOLD, 30));
		contentPane.add(loginLabel);
		
		JLabel usernameLoginLabel = new JLabel("username");
		usernameLoginLabel.setBounds(196, 65, 58, 14);
		usernameLoginLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(usernameLoginLabel);
		
		JLabel passwordLoginLabel = new JLabel("password");
		passwordLoginLabel.setForeground(new Color(255, 255, 255));
		passwordLoginLabel.setBounds(194, 114, 60, 14);
		contentPane.add(passwordLoginLabel);
		
		usernameLoginField = new JTextField();
		usernameLoginField.setBounds(173, 84, 104, 18);
		contentPane.add(usernameLoginField);
		usernameLoginField.setColumns(10);
		
		JButton confirmarLoginBtn = new JButton("CONFIRMAR");
                confirmarLoginBtn.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) {
                        
                        String email = usernameLoginField.getText();
                        String senha = passwordLoginField.getText();

                        try {
                            
                            UserController controller = new UserController();
                            controller.confirmaUsuario(email, senha);
                     
                            JOptionPane.showMessageDialog(LoginInterface.this.frame, "Usuário encontrado.", "Login", JOptionPane.INFORMATION_MESSAGE);
                            
                        }catch (BusinessException be){
                            JOptionPane.showMessageDialog(LoginInterface.this.frame, be.getMessage(), "Login", JOptionPane.ERROR_MESSAGE);
                        } catch (SystemException se) {
                            JOptionPane.showMessageDialog(LoginInterface.this.frame, se.getMessage(), "Login", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        dispose();
                    }
                });
		confirmarLoginBtn.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		confirmarLoginBtn.setMargin(new Insets(2, 2, 2, 2));
		confirmarLoginBtn.setBounds(173, 184, 110, 24);
		contentPane.add(confirmarLoginBtn);
		
		passwordLoginField = new JPasswordField();
		passwordLoginField.setBounds(171, 133, 104, 18);
		contentPane.add(passwordLoginField);
		
		JButton cadastrarBtn = new JButton("cadastrar-se");
		cadastrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterInterface registerFrame = new RegisterInterface();
				registerFrame.setVisible(true);
				dispose();
			}
		});
		cadastrarBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
		cadastrarBtn.setMargin(new Insets(2, 2, 2, 2));
		cadastrarBtn.setForeground(new Color(255, 255, 255));
		cadastrarBtn.setContentAreaFilled(false);
		cadastrarBtn.setBorderPainted(false);
		cadastrarBtn.setBounds(132, 215, 78, 24);
		contentPane.add(cadastrarBtn);
		
		JButton redefinirsenhaBtn = new JButton("redefinir senha");
		redefinirsenhaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecuperacaoSenhaInterface RecuperacaoFrame = new RecuperacaoSenhaInterface();
				RecuperacaoFrame.setVisible(true);
				dispose();
				
			}
		});
		redefinirsenhaBtn.setBorderPainted(false);
		redefinirsenhaBtn.setMargin(new Insets(2, 2, 2, 2));
		redefinirsenhaBtn.setContentAreaFilled(false);
		redefinirsenhaBtn.setForeground(new Color(255, 255, 255));
		redefinirsenhaBtn.setFont(new Font("Dialog", Font.PLAIN, 12));
		redefinirsenhaBtn.setBounds(242, 215, 98, 24);
		contentPane.add(redefinirsenhaBtn);

	}
}
