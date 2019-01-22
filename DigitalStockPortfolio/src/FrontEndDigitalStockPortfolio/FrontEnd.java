package FrontEndDigitalStockPortfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class FrontEnd {
	public TestApiDigitalStockPortfolioMichel tasp;

	private ArrayList<String> symbolList = new ArrayList<String>();

	// Default Konstuktor
	private FrontEnd() {

		// Read all Symbols
		Reader in;
		try {
			in = new FileReader("companylist.txt");
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
			for (CSVRecord record : records) {
				symbolList.add(record.get(0));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Hauptkontruktor
	public FrontEnd(TestApiDigitalStockPortfolioMichel tasp) {
		this();
		this.tasp = tasp;

		// Frame
		JFrame frame = new JFrame("Digital Stock Portfolio");
		frame.setPreferredSize(new Dimension(700, 900));

		// Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();

		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);

		southPanel.setLayout(new FlowLayout());

		JTextArea textArea = new JTextArea(5, 30);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(600, 600));

		southPanel.add(scrollPane);

		// JLabels
		JLabel title = new JLabel("Digital Stock Portfolio");
		title.setFont(title.getFont().deriveFont(30.0f));
		title.setForeground(Color.BLUE);

		JLabel labelTransactionAmount = new JLabel("Transaktionsbetrag eingeben:");

		JLabel labelStockSelection = new JLabel("Aktie auswaehlen:");

		JLabel labelPriceLimit = new JLabel("Preis Limite eingeben");

		JLabel labelJobID = new JLabel("Job ID eingeben");

		// JButtons
		JButton buttonGetAccountBalance = new JButton();
		buttonGetAccountBalance.setText("Saldo Konto abfragen");

		JButton buttonDeposit = new JButton();
		buttonDeposit.setText("einzahlen");

		JButton buttonDisburse = new JButton();
		buttonDisburse.setText("auszahlen");

		JButton buttonGetMarketPrice = new JButton();
		buttonGetMarketPrice.setText("Marktpreis abfragen");

		JButton buttonCalculateWinOrLoss = new JButton();
		buttonCalculateWinOrLoss.setText("Calculate Win/Loss");

		JButton buttonBuyShare = new JButton();
		buttonBuyShare.setText("Aktie kaufen");

		JButton buttonSellShare = new JButton();
		buttonSellShare.setText("Aktie verkaufen");

		JButton buttonDefinedLimitBuy = new JButton();
		buttonDefinedLimitBuy.setText("Aktie kaufen mit Limite");

		JButton buttonDefinedLimitSell = new JButton();
		buttonDefinedLimitSell.setText("Aktie verkaufen mit Limite");

		JButton buttonPrintJobs = new JButton();
		buttonPrintJobs.setText("pendente Jobs abfragen");

		JButton buttonRemoveJobs = new JButton();
		buttonRemoveJobs.setText("pendenter Job loeschen");

		JButton buttonPrintShares = new JButton();
		buttonPrintShares.setText("Depot");

		// TextFields
		JTextField textFieldTransactionAmount = new JTextField(10);
		JTextField textFieldDefinedLimitPrice = new JTextField(10);
		JTextField textFieldRemoveJobs = new JTextField(5);

		// ComboBoxModel
		DefaultComboBoxModel<String> comboBoxModelMarketShares = new DefaultComboBoxModel<String>(
				symbolList.toArray(new String[0]));

		JComboBox<String> comboBoxMarketShares = new JComboBox<String>(comboBoxModelMarketShares);

		// Listener für Buttons
		buttonGetAccountBalance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.getAccountBalance();
			}
		});

		buttonDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldTransactionAmount.getText().equals(null) || textFieldTransactionAmount.getText().equals("")) {
					System.out.println("Bitte im Feld Transaktionsbetrag einen Betrag eingeben!");
				} else {
					double s = Double.parseDouble(textFieldTransactionAmount.getText());
					tasp.deposit(s);
				}
			}
		});

		buttonDisburse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double s = Double.parseDouble(textFieldTransactionAmount.getText());
				tasp.disburse(s);
			}
		});

		buttonCalculateWinOrLoss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.calculateWinOrLoss();
			}
		});

		buttonGetMarketPrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.getMarketPrice(String.valueOf(comboBoxMarketShares.getSelectedItem()));
			}
		});

		buttonBuyShare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.buyShare(String.valueOf(comboBoxMarketShares.getSelectedItem()));
			}
		});

		buttonSellShare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.sellShare(String.valueOf(comboBoxMarketShares.getSelectedItem()));

			}
		});

		buttonDefinedLimitBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double s = Double.parseDouble(textFieldDefinedLimitPrice.getText());
				tasp.definedLimitBuy(String.valueOf(comboBoxMarketShares.getSelectedItem()), s);
			}
		});

		buttonDefinedLimitSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double s = Double.parseDouble(textFieldDefinedLimitPrice.getText());
				tasp.definedLimitSell(String.valueOf(comboBoxMarketShares.getSelectedItem()), s);
			}
		});

		buttonPrintJobs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.printJobs();
			}
		});

		buttonRemoveJobs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int s = Integer.parseInt(textFieldRemoveJobs.getText());
				tasp.removeJobs(s);
			}
		});

		buttonPrintShares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tasp.printShares();
			}
		});

		// NorthPanel
		northPanel.add(title);

		// CenterPanel
		centerPanel.add(buttonGetAccountBalance);

		centerPanel.add(labelTransactionAmount);
		centerPanel.add(textFieldTransactionAmount);
		centerPanel.add(buttonDeposit);
		centerPanel.add(buttonDisburse);

		centerPanel.add(labelStockSelection);
		centerPanel.add(comboBoxMarketShares);

		centerPanel.add(buttonGetMarketPrice);
		centerPanel.add(buttonBuyShare);
		centerPanel.add(buttonSellShare);

		centerPanel.add(labelPriceLimit);
		centerPanel.add(textFieldDefinedLimitPrice);
		centerPanel.add(buttonDefinedLimitBuy);
		centerPanel.add(buttonDefinedLimitSell);

		centerPanel.add(buttonPrintJobs);

		centerPanel.add(labelJobID);
		centerPanel.add(textFieldRemoveJobs);
		centerPanel.add(buttonRemoveJobs);

		centerPanel.add(buttonPrintShares);
		centerPanel.add(buttonCalculateWinOrLoss);

		// position
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
