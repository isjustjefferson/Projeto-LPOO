package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegisterInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senhaField;
	private JPasswordField confirmacaoSenhaField;
	private JTextField nomeField;
	private JTextField emailField;
	private JLabel nomeLabel;
	private JLabel cadastroLabel;
	private JLabel emailLabel;
	private JLabel senhaLabel;
	private JLabel confirmacaoSenhaLabel;

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

	/**
	 * Create the frame.
	 */
	public RegisterInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(218, 48, 334, 344);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(109, 279, 104, 21);
		panel.add(cadastrarButton);
		
		senhaField = new JPasswordField();
		senhaField.setBounds(89, 186, 145, 21);
		panel.add(senhaField);
		
		confirmacaoSenhaField = new JPasswordField();
		confirmacaoSenhaField.setBounds(89, 240, 145, 19);
		panel.add(confirmacaoSenhaField);
		
		nomeField = new JTextField();
		nomeField.setColumns(10);
		nomeField.setBounds(89, 84, 145, 21);
		panel.add(nomeField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(89, 138, 145, 21);
		panel.add(emailField);
		
		nomeLabel = new JLabel("NOME");
		nomeLabel.setBounds(89, 61, 45, 13);
		panel.add(nomeLabel);
		
		cadastroLabel = new JLabel("Cadastre-se");
		cadastroLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cadastroLabel.setBounds(110, 25, 103, 13);
		panel.add(cadastroLabel);
		
		emailLabel = new JLabel("EMAIL");
		emailLabel.setBounds(89, 115, 45, 13);
		panel.add(emailLabel);
		
		senhaLabel = new JLabel("SENHA");
		senhaLabel.setBounds(89, 169, 45, 13);
		panel.add(senhaLabel);
		
		confirmacaoSenhaLabel = new JLabel("CONFIRMAR A SENHA");
		confirmacaoSenhaLabel.setBounds(89, 217, 111, 13);
		panel.add(confirmacaoSenhaLabel);

	}
}
