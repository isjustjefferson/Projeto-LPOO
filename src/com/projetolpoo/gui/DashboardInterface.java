package com.projetolpoo.gui;

import com.projetolpoo.business.AccountController;
import com.projetolpoo.business.MetaController;
import com.projetolpoo.business.UserController;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.border.LineBorder;
import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Meta;
import com.projetolpoo.entities.Transacao;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;

public class DashboardInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton addRecVarBttn;
    private JButton addDespVarBttn;
    private JComboBox<Object> comboBoxMetas;
    private JPanel graficoPanel;
    private RoundPanel profilePhotoPanel;
    private Account contaDoUsuario;
    private JLabel valorSaldoLabel;
    private JLabel valorRecFixasLabel;
    private JLabel valorRecVarLabel;
    private JLabel valorDespFixasLabel;
    private JLabel valorDespVarLabel;
    private final UserController userController = UserController.getInstanceUserController();
    private final AccountController accountController = new AccountController();
        

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

    public DashboardInterface() {
	this.contaDoUsuario = accountController.getAccountInstance();
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

    carregarFotoPerfil();	
		
	escolherFotoBtn.addActionListener((ActionEvent e) -> {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Selecionar imagem de perfil");
	fileChooser.setAcceptAllFileFilterUsed(false);
	fileChooser.addChoosableFileFilter(
	    new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

	int result = fileChooser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
            try {
		ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                userController.registraImagem(icon);
                carregarFotoPerfil();
            } catch (Exception ex) {
		JOptionPane.showMessageDialog(this,
                        "Erro ao carregar imagem: " + ex.getMessage());
                }
            }
        });
		
	JPanel functionsDashboard = new JPanel();
	functionsDashboard.setBorder(new LineBorder(new Color(0, 0, 0)));
	functionsDashboard.setBounds(10, 120, 100, 310);
	contentPane.add(functionsDashboard);
	functionsDashboard.setLayout(null);
				
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
	    JOptionPane.showMessageDialog(this, 
                "Você atingiu o limite máximo de 5 metas.", "Limite Atingido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        AdicionarMetaDialog dialog = new AdicionarMetaDialog(this);
	dialog.setVisible(true);
	Meta metaCriada = dialog.getNovaMeta();
	if (metaCriada != null) {
	    contaDoUsuario.adicionarMeta(metaCriada);
	    atualizarMetasComboBox(); 
	    comboBoxMetas.setSelectedItem(metaCriada);
	    JOptionPane.showMessageDialog(this, 
                    "Meta '" + metaCriada.getNome() + "' adicionada com sucesso!");
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
                    MetaController metaController = new MetaController();
                    metaController.removeMeta(metaParaRemover);
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
        perfilBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            PerfilConfigDialog perfilDialog = new PerfilConfigDialog(DashboardInterface.this, userController);
            perfilDialog.setVisible(true);
            carregarFotoPerfil();
        }
    });
	perfilBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	perfilBtn.setBounds(10, 202, 85, 21);
	functionsDashboardPanel.add(perfilBtn);
	
	JPanel bemVindoPanel = new JPanel();
	bemVindoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
	bemVindoPanel.setBounds(120, 10, 460, 100);
	contentPane.add(bemVindoPanel);
	bemVindoPanel.setLayout(null);
		
	JLabel bemVindoLabel = new JLabel("Olá! Bem-vindo ao");
	bemVindoLabel.setFont(new Font("Dialog", Font.BOLD, 26));
	bemVindoLabel.setBounds(69, 10, 254, 37);
	bemVindoPanel.add(bemVindoLabel);
	
	JLabel lblJuliusFinances = new JLabel("Julius Finance's");
	lblJuliusFinances.setFont(new Font("Dialog", Font.BOLD, 26));
	lblJuliusFinances.setBounds(123, 57, 214, 33);
	bemVindoPanel.add(lblJuliusFinances);
		
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
		
	valorSaldoLabel = new JLabel(""); 
	valorSaldoLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	valorSaldoLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	valorSaldoLabel.setBounds(10, 41, 438, 33);
	saldoAtualPanel.add(valorSaldoLabel);
	
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
	
	addRecVarBttn = new JButton("+");
	addRecVarBttn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            chamarDialogoTransacao(true, "Variável");
        }
	});

        addRecVarBttn.setMargin(new Insets(2, 2, 2, 2));
	addRecVarBttn.setPreferredSize(new Dimension(1, 1));
	addRecVarBttn.setBounds(407, 207, 27, 21);
	receitasPanel.add(addRecVarBttn);
		
	JLabel recFixasLabel = new JLabel("Fixas:");
	recFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	recFixasLabel.setBounds(10, 59, 80, 29);
	receitasPanel.add(recFixasLabel);
		
	valorRecFixasLabel = new JLabel("");
	valorRecFixasLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	valorRecFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	valorRecFixasLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	valorRecFixasLabel.setBounds(50, 94, 334, 44);
	receitasPanel.add(valorRecFixasLabel);

    JButton addRecFixaBttn = new JButton("+");
    addRecFixaBttn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            chamarDialogoTransacao(true, "Fixo");
        }
    });
    addRecFixaBttn.setMargin(new Insets(2, 2, 2, 2));
    addRecFixaBttn.setPreferredSize(new Dimension(1, 1));
    addRecFixaBttn.setBounds(407, 115, 27, 21);
    receitasPanel.add(addRecFixaBttn);
		
	JLabel recVarLabel = new JLabel("Variáveis:");
	recVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	recVarLabel.setBounds(10, 150, 119, 29);
	receitasPanel.add(recVarLabel);
		
	valorRecVarLabel = new JLabel("");
	valorRecVarLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	valorRecVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	valorRecVarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
		
	addDespVarBttn = new JButton("+");
	addDespVarBttn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            chamarDialogoTransacao(false, "Variável");
        }
	});
	
    addDespVarBttn.setPreferredSize(new Dimension(1, 1));
	addDespVarBttn.setMargin(new Insets(2, 2, 2, 2));
    addDespVarBttn.setBounds(407, 207, 27, 21);
	despesasPanel.add(addDespVarBttn);
		
    JLabel despFixasLabel = new JLabel("Fixas:");
	despFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
    despFixasLabel.setBounds(12, 59, 80, 29);
	despesasPanel.add(despFixasLabel);
		
    valorDespFixasLabel = new JLabel("");
	valorDespFixasLabel.setFont(new Font("Dialog", Font.BOLD, 25));
    valorDespFixasLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	valorDespFixasLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
    valorDespFixasLabel.setBounds(50, 94, 334, 44);
	despesasPanel.add(valorDespFixasLabel);

    JButton addDespFixaBttn = new JButton("+");
    addDespFixaBttn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            chamarDialogoTransacao(false, "Fixo");
        }
    });
    addDespFixaBttn.setPreferredSize(new Dimension(1, 1));
    addDespFixaBttn.setMargin(new Insets(2, 2, 2, 2));
    addDespFixaBttn.setBounds(407, 115, 27, 21);
    despesasPanel.add(addDespFixaBttn);
		
    JLabel despVarLabel = new JLabel("Variáveis:");
    despVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	despVarLabel.setBounds(12, 150, 119, 29);
    despesasPanel.add(despVarLabel);
		
	valorDespVarLabel = new JLabel("");
    valorDespVarLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	valorDespVarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    valorDespVarLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
    valorDespVarLabel.setBounds(50, 186, 334, 44);
	despesasPanel.add(valorDespVarLabel);
		
    atualizarMetasComboBox();
    atualizarValoresFinanceiros();
	desenharGrafico(null);
		
    }
    
    private void chamarDialogoTransacao(boolean isReceita, String tipoPadrao) {
        AdicionarTransacaoDialog dialog = new AdicionarTransacaoDialog(this, isReceita, contaDoUsuario, tipoPadrao);
        dialog.setVisible(true);

        Transacao novaTransacao = dialog.getNovaTransacao();

        if (novaTransacao != null) {
            contaDoUsuario.getTransacoes().add(novaTransacao);
            String tipo = isReceita ? "Receita" : "Despesa";
            JOptionPane.showMessageDialog(DashboardInterface.this, tipo + " adicionada com sucesso!");
        }

        atualizarValoresFinanceiros();
        Object itemSelecionado = comboBoxMetas.getSelectedItem();
        if (itemSelecionado instanceof Meta) {
            desenharGrafico((Meta) itemSelecionado);
        } else {
            desenharGrafico(null);
        }
    }

    private void atualizarValoresFinanceiros() {
        double receitasFixas = 0;
    	double receitasVariaveis = 0;
    	double despesasFixas = 0;
        double despesasVariaveis = 0;
        double saldoCalculado = 0;

        for (Transacao t : contaDoUsuario.getTransacoes()) {
            saldoCalculado += t.getValor();
            if (t.getValor() > 0) {
                if (t.isFixo()) {
                    receitasFixas += t.getValor();
                } else {
                    receitasVariaveis += t.getValor();
                }
            } else {
                if (t.isFixo()) {
                    despesasFixas += t.getValor();
                } else {
                    despesasVariaveis += t.getValor();
                }
            }
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        valorSaldoLabel.setText(currencyFormat.format(saldoCalculado));
        valorRecFixasLabel.setText(currencyFormat.format(receitasFixas));
        valorRecVarLabel.setText(currencyFormat.format(receitasVariaveis));
        valorDespFixasLabel.setText(currencyFormat.format(Math.abs(despesasFixas)));
        valorDespVarLabel.setText(currencyFormat.format(Math.abs(despesasVariaveis)));
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

        List<Transacao> transacoesValidas = contaDoUsuario.getTransacoes().stream()
            .filter(t -> t.getData() != null)
            .collect(Collectors.toList());
            
        transacoesValidas.sort((t1, t2) -> t1.getData().compareTo(t2.getData()));
        
        double saldoCumulativo = 0.0;
        for (Transacao t : transacoesValidas) {
            saldoCumulativo += t.getValor();
            series.addOrUpdate(new Day(t.getData().getDayOfMonth(), t.getData().getMonthValue(), t.getData().getYear()), saldoCumulativo);
        }
	    
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Evolução do Saldo", "Data", "Saldo (R$)", dataset, false, true, false);
        XYPlot plot = chart.getXYPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        yAxis.setNumberFormatOverride(currencyFormat);
        xAxis.setDateFormatOverride(new SimpleDateFormat("dd/MM"));

        if (transacoesValidas.isEmpty()) {
            yAxis.setRange(0.0, 1000.0);
        } else if (transacoesValidas.size() == 1) {
            double valorUnico = transacoesValidas.get(0).getValor();
            yAxis.setRange(0.0, Math.max(100.0, valorUnico * 1.5));
        }

        plot.clearRangeMarkers();
        if (metaSelecionada != null) {
            ValueMarker marker = new ValueMarker(metaSelecionada.getValorAlvo());
            marker.setPaint(Color.GREEN);
            marker.setLabel(String.format("Meta: %s (%s)", metaSelecionada.getNome(), currencyFormat.format(metaSelecionada.getValorAlvo())));
            marker.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
            marker.setLabelAnchor(RectangleAnchor.TOP);
            plot.addRangeMarker(marker);
           
            if (metaSelecionada.getValorAlvo() > yAxis.getUpperBound()) {
                yAxis.setUpperBound(metaSelecionada.getValorAlvo() * 1.2);
            }
            if (metaSelecionada.getValorAlvo() < yAxis.getLowerBound()) {
                yAxis.setLowerBound(Math.max(0.0, metaSelecionada.getValorAlvo() * 0.6));
            }
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        graficoPanel.add(chartPanel);
        graficoPanel.revalidate();
        graficoPanel.repaint();
    }
    
    private void carregarFotoPerfil() {
        try {
            ImageIcon icon = userController.selecionaImagemController(); 
            if (icon != null) {
                Image img = icon.getImage().getScaledInstance(
                    profilePhotoPanel.getWidth(),
                    profilePhotoPanel.getHeight(),
                    Image.SCALE_SMOOTH);
                profilePhotoPanel.setImage(img);
                profilePhotoPanel.repaint();
            }
        } catch (Exception ex){
        }
    }
}