package com.projetolpoo.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.projetolpoo.business.UserController;
import com.projetolpoo.exception.BusinessException;
import com.projetolpoo.exception.SystemException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PerfilConfigDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField emailField;
    private JTextField novaSenhaField;
    private JPasswordField confirmaNovaSenhaField;
    private RoundPanel fotoPanel;
    private UserController userController;
    private ImageIcon novaFotoIcon = null;

    public PerfilConfigDialog(JFrame owner, UserController userController) {
        super(owner, "Configurações de Perfil", true);
        this.userController = userController;
        
        setBounds(100, 100, 500, 420);
        setLocationRelativeTo(owner);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Configurações de Perfil");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setBounds(10, 20, 466, 30);
        contentPanel.add(titleLabel);

        fotoPanel = new RoundPanel();
        fotoPanel.setCornerRadius(100);
        fotoPanel.setBackground(Color.BLACK);
        fotoPanel.setBounds(195, 60, 100, 100);
        contentPanel.add(fotoPanel);
        
        carregarFotoAtual();

        JButton alterarFotoBtn = new JButton("Alterar Foto");
        alterarFotoBtn.addActionListener(e -> onAlterarFoto());
        alterarFotoBtn.setBounds(190, 170, 110, 25);
        contentPanel.add(alterarFotoBtn);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        emailLabel.setBounds(85, 219, 55, 25);
        contentPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(147, 220, 273, 25);
        contentPanel.add(emailField);
        emailField.setColumns(10);

        JLabel novaSenhaLabel = new JLabel("Nova Senha:");
        novaSenhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        novaSenhaLabel.setBounds(60, 254, 80, 25);
        contentPanel.add(novaSenhaLabel);

        novaSenhaField = new JTextField();
        novaSenhaField.setColumns(10);
        novaSenhaField.setBounds(147, 255, 273, 25);
        contentPanel.add(novaSenhaField);

        JLabel confirmaNovaSenhaLabel = new JLabel("Confirmação de Senha:");
        confirmaNovaSenhaLabel.setBounds(10, 290, 140, 25);
        contentPanel.add(confirmaNovaSenhaLabel);
        
        confirmaNovaSenhaField = new JPasswordField();
        confirmaNovaSenhaField.setBounds(147, 290, 273, 25);
        contentPanel.add(confirmaNovaSenhaField);

        JButton salvarBtn = new JButton("Salvar Alterações");
        salvarBtn.addActionListener(e -> onSalvar());
        salvarBtn.setBounds(175, 340, 140, 30);
        contentPanel.add(salvarBtn);
    }
    
    private void carregarFotoAtual() {
        try {
            ImageIcon icon = userController.selecionaImagemController(); 
            if (icon != null) {
                Image img = icon.getImage().getScaledInstance(
                    fotoPanel.getWidth(),
                    fotoPanel.getHeight(),
                    Image.SCALE_SMOOTH);
                fotoPanel.setImage(img);
                fotoPanel.repaint();
            }
        } catch (Exception ex){
            // Silenciosamente ignora o erro se não houver foto
        }
    }

    private void onAlterarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar nova imagem de perfil");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                this.novaFotoIcon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                Image img = novaFotoIcon.getImage().getScaledInstance(fotoPanel.getWidth(), fotoPanel.getHeight(), Image.SCALE_SMOOTH);
                fotoPanel.setImage(img);
                fotoPanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a nova imagem: " + ex.getMessage());
            }
        }
    }
    
    private void onSalvar() {
        if (this.novaFotoIcon != null) {
            userController.registraImagem(this.novaFotoIcon);
        }
        
        try{
            userController.trocaSenhaController(emailField.getText(), novaSenhaField.getText(), confirmaNovaSenhaField.getText());
        }catch(BusinessException | SystemException e){
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
        dispose();
    }
}