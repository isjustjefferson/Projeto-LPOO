package com.projetolpoo.gui;

import java.awt.EventQueue;
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

public class DashboardInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton_4;
	private JComboBox<Object> comboBoxMetas;
	private JPanel graficoPanel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);
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
		
		JButton redondoBtn = new JButton("Trocar Foto");
		redondoBtn.setMargin(new Insets(2, 2, 2, 2));
		redondoBtn.setBounds(new Rectangle(12, 12, 0, 0));
		redondoBtn.setBounds(7, 41, 85, 21);
		profileDashboard.add(redondoBtn);
		
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
		
		JButton removerBtn = new JButton("Remover");
		removerBtn.setBounds(7, 248, 85, 21);
		functionsDashboard.add(removerBtn);
		removerBtn.setMargin(new Insets(2, 2, 2, 2));
		removerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton adicionarBtn = new JButton("Adicionar");
		adicionarBtn.setBounds(7, 217, 85, 21);
		functionsDashboard.add(adicionarBtn);
		adicionarBtn.setMargin(new Insets(2, 2, 2, 2));
		adicionarBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		bemVindoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bemVindoLabel.setBounds(10, 10, 118, 19);
		bemVindoPanel.add(bemVindoLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lorem ipsum dolor allahuh akbhar \r\nfire in the hole expeliarmus pokemon \r\nkaioken expectum patronum cruciatus \r\nred xvii ink pink blinky chapolim.");
		lblNewLabel_1.setBounds(10, 35, 307, 55);
		bemVindoPanel.add(lblNewLabel_1);
		
		JPanel saldoAtualPanel = new JPanel();
		saldoAtualPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		saldoAtualPanel.setBounds(596, 10, 460, 100);
		contentPane.add(saldoAtualPanel);
		saldoAtualPanel.setLayout(null);
		
		JLabel saldoAtualLabel = new JLabel("Saldo Atual:");
		saldoAtualLabel.setForeground(new Color(0, 0, 0));
		saldoAtualLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saldoAtualLabel.setBackground(new Color(0, 0, 0));
		saldoAtualLabel.setBounds(10, 10, 76, 19);
		saldoAtualPanel.add(saldoAtualLabel);
		
		JLabel valorSaldoAtualLabel = new JLabel("R$");
		valorSaldoAtualLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		valorSaldoAtualLabel.setBounds(20, 39, 45, 35);
		saldoAtualPanel.add(valorSaldoAtualLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(120, 120, 936, 553);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		graficoPanel = new JPanel();
		graficoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		graficoPanel.setBackground(new Color(217, 217, 217));
		graficoPanel.setBounds(10, 10, 916, 250);
		panel_4.add(graficoPanel);
		graficoPanel.setLayout(null);
		
		JPanel receitasPanel = new JPanel();
		receitasPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		receitasPanel.setBackground(new Color(217, 217, 217));
		receitasPanel.setBounds(10, 270, 444, 273);
		panel_4.add(receitasPanel);
		receitasPanel.setLayout(null);
		
		JLabel receitasLabel = new JLabel("Receitas:");
		receitasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		receitasLabel.setBounds(10, 10, 68, 29);
		receitasPanel.add(receitasLabel);
		
		btnNewButton_4 = new JButton("+");
		btnNewButton_4.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_4.setPreferredSize(new Dimension(1, 1));
		btnNewButton_4.setBounds(407, 242, 27, 21);
		receitasPanel.add(btnNewButton_4);
		
		JPanel despesasPanel = new JPanel();
		despesasPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		despesasPanel.setBackground(new Color(217, 217, 217));
		despesasPanel.setBounds(482, 270, 444, 273);
		panel_4.add(despesasPanel);
		despesasPanel.setLayout(null);
		
		JLabel despesasLabel = new JLabel("Despesas:");
		despesasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		despesasLabel.setBounds(10, 10, 68, 29);
		despesasPanel.add(despesasLabel);
		
		JButton btnNewButton_4_1 = new JButton("+");
		btnNewButton_4_1.setPreferredSize(new Dimension(1, 1));
		btnNewButton_4_1.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_4_1.setBounds(407, 242, 27, 21);
		despesasPanel.add(btnNewButton_4_1);
		
		 atualizarMetasComboBox();
		 desenharGrafico(null);
		
	}

	private void atualizarMetasComboBox() {

       //DADOS DE TESTE! CASO QUEIRAM USAR PARA APRESENTAR PARA CARTAXO, TIREM O COMENTARIO. 
		/*Account contaDeTeste = new Account();
        contaDeTeste.adicionarMeta(new Meta("Viagem", 2500.00));
        contaDeTeste.adicionarMeta(new Meta("Notebook", 3000.00));
        contaDeTeste.adicionarMeta(new Meta("Curso", 2000.00));
		
        comboBoxMetas.removeAllItems();
        comboBoxMetas.addItem("Metas");
        List<Meta> metas = contaDeTeste.getMetas();
        for (Meta meta : metas) {
            comboBoxMetas.addItem(meta);
        
        }
    */
		comboBoxMetas.addItem("Metas");
    }
	private void desenharGrafico(Meta metaSelecionada) {
	    //DADOS DE TESTE! CASO QUEIRAM APRESENTAR PRA CARTAXO, RETIREM O COMENTARIO. 
	    /*Account contaDeTeste = new Account();
	    // Receitas
	    contaDeTeste.adicionarTransacao(new Transacao("Salário", 3500.00, LocalDate.of(2025, 7, 5)));
	    contaDeTeste.adicionarTransacao(new Transacao("Freelance", 800.00, LocalDate.of(2025, 7, 12)));
	    // Despesas
	    contaDeTeste.adicionarTransacao(new Transacao("Aluguel", -1200.00, LocalDate.of(2025, 7, 6)));
	    contaDeTeste.adicionarTransacao(new Transacao("Internet", -150.00, LocalDate.of(2025, 7, 10)));
	    contaDeTeste.adicionarTransacao(new Transacao("Supermercado", -600.00, LocalDate.of(2025, 7, 11)));*/
	    //FINAL DADOS DE TESTE!
	    
	    TimeSeries series = new TimeSeries("Evolução do Saldo");
	    double saldoCumulativo = 0.0;
	    
	    //TESTE! CASO QUEIRAM APRESENTAR PRA CARTAXO, RETIREM O COMENTARIO. 
	    /*contaDeTeste.getTransacoes().sort((t1, t2) -> t1.getData().compareTo(t2.getData()));
	    
	    for (Transacao t : contaDeTeste.getTransacoes()) {
	        saldoCumulativo += t.getValor();
	        series.add(new Day(t.getData().getDayOfMonth(), t.getData().getMonthValue(), t.getData().getYear()), saldoCumulativo);
	    }*/
	    //TESTE!
	    
	    TimeSeriesCollection dataset = new TimeSeriesCollection();
	    dataset.addSeries(series);

	  
	    JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Evolução", 
	            "Data",              
	            "Saldo (R$)",        
	            dataset,
	            false,               
	            false,
	            false
	    );

	    
	    XYPlot plot = chart.getXYPlot(); 
	    plot.clearDomainMarkers(); 
	    
	    if (metaSelecionada != null) {
	        
	        ValueMarker marker = new ValueMarker(metaSelecionada.getValorAlvo());
	        marker.setPaint(Color.GREEN); 
	        marker.setLabel(metaSelecionada.getNome()); 
	        plot.addRangeMarker(marker); 
	        marker.setLabelAnchor(RectangleAnchor.CENTER);
	    }
	    
	    
	    ChartPanel chartPanel = new ChartPanel(chart);    
	    
	    graficoPanel.removeAll(); 
	    graficoPanel.setLayout(new BorderLayout()); 
	    graficoPanel.add(chartPanel, BorderLayout.CENTER); 
	    
	    graficoPanel.revalidate(); 
	    graficoPanel.repaint(); 
	}
}