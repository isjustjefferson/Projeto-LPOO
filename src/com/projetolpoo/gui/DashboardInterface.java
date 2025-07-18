package com.projetolpoo.gui;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Image;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.awt.BorderLayout; 
import java.time.LocalDate; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JToggleButton;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Meta;
import com.projetolpoo.entities.Transacao;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DashboardInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addRecBttn;
	private JComboBox<Object> comboBoxMetas;
	private JPanel graficoPanel;
	private RoundPanel profilePhotoPanel;
	private Account contaDoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardInterface frame = new DashboardInterface();
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
	public DashboardInterface() {
		this.contaDoUsuario = new Account();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(15, 0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel profileDashboard = new JPanel();
		profileDashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		profileDashboard.setBackground(new Color(255, 255, 255));
		profileDashboard.setBounds(10, 10, 100, 100);
		contentPane.add(profileDashboard);
		profileDashboard.setLayout(null);
		
				JButton escolherFotoBtn = new JButton("");
				escolherFotoBtn.setForeground(new Color(255, 255, 255));
				escolherFotoBtn.setBounds(10, 29, 80, 49);
				escolherFotoBtn.setContentAreaFilled(false); 
				escolherFotoBtn.setBorderPainted(false);     
				escolherFotoBtn.setFocusPainted(false);      
				profileDashboard.add(escolherFotoBtn);
				escolherFotoBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
				profilePhotoPanel = new RoundPanel();
				profilePhotoPanel.setBackground(new Color(0, 0, 0));
				profilePhotoPanel.setBounds(5, 5, 90, 90);
				profileDashboard.add(profilePhotoPanel);
				profilePhotoPanel.setCornerRadius(100);
				try {
				    ImageIcon icon = new ImageIcon("src/imagens/foto_padrao.png"); 
				    Image img = icon.getImage().getScaledInstance(
				        profilePhotoPanel.getWidth(), profilePhotoPanel.getHeight(), Image.SCALE_SMOOTH);
				    profilePhotoPanel.setImage(img);
				} catch (Exception e) {
				    System.out.println("Erro ao carregar imagem padrão: " + e.getMessage());
				}
				
				escolherFotoBtn.addActionListener(e -> {
				    JFileChooser fileChooser = new JFileChooser();
				    fileChooser.setDialogTitle("Selecionar imagem de perfil");
				    fileChooser.setAcceptAllFileFilterUsed(false);
				    fileChooser.addChoosableFileFilter(
				        new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

				    int result = fileChooser.showOpenDialog(this);
				    if (result == JFileChooser.APPROVE_OPTION) {
				        try {
				            ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
				            				            
				            Image img = icon.getImage().getScaledInstance(
				                profilePhotoPanel.getWidth(), profilePhotoPanel.getHeight(), Image.SCALE_SMOOTH);
				            				           
				            profilePhotoPanel.setImage(img);
				            				            
				            profilePhotoPanel.repaint();

				        } catch (Exception ex) {
				            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + ex.getMessage());
				        }
				    }
				});
		
		JPanel functionsDashboard = new JPanel();
		functionsDashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		functionsDashboard.setBounds(10, 120, 100, 310);
		contentPane.add(functionsDashboard);
		functionsDashboard.setLayout(null);
		
		JButton relatorioBtn = new JButton("Relatório");
		relatorioBtn.setMargin(new Insets(2, 2, 2, 2));
		relatorioBtn.setBounds(7, 279, 85, 21);
		functionsDashboard.add(relatorioBtn);
		relatorioBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		comboBoxMetas = new JComboBox<>();
		comboBoxMetas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {		        
		        Object itemSelecionado = comboBoxMetas.getSelectedItem();
		        if (itemSelecionado instanceof Meta) {
		            desenharGrafico((Meta) itemSelecionado); 
		        } else {		         
		            desenharGrafico(null);
		        }
		    }
		});
		comboBoxMetas.setBounds(10, 10, 82, 21);
		functionsDashboard.add(comboBoxMetas);
		
		JButton adicionarBtn = new JButton("Adicionar");
		adicionarBtn.setBounds(7, 217, 85, 21);
		functionsDashboard.add(adicionarBtn);
		adicionarBtn.setMargin(new Insets(2, 2, 2, 2));
		adicionarBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adicionarBtn.addActionListener(e -> {
		    if (contaDoUsuario.getMetas().size() >= 5) {
		        JOptionPane.showMessageDialog(this, "Você atingiu o limite máximo de 5 metas.", "Limite Atingido", JOptionPane.WARNING_MESSAGE);
		        return;
		    }
		    AdicionarMetaDialog dialog = new AdicionarMetaDialog(this);
		    dialog.setVisible(true);
		    Meta metaCriada = dialog.getNovaMeta();
		    if (metaCriada != null) {
		        contaDoUsuario.adicionarMeta(metaCriada);
		        atualizarMetasComboBox(); 
		        comboBoxMetas.setSelectedItem(metaCriada);
		        JOptionPane.showMessageDialog(this, "Meta '" + metaCriada.getNome() + "' adicionada com sucesso!");
		    }
		});
		
		JButton removerBtn = new JButton("Remover");
		removerBtn.setBounds(7, 248, 85, 21);
		functionsDashboard.add(removerBtn);
		removerBtn.setMargin(new Insets(2, 2, 2, 2));
		removerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removerBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Object itemSelecionado = comboBoxMetas.getSelectedItem();
		        if (!(itemSelecionado instanceof Meta)) {
		            JOptionPane.showMessageDialog(
		                DashboardInterface.this, 
		                "Por favor, selecione uma meta válida para remover.", 
		                "Nenhuma Meta Selecionada", 
		                JOptionPane.INFORMATION_MESSAGE
		            );
		            return; 
		        }

		        Meta metaParaRemover = (Meta) itemSelecionado;
		        Object[] options = { "Sim, remover", "Não" };
		        int resposta = JOptionPane.showOptionDialog(
		            DashboardInterface.this, 
		            "Você tem certeza que deseja remover a meta '" + metaParaRemover.getNome() + "'?",
		            "Confirmar Remoção",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options[1]
		        );

		        if (resposta == 0) { 
		            contaDoUsuario.getMetas().remove(metaParaRemover);
		            atualizarMetasComboBox();
		            desenharGrafico(null);
		            JOptionPane.showMessageDialog(DashboardInterface.this, "Meta removida com sucesso!");
		        }
		    }
		}); 
		
		
		JPanel functionsDashboardPanel = new JPanel();
		functionsDashboardPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		functionsDashboardPanel.setBounds(10, 440, 100, 233);
		contentPane.add(functionsDashboardPanel);
		functionsDashboardPanel.setLayout(null);
		
		JButton perfilBtn = new JButton("Perfil");
		perfilBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		perfilBtn.setBounds(7, 10, 85, 21);
		functionsDashboardPanel.add(perfilBtn);
		
		JButton desconectarBtn = new JButton("Desconectar");
		desconectarBtn.setMargin(new Insets(2, 2, 2, 2));
		desconectarBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		desconectarBtn.setBounds(7, 202, 85, 21);
		functionsDashboardPanel.add(desconectarBtn);
		
		JPanel bemVindoPanel = new JPanel();
		bemVindoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bemVindoPanel.setBounds(120, 10, 460, 100);
		contentPane.add(bemVindoPanel);
		bemVindoPanel.setLayout(null);
		
		JLabel bemVindoLabel = new JLabel("Olá! Bem-vindo ao");
		bemVindoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		bemVindoLabel.setBounds(10, 10, 184, 19);
		bemVindoPanel.add(bemVindoLabel);
		
		JPanel saldoAtualPanel = new JPanel();
		saldoAtualPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		saldoAtualPanel.setBounds(596, 10, 460, 100);
		contentPane.add(saldoAtualPanel);
		saldoAtualPanel.setLayout(null);
		
		JLabel saldoAtualLabel = new JLabel("Saldo Atual:");
		saldoAtualLabel.setForeground(new Color(0, 0, 0));
		saldoAtualLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		saldoAtualLabel.setBackground(new Color(0, 0, 0));
		saldoAtualLabel.setBounds(10, 10, 117, 19);
		saldoAtualPanel.add(saldoAtualLabel);
		
		JLabel valorSaldoLabel = new JLabel("");
		valorSaldoLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		valorSaldoLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorSaldoLabel.setBounds(10, 41, 438, 33);
		saldoAtualPanel.add(valorSaldoLabel);
		int saldo = 5000;
		String saldoStr = Integer.toString(saldo);
		valorSaldoLabel.setText("R$ " + saldoStr);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainPanel.setBounds(120, 120, 936, 553);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		graficoPanel = new JPanel();
		graficoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		graficoPanel.setBackground(new Color(217, 217, 217));
		graficoPanel.setBounds(10, 10, 916, 250);
		mainPanel.add(graficoPanel);
		graficoPanel.setLayout(null);
		
		JPanel receitasPanel = new JPanel();
		receitasPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		receitasPanel.setBackground(new Color(217, 217, 217));
		receitasPanel.setBounds(10, 270, 444, 273);
		mainPanel.add(receitasPanel);
		receitasPanel.setLayout(null);
		
		JLabel receitasLabel = new JLabel("RECEITAS");
		receitasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		receitasLabel.setBounds(142, 12, 127, 29);
		receitasPanel.add(receitasLabel);
		
		addRecBttn = new JButton("+");
		addRecBttn.setMargin(new Insets(2, 2, 2, 2));
		addRecBttn.setPreferredSize(new Dimension(1, 1));
		addRecBttn.setBounds(407, 242, 27, 21);
		receitasPanel.add(addRecBttn);
		
		JLabel recFixasLabel = new JLabel("Fixas:");
		recFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		recFixasLabel.setBounds(10, 59, 80, 29);
		receitasPanel.add(recFixasLabel);
		
		JLabel valorRecFixasLabel = new JLabel("A PORRA DO VALOR AQUI");
		valorRecFixasLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorRecFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		valorRecFixasLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		valorRecFixasLabel.setBounds(50, 94, 334, 44);
		receitasPanel.add(valorRecFixasLabel);
		
		JLabel recVarLabel = new JLabel("Variáveis:");
		recVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		recVarLabel.setBounds(10, 150, 119, 29);
		receitasPanel.add(recVarLabel);
		
		JLabel valorRecVarLabel = new JLabel("A PORRA DO VALOR AQUI");
		valorRecVarLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorRecVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		valorRecVarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		valorRecVarLabel.setBounds(50, 186, 334, 44);
		receitasPanel.add(valorRecVarLabel);
		
		JPanel despesasPanel = new JPanel();
		despesasPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		despesasPanel.setBackground(new Color(217, 217, 217));
		despesasPanel.setBounds(482, 270, 444, 273);
		mainPanel.add(despesasPanel);
		despesasPanel.setLayout(null);
		
		JLabel despesasLabel = new JLabel("DESPESAS");
		despesasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		despesasLabel.setBounds(153, 12, 143, 29);
		despesasPanel.add(despesasLabel);
		
		JButton addDespBttn = new JButton("+");
		addDespBttn.setPreferredSize(new Dimension(1, 1));
		addDespBttn.setMargin(new Insets(2, 2, 2, 2));
		addDespBttn.setBounds(407, 242, 27, 21);
		despesasPanel.add(addDespBttn);
		
		JLabel despFixasLabel = new JLabel("Fixas:");
		despFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		despFixasLabel.setBounds(12, 59, 80, 29);
		despesasPanel.add(despFixasLabel);
		
		JLabel valorDespFixasLabel = new JLabel("A PORRA DO VALOR AQUI");
		valorDespFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		valorDespFixasLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		valorDespFixasLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorDespFixasLabel.setBounds(50, 94, 334, 44);
		despesasPanel.add(valorDespFixasLabel);
		
		JLabel despVarLabel = new JLabel("Variáveis:");
		despVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		despVarLabel.setBounds(12, 150, 119, 29);
		despesasPanel.add(despVarLabel);
		
		JLabel valorDespVarLabel = new JLabel("A PORRA DO VALOR AQUI");
		valorDespVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		valorDespVarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		valorDespVarLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		valorDespVarLabel.setBounds(50, 186, 334, 44);
		despesasPanel.add(valorDespVarLabel);
		
		 atualizarMetasComboBox();
		 desenharGrafico(null);
		
	}

	private void atualizarMetasComboBox() {
        comboBoxMetas.removeAllItems();
        comboBoxMetas.addItem("Metas");
        for (Meta meta : this.contaDoUsuario.getMetas()) {
            comboBoxMetas.addItem(meta);
        }
    }
	
	private void desenharGrafico(Meta metaSelecionada) {
	    graficoPanel.removeAll();
	    graficoPanel.setLayout(new GridLayout(1, 1));
	    TimeSeries series = new TimeSeries("Evolução do Saldo");
	    double saldoCumulativo = 0.0;
	    
	    contaDoUsuario.getTransacoes().sort((t1, t2) -> t1.getData().compareTo(t2.getData()));
	    for (Transacao t : this.contaDoUsuario.getTransacoes()) {
	        saldoCumulativo += t.getValor();
	        series.add(new Day(t.getData().getDayOfMonth(), t.getData().getMonthValue(), t.getData().getYear()), saldoCumulativo);
	    }
	    
	    TimeSeriesCollection dataset = new TimeSeriesCollection();
	    dataset.addSeries(series);

	    JFreeChart chart = ChartFactory.createTimeSeriesChart("Evolução do Saldo", "Data", "Saldo (R$)", dataset, false, true, false);
	    XYPlot plot = chart.getXYPlot();
	    plot.clearRangeMarkers();
	    
	    if (metaSelecionada != null) {
	        ValueMarker marker = new ValueMarker(metaSelecionada.getValorAlvo());
	        marker.setPaint(Color.GREEN);
	        marker.setLabel(String.format("Meta: %s (R$ %d)", metaSelecionada.getNome(), metaSelecionada.getValorAlvo()));
	        marker.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
	        plot.addRangeMarker(marker);
	    }
	    
	    ChartPanel chartPanel = new ChartPanel(chart);
	    graficoPanel.add(chartPanel);
	    graficoPanel.revalidate();
	    graficoPanel.repaint();
	}
}