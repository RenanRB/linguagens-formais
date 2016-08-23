package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Grid;
import util.NumberedBorder;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private Grid g = new Grid().loadDefaultGrid();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 864, 187);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBorder(new NumberedBorder());
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Analisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				model.setNumRows(0);
				
				String[] lineInformation = new String[10];
				String[] wrapByLine = textArea.getText().split("\n");
				int lineNumber = 1;
				
				for (String line : wrapByLine) {
					
					String[] t = line.split(" |	");
					for (String string : t) {
						lineInformation[0] = lineNumber+"";
						lineInformation[1] = g.validateWord(string);
						lineInformation[2] = string;
						lineInformation[3] = g.getSequence();
						model.addRow(lineInformation);
					}
					lineNumber = lineNumber + 1;
				}
			}
		});
		btnNewButton.setBounds(10, 216, 89, 23);
		contentPane.add(btnNewButton);
		
		String[] colunas = new String[]{"Linha","Resultado","Sequência","Reconhecimento"};
		 
		JTable tabela = new JTable();
		model = new DefaultTableModel(null , colunas );
		tabela.setModel(model);
//		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setMaxWidth(45);
//		tabela.getColumnModel().getColumn(1).setMaxWidth(170);
//		tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
//		tabela.getColumnModel().getColumn(3).setPreferredWidth(250);
		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setSize(864, 173);
		scroll.setLocation(10, 269);
		scroll.setViewportView(tabela);
		getContentPane().add(scroll);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanFields();
			}
		});
		btnLimpar.setBounds(246, 216, 89, 23);
		contentPane.add(btnLimpar);
		Icon icone = new ImageIcon("C:/Users/User/Pictures/Camera Roll/3.png");
		JButton btnEquipe = new JButton("Equipe", icone);
		btnEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane,   "**************************************\n"
						                                   + "*  Desenvolvedores do Projeto     *\n"
						                                   + "**************************************\n"
						                                   + "*  Geovani Finoti Leitão                   *\n"
						                                   + "*  Renan Rafael Bertoldo                *\n"
						                                   + "**************************************");
			}
		});
		btnEquipe.setBackground(new Color(204, 204, 255));
		btnEquipe.setBounds(534, 216, 175, 23);
		contentPane.add(btnEquipe);
	}
	private void cleanFields(){
		textArea.setText("");
		model.setNumRows(0);
	}
}
