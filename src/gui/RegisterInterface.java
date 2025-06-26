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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel CadastroContentPane;
	private JPasswordField SenhaRegisterField;
	private JPasswordField ConfirmacaoSenhaRegisterField;
	private JTextField NomeRegisterField;
	private JTextField EmailRegisterField;
	private JLabel NomeRegisterLabel;
	private JLabel CadastroRegisterLabel;
	private JLabel EmailRegisterLabel;
	private JLabel SenhaRegisterLabel;
	private JLabel ConfirmacaoSenhaRegisterLabel;

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
		CadastroContentPane = new JPanel();
		CadastroContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(CadastroContentPane);
		CadastroContentPane.setLayout(null);
		
		JPanel CadastroRegisterPanel = new JPanel();
		CadastroRegisterPanel.setBounds(218, 48, 334, 344);
		CadastroContentPane.add(CadastroRegisterPanel);
		CadastroRegisterPanel.setLayout(null);
		
		JButton CadastrarButton = new JButton("Cadastrar");
		CadastrarButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Abre a tela de login
		        LoginInterface loginFrame = new LoginInterface();
		        loginFrame.setVisible(true);

		        // Fecha a tela atual de cadastro
		        dispose();
		    }
		});
		CadastrarButton.setBounds(115, 279, 104, 21);
		CadastroRegisterPanel.add(CadastrarButton);
		
		SenhaRegisterField = new JPasswordField();
		SenhaRegisterField.setBounds(89, 186, 145, 21);
		CadastroRegisterPanel.add(SenhaRegisterField);
		
		ConfirmacaoSenhaRegisterField = new JPasswordField();
		ConfirmacaoSenhaRegisterField.setBounds(89, 240, 145, 19);
		CadastroRegisterPanel.add(ConfirmacaoSenhaRegisterField);
		
		NomeRegisterField = new JTextField();
		NomeRegisterField.setColumns(10);
		NomeRegisterField.setBounds(89, 84, 145, 21);
		CadastroRegisterPanel.add(NomeRegisterField);
		
		EmailRegisterField = new JTextField();
		EmailRegisterField.setColumns(10);
		EmailRegisterField.setBounds(89, 138, 145, 21);
		CadastroRegisterPanel.add(EmailRegisterField);
		
		NomeRegisterLabel = new JLabel("NOME");
		NomeRegisterLabel.setBounds(89, 61, 45, 13);
		CadastroRegisterPanel.add(NomeRegisterLabel);
		
		CadastroRegisterLabel = new JLabel("Cadastre-se");
		CadastroRegisterLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		CadastroRegisterLabel.setBounds(110, 25, 103, 13);
		CadastroRegisterPanel.add(CadastroRegisterLabel);
		
		EmailRegisterLabel = new JLabel("EMAIL");
		EmailRegisterLabel.setBounds(89, 115, 45, 13);
		CadastroRegisterPanel.add(EmailRegisterLabel);
		
		SenhaRegisterLabel = new JLabel("SENHA");
		SenhaRegisterLabel.setBounds(89, 169, 45, 13);
		CadastroRegisterPanel.add(SenhaRegisterLabel);
		
		ConfirmacaoSenhaRegisterLabel = new JLabel("CONFIRMAR A SENHA");
		ConfirmacaoSenhaRegisterLabel.setBounds(89, 217, 111, 13);
		CadastroRegisterPanel.add(ConfirmacaoSenhaRegisterLabel);

	}
}