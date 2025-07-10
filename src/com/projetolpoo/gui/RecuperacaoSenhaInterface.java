package com.projetolpoo.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecuperacaoSenhaInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecuperacaoSenhaInterface frame = new RecuperacaoSenhaInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecuperacaoSenhaInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(111, 111, 111));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel RecuperacaoSenhaLabel = new JLabel("Recuperação de Senha ");
		RecuperacaoSenhaLabel.setForeground(new Color(255, 255, 255));
		RecuperacaoSenhaLabel.setFont(new Font("Poppins Medium", Font.BOLD, 34));
		RecuperacaoSenhaLabel.setBounds(311, 25, 461, 41);
		contentPane.add(RecuperacaoSenhaLabel);
		
		JLabel NovaSenhaRecuperacaoLabel = new JLabel("Nova Senha ");
		NovaSenhaRecuperacaoLabel.setForeground(new Color(255, 255, 255));
		NovaSenhaRecuperacaoLabel.setFont(new Font("Poppins Medium", Font.BOLD, 16));
		NovaSenhaRecuperacaoLabel.setBounds(311, 139, 114, 25);
		contentPane.add(NovaSenhaRecuperacaoLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(111, 111, 111));
		passwordField.setBounds(311, 174, 169, 30);
		contentPane.add(passwordField);
		
		JLabel ConfirmarNovaSenhaRecuperacaoLabel = new JLabel("Confirmar senha ");
		ConfirmarNovaSenhaRecuperacaoLabel.setForeground(new Color(255, 255, 255));
		ConfirmarNovaSenhaRecuperacaoLabel.setFont(new Font("Poppins Medium", Font.BOLD, 16));
		ConfirmarNovaSenhaRecuperacaoLabel.setBounds(311, 231, 149, 30);
		contentPane.add(ConfirmarNovaSenhaRecuperacaoLabel);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBackground(new Color(111, 111, 111));
		passwordField_1.setBounds(311, 271, 169, 30);
		contentPane.add(passwordField_1);
		
		JButton ConfirmarSenhaRecuperacaobtn = new JButton("Confirmar");
		ConfirmarSenhaRecuperacaobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginInterface LoginFrame = new LoginInterface();
				LoginFrame.setVisible(true);
				dispose();
				

			}
		});
		ConfirmarSenhaRecuperacaobtn.setFont(new Font("Poppins Medium", Font.BOLD, 12));
		ConfirmarSenhaRecuperacaobtn.setBounds(462, 350, 114, 41);
		contentPane.add(ConfirmarSenhaRecuperacaobtn);
	}
}
