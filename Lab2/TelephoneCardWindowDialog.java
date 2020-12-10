package Lab2;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 16.11.2020r.
 * wersja 2.0
 * 
 * Program: Realizacja okienek za pomoc¹ biblioteki Swing
 * 
 * Zawiera: Klasa reprezentujaca okien dodawania nowej osoby
 */


import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelephoneCardWindowDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 42L;
	
	private TelephoneCard telephone;
	
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
	JComboBox<TypeOfCard> typeField = new JComboBox<TypeOfCard>(TypeOfCard.values()); 
	JTextField priceField = new JTextField(10);
	
	
	JButton OKButton = new JButton("  OK  ");
	JButton CancelButton = new JButton("Cancel");
	
	private TelephoneCardWindowDialog(Window RefToSuperClass, TelephoneCard telephone) {
		super(RefToSuperClass, Dialog.ModalityType.DOCUMENT_MODAL);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(230, 280);
		setLocationRelativeTo(RefToSuperClass);
		
		this.telephone = telephone;	
		
		if(telephone == null) {
			setTitle("Nowa karta");
		}else {
				setTitle(telephone.toString());
				titleField.setText(telephone.getTitle());
				seriesField.setText(telephone.getSeries());
				productionDataField.setText("" + telephone.getProductionData());
				pulsesField.setText("" + telephone.getPulses());
				typeField.setSelectedItem(telephone.getType());
				priceField.setText("" + telephone.getPrice());
			}
		
		OKButton.addActionListener(this);
		CancelButton.addActionListener(this);
		
		JPanel panel = new JPanel();
			
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
		
		panel.add(OKButton);
		panel.add(CancelButton);
		
		setContentPane(panel);
		
		setVisible(true);
	}
	
	public static TelephoneCard creativeTelephoneCard(Window win) {
		TelephoneCardWindowDialog temp = new TelephoneCardWindowDialog(win, null);
		return temp.telephone;
	}
	
	public static void changeTelephoneCard(Window win, TelephoneCard card) {
		new TelephoneCardWindowDialog(win, card);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object event = ev.getSource();
		
		if(event == OKButton) {
			try {
				if(telephone == null) {
					telephone = new TelephoneCard(titleField.getText(), seriesField.getText());
				}else {
					telephone.setTitle(titleField.getText());
					telephone.setSeries(seriesField.getText());
				}
				telephone.setProductionData(productionDataField.getText());
				telephone.setPulses(pulsesField.getText());
				telephone.setType((TypeOfCard) typeField.getSelectedItem());
				telephone.setPrice(priceField.getText());
				
				dispose();
			}catch(TelephoneCardException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(event== CancelButton) {
			dispose();
		}
	}
}

