package com.projetolpoo.gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.SystemException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class RegisterInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cadastroContentPane;
	private JPasswordField senhaRegisterField;
	private JPasswordField confirmacaoSenhaRegisterField;
	private JTextField nomeRegisterField;
	private JTextField emailRegisterField;
	private JLabel nomeRegisterLabel;
	private JLabel cadastroRegisterLabel;
	private JLabel emailRegisterLabel;
	private JLabel senhaRegisterLabel;
	private JLabel confirmacaoSenhaRegisterLabel;

	/**
	 * Launch the application.
	 */
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
    private Component frame;

	/**
	 * Create the frame.
	 */
	public RegisterInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 477);
		cadastroContentPane = new JPanel();
		cadastroContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastroContentPane);
		cadastroContentPane.setLayout(null);
		
		JPanel cadastroRegisterPanel = new JPanel();
		cadastroRegisterPanel.setBounds(218, 48, 334, 344);
		cadastroContentPane.add(cadastroRegisterPanel);
		cadastroRegisterPanel.setLayout(null);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
                        String nome = nomeRegisterField.getText();
                        String email = emailRegisterField.getText();
                        String senha = senhaRegisterField.getText();
                        String confirmacaoSenha = confirmacaoSenhaRegisterField.getText();
                        
                        try {
                            
                            UserController controller = new UserController();
                            controller.registraUsuario(nome, email, senha, confirmacaoSenha);
                            
                            JOptionPane.showMessageDialog(RegisterInterface.this.frame, "Sucesso ao cadastrar usuário.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                            
                        } catch (BusinessException be){
                            JOptionPane.showMessageDialog(RegisterInterface.this.frame, be.getMessage(), "Cadastro", JOptionPane.ERROR_MESSAGE);
                        } catch (SystemException se) {
                            JOptionPane.showMessageDialog(RegisterInterface.this.frame, se.getMessage(), "Cadastro", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        // Abre a tela de login
		        LoginInterface loginFrame = new LoginInterface();
		        loginFrame.setVisible(true);

		        // Fecha a tela atual de cadastro
		        dispose();
		    }
		});
		cadastrarButton.setBounds(115, 279, 104, 21);
		cadastroRegisterPanel.add(cadastrarButton);
		
		senhaRegisterField = new JPasswordField();
		senhaRegisterField.setBounds(89, 186, 145, 21);
		cadastroRegisterPanel.add(senhaRegisterField);
		
		confirmacaoSenhaRegisterField = new JPasswordField();
		confirmacaoSenhaRegisterField.setBounds(89, 240, 145, 19);
		cadastroRegisterPanel.add(confirmacaoSenhaRegisterField);
		
		nomeRegisterField = new JTextField();
		nomeRegisterField.setColumns(10);
		nomeRegisterField.setBounds(89, 84, 145, 21);
		cadastroRegisterPanel.add(nomeRegisterField);
		
		emailRegisterField = new JTextField();
		emailRegisterField.setColumns(10);
		emailRegisterField.setBounds(89, 138, 145, 21);
		cadastroRegisterPanel.add(emailRegisterField);
		
		nomeRegisterLabel = new JLabel("NOME");
		nomeRegisterLabel.setBounds(89, 61, 45, 13);
		cadastroRegisterPanel.add(nomeRegisterLabel);
		
		cadastroRegisterLabel = new JLabel("Cadastre-se");
		cadastroRegisterLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cadastroRegisterLabel.setBounds(110, 25, 103, 13);
		cadastroRegisterPanel.add(cadastroRegisterLabel);
		
		emailRegisterLabel = new JLabel("EMAIL");
		emailRegisterLabel.setBounds(89, 115, 45, 13);
		cadastroRegisterPanel.add(emailRegisterLabel);
		
		senhaRegisterLabel = new JLabel("SENHA");
		senhaRegisterLabel.setBounds(89, 169, 45, 13);
		cadastroRegisterPanel.add(senhaRegisterLabel);
		
		confirmacaoSenhaRegisterLabel = new JLabel("CONFIRMAR A SENHA");
		confirmacaoSenhaRegisterLabel.setBounds(89, 217, 111, 13);
		cadastroRegisterPanel.add(confirmacaoSenhaRegisterLabel);

	}
}