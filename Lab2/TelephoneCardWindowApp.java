package Lab2;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 16.11.2020r.
 * wersja 2.0
 * 
 * Program: Realizacja okienek za pomoc¹ biblioteki Swing
 * 
 * Zawiera: Klasa reprezentujaca g³ówne okno wraz z g³ówna pêtla
 * 		    z dodatkowymi zadniami dla chetnych 3,4,5.
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelephoneCardWindowApp extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 47l;
	
	private static final String HELLO_MES =
			"Autor: Pawe³ Ró¿añski\n" +
			"numer indeksu: 252772\n" +
			"Z dodatkowymi æwiczeniami dla chetnych 3,4,5\n" + 
			"Data: 16.11.2020r.\n";
	
	public static void main(String[] args) {
		new TelephoneCardWindowApp();
	}
	
	private TelephoneCard card;
	
	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	JLabel titleLabel          = new JLabel("         Tytu³: ");
	JLabel seriesLabel         = new JLabel("         Seria: ");
	JLabel productionDataLabel = new JLabel("Data produkcji: ");
	JLabel pulsesLabel         = new JLabel("       Impulsy: ");
	JLabel typeLabel           = new JLabel("           Typ: ");
	JLabel priceLabel          = new JLabel("          Cena: ");
	
	JTextField titleField = new JTextField(10);
	JTextField seriesField = new JTextField(10);
	JTextField productionDataField = new JTextField(10);
	JTextField pulsesField = new JTextField(10);
	JTextField typeField = new JTextField(10);
	JTextField priceField = new JTextField(10);
	
	JButton newCardButton = new JButton("Nowa Karta Telefoniczna");
	JButton editCardButton = new JButton("Zmieñ dane Karty");
	JButton SaveToFileButton = new JButton("Zapisz dane do pliku");
	JButton LoadFromFileButton = new JButton("Odczytaj dane z pliku");
	JButton DeleteCardButton = new JButton("Usun Karte Telefoniczna");
	JButton CardInfoButton = new JButton("Informacje o programie i autorze");
	JButton exitProgramButton = new JButton("Zakoñcz");
	JButton SaveSerializationButton = new JButton("Zapisz Serializacja");
	JButton LoadSerializationButton = new JButton("Odczytaj Serializacja");
	
	JMenuItem newCardJMenuItem = new JMenuItem("Nowa Karta Telefoniczna");
	JMenuItem editCardJMenuItem = new JMenuItem("Zmieñ dane Karty");
	JMenuItem SaveToFileJMenuItem = new JMenuItem("Zapisz dane do pliku");
	JMenuItem LoadFromFileJMenuItem = new JMenuItem("Odczytaj dane z pliku");
	JMenuItem DeleteCardJMenuItem = new JMenuItem("Usun Karte Telefoniczna");
	JMenuItem CardInfoJMenuItem = new JMenuItem("Informacje o programie i autorze");
	JMenuItem exitProgramJMenuItem = new JMenuItem("Zakoñcz");
	JMenuItem SaveSerializationJMenuItem = new JMenuItem("Zapisz Serializacja");
	JMenuItem LoadSerializationJMenuItem = new JMenuItem("Odczytaj Serializacja");
	
	
	public TelephoneCardWindowApp() {
		
		setTitle("TelephoneCardWindowApp");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550,550);
		setResizable(false);
		setLocationRelativeTo(null);
		
		titleLabel.setFont(font);
		seriesLabel.setFont(font);
		productionDataLabel.setFont(font);
		pulsesLabel.setFont(font);
		typeLabel.setFont(font);
		priceLabel.setFont(font);
		
		newCardButton.addActionListener(this);
		editCardButton.addActionListener(this);
		SaveToFileButton.addActionListener(this);
		LoadFromFileButton.addActionListener(this);
		DeleteCardButton.addActionListener(this);
		CardInfoButton.addActionListener(this);
		exitProgramButton.addActionListener(this);
		SaveSerializationButton.addActionListener(this);
		LoadSerializationButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		JMenu file = new JMenu("MENU");
		jmb.add(file);
		
		file.add(newCardJMenuItem);
		file.addSeparator();
		file.add(editCardJMenuItem);
		file.addSeparator();
		file.add(SaveToFileJMenuItem);
		file.addSeparator();
		file.add(LoadFromFileJMenuItem);
		file.addSeparator();
		file.add(DeleteCardJMenuItem);
		file.addSeparator();
		file.add(CardInfoJMenuItem);
		file.addSeparator();
		file.add(SaveSerializationJMenuItem);
		file.addSeparator();
		file.add(LoadSerializationJMenuItem);
		file.addSeparator();
		file.add(exitProgramJMenuItem);
		
		newCardJMenuItem.addActionListener(this);
		editCardJMenuItem.addActionListener(this);
		SaveToFileJMenuItem.addActionListener(this);
		LoadFromFileJMenuItem.addActionListener(this);
		DeleteCardJMenuItem.addActionListener(this);
		CardInfoJMenuItem.addActionListener(this);
		exitProgramJMenuItem.addActionListener(this);
		SaveSerializationJMenuItem.addActionListener(this);
		LoadSerializationJMenuItem.addActionListener(this);
		
		panel.add(titleLabel);
		panel.add(titleField);
		
		panel.add(seriesLabel);
		panel.add(seriesField);
		
		panel.add(productionDataLabel);
		panel.add(productionDataField);
		
		panel.add(pulsesLabel);
		panel.add(pulsesField);
		
		panel.add(typeLabel);
		panel.add(typeField);
		
		panel.add(priceLabel);
		panel.add(priceField);
		
		panel.add(newCardButton);
		panel.add(editCardButton);
		panel.add(SaveToFileButton);
		panel.add(LoadFromFileButton);
		panel.add(DeleteCardButton);
		panel.add(CardInfoButton);
		panel.add(exitProgramButton);
		panel.add(SaveSerializationButton);
		panel.add(LoadSerializationButton);
		
		setContentPane(panel);
		
		ShowTelephoneCard();
		
		setVisible(true);
	}
	
	private void ShowTelephoneCard() {
		if(card==null) {
			titleField.setText("");
			seriesField.setText("");
			productionDataField.setText("");
			pulsesField.setText("");
			typeField.setText("");
			priceField.setText("");
		}else{
			titleField.setText(card.getTitle());
			seriesField.setText(card.getSeries());
			productionDataField.setText("" + card.getProductionData());
			pulsesField.setText("" + card.getPulses());
			typeField.setText("" + card.getType());
			priceField.setText("" + card.getPrice());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev)  {
		Object event = ev.getSource();
		
		try {
			if(event == newCardButton || event == newCardJMenuItem) {
				card = TelephoneCardWindowDialog.creativeTelephoneCard(this);
			}
			if(event == editCardButton || event == editCardJMenuItem) {
				if(card == null)
					throw new TelephoneCardException("Nie stworzona jeszcze osoby");
				TelephoneCardWindowDialog.changeTelephoneCard(this, card);
			}
//Zakomentowe linie jest to zapis zwyk³y przed zmiana na JFileChooser
//			if(event == SaveToFileButton || event == SaveToFileJMenuItem) {
//				String filename = JOptionPane.showInputDialog("Podaj nazwe pliku, w ktorym chcesz zapisywac: ");
//				if(filename == null || filename.equals(""))
//					return;
//				TelephoneCard.SaveDataInFile(filename, card);
//			}
			if(event == SaveToFileButton || event == SaveToFileJMenuItem) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Wybierz miejsce zapisu: ");
					fileChooser.setCurrentDirectory(new File("."));
					int result = fileChooser.showSaveDialog(this);
					if(result == JFileChooser.APPROVE_OPTION) {
						File filetoSave = fileChooser.getSelectedFile();
						FileWriter fw = new FileWriter(filetoSave);
						fw.write(TelephoneCard.SaveDataString(card));
						fw.close();
					}
				}catch(IOException e) {
					throw new TelephoneCardException("Blad zapisu");
				}catch(NullPointerException e) {
					throw new TelephoneCardException("Najpierw podaj dane");
				}
			}
//Zakomentowe linij jest to odczyt zwyk³y przed zmiana na JFileChooser
//			if(event == LoadFromFileButton || event == LoadFromFileJMenuItem) {
//				String filename = JOptionPane.showInputDialog("Podaj nazwe pliku, z ktorego chcesz wczytaæ: ");
//				if(filename == null || filename.equals("")) 
//					return;
//				card = TelephoneCard.UploadDataInFile(filename);
//			}
			if(event == LoadFromFileButton || event == LoadFromFileJMenuItem) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Wybierz plik");
				fileChooser.setCurrentDirectory(new File("."));
				int user = fileChooser.showOpenDialog(this);
				
				if(user == JFileChooser.APPROVE_OPTION) {
					try {
						File selectedfile = fileChooser.getSelectedFile();
						FileReader  filereader = new FileReader(selectedfile.getPath());
						BufferedReader buffreader = new BufferedReader(filereader);
						card = TelephoneCard.UploadData(buffreader);
					}catch(FileNotFoundException e) {
						throw new TelephoneCardException("Blad odczytu");
					}
				}
			}
			if(event == SaveSerializationButton || event == SaveSerializationJMenuItem) {
				String filename = JOptionPane.showInputDialog("Podaj nazwe pliku, w ktorym chcesz zapisywac (Serializacja): ");
				if(filename == null || filename.equals(""))
					return;
				TelephoneCard.writeTelephoneCard(filename, card);
			}
			if(event == LoadSerializationButton || event == LoadSerializationJMenuItem) {
				String filename = JOptionPane.showInputDialog("Podaj nazwe pliku, z ktorego chcesz wczytaæ (Serializacja): ");
				if(filename == null || filename.equals(""))
					return;
				card = TelephoneCard.readTelephoneCard(filename);
			}
			if(event == DeleteCardButton || event == DeleteCardJMenuItem) {
				card = null;
			}
			if(event == CardInfoButton || event == CardInfoJMenuItem) {
				JOptionPane.showMessageDialog(this, HELLO_MES);
			}
			if(event == exitProgramButton || event == exitProgramJMenuItem) {
				System.exit(0);
			}
			
		}catch(TelephoneCardException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
		}
		ShowTelephoneCard();
	}
}
