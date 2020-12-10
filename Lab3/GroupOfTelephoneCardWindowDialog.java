package Lab3;

import Lab2.*;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 30.10.2020r.
 * wersja 1.2
 * 
 * Program: Aplikacja okienkowa z GUI, która umozliwia zarzadzanie grupami obiektów typu TelephoneCard
 * 
 * Zawiera: Klase do zazadzania poszczegó³nymi kartami w grupie i operacje na nich
 */

public class GroupOfTelephoneCardWindowDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String HELLO_MES =
			"Autor: Pawe³ Ró¿añski\n" +
			"numer indeksu: 252772\n" + 
			"Data: 30.11.2020r.\n" +
			"Program do edytowania grup kart\n";
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu menuChangingCard = new JMenu("Lista kart");
	
	JMenuItem menuAddCard = new JMenuItem("Dodaj now¹ kartê");
	JMenuItem menuEditCard = new JMenuItem("Edytuj karte");
	JMenuItem menuDeleteCard = new JMenuItem("Usuñ karte");
	JMenuItem menuSaveCard = new JMenuItem("Zapisz karte do pliku");
	JMenuItem menuLoadCard = new JMenuItem("Wczytaj karte z pliku");
	
	
	JMenu menuSortCard = new JMenu("Posortuj");
	
	JMenuItem menuSortTitle = new JMenuItem("Sortuj wed³ug tytu³u (alfabetycznie)");
	JMenuItem menuSortSeries = new JMenuItem("Sortuj wed³ug serii (alfabetycznie)");
	JMenuItem menuSortProductionDate = new JMenuItem("Sortuj wed³ug daty produkcji");
	JMenuItem menuSortPulses = new JMenuItem("Sortuj wed³ug iloœci impulsów");
	JMenuItem menuSortType = new JMenuItem("Sortuj wed³ug typu");
	JMenuItem menuSortPrice = new JMenuItem("Sortuj wed³ug ceny");
	
	JMenu menuEditGroupPreferences = new JMenu("W³asciwosci");
	
	JMenuItem menuChangeNameOfCollection = new JMenuItem("Zmieñ nazwê kolekcji");
	JMenuItem menuChangeTypeOfCollection = new JMenuItem("Zmien typ kolekcji");
	
	JMenu menuInfo = new JMenu("Informacje");
	
	JMenuItem menuAutor = new JMenuItem("Autor");
	
	JButton buttonAddCard = new JButton("Dodaj now¹ kartê");
	JButton buttonEditCard = new JButton("Edytuj karte");
	JButton buttonDeleteCard = new JButton("Usuñ karte");
	JButton buttonSaveCard = new JButton("Zapisz karte do pliku");
	JButton buttonLoadCard = new JButton("Wczytaj karte z pliku");
	
	JLabel labelNameOfCollection = new JLabel("Nazwa grupy: ");
	JLabel labelTypeOfCollection = new JLabel("Rodzaj kolekcji: ");
	
	JLabel labelDisplayName = new JLabel("---");
	JLabel labelDisplayType = new JLabel("---");
	
	private TableCard tableCard;
	private GroupOfTelephoneCard group;
	
	public static void changeGroupOfTelephoneCard(Window RefToSuperClass, GroupOfTelephoneCard tempgroup) {
		new GroupOfTelephoneCardWindowDialog(RefToSuperClass, tempgroup);
	}
	
	private GroupOfTelephoneCardWindowDialog(Window RefToSuperClass, GroupOfTelephoneCard groupFromRef) {
		super(RefToSuperClass, Dialog.ModalityType.DOCUMENT_MODAL);
		
		setTitle("Modyfikacja grupy kart");
		setSize(400,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(RefToSuperClass);
		
		setJMenuBar(menuBar);
		
		menuBar.add(menuChangingCard);
		
		menuChangingCard.add(menuAddCard);
		menuChangingCard.add(menuEditCard);
		menuChangingCard.add(menuDeleteCard);
		menuChangingCard.add(menuSaveCard);
		menuChangingCard.add(menuLoadCard);
		
		menuBar.add(menuSortCard);
		
		menuSortCard.add(menuSortTitle);
		menuSortCard.add(menuSortSeries);
		menuSortCard.add(menuSortProductionDate);
		menuSortCard.add(menuSortPulses);
		menuSortCard.add(menuSortType);
		menuSortCard.add(menuSortPrice);
		
		menuBar.add(menuEditGroupPreferences);
		
		menuEditGroupPreferences.add(menuChangeNameOfCollection);
		menuEditGroupPreferences.add(menuChangeTypeOfCollection);
		
		menuBar.add(menuInfo);
		
		menuInfo.add(menuAutor);
		
		menuAddCard.addActionListener(this);
		menuEditCard.addActionListener(this);
		menuDeleteCard.addActionListener(this);
		menuSaveCard.addActionListener(this);
		menuLoadCard.addActionListener(this);
		menuSortTitle.addActionListener(this);
		menuSortSeries.addActionListener(this);
		menuSortProductionDate.addActionListener(this);
		menuSortPulses.addActionListener(this);
		menuSortType.addActionListener(this);
		menuSortPrice.addActionListener(this);	
		menuChangeNameOfCollection.addActionListener(this);	
		menuChangeTypeOfCollection.addActionListener(this);
		menuAutor.addActionListener(this);
		
		buttonAddCard.addActionListener(this);
		buttonEditCard.addActionListener(this);
		buttonDeleteCard.addActionListener(this);
		buttonSaveCard.addActionListener(this);
		buttonLoadCard.addActionListener(this);
		
		
		this.group = groupFromRef;
		
		tableCard = new TableCard(this.group, 350, 200);
		tableCard.refreshView();
		
		JPanel panel = new JPanel();
		
		panel.add(labelNameOfCollection);
		panel.add(labelDisplayName);
		
		panel.add(labelTypeOfCollection);
		panel.add(labelDisplayType);
		
		labelDisplayName.setText(group.getName());
		labelDisplayType.setText(group.getType().toString()); 
		
		panel.add(tableCard);
		
		panel.add(buttonAddCard);
		panel.add(buttonEditCard);
		panel.add(buttonDeleteCard);
		panel.add(buttonSaveCard);
		panel.add(buttonLoadCard);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public static GroupOfTelephoneCard createNewGroupOfTelephoneCard(Window parent) {
		GroupOfTelephoneCard tempgroup;
		
		String name = JOptionPane.showInputDialog(parent, "Podaj nazwe grupy kart: ");
		if(name == null || name.equals("")) {
			return null;
		}
		
		Object[] temp = TypeOfGroup.values();
		
		TypeOfGroup type = (TypeOfGroup)JOptionPane.showInputDialog(parent,
				"Wybierz typ kolekcji \n i sposób implementacji:",
				"Zmien typ kolekcji", 3, null, temp, null);
		
		try {
			tempgroup = new GroupOfTelephoneCard(type,name);
		}catch(TelephoneCardException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Blad", 0);
			return null;
		}
		
		GroupOfTelephoneCardWindowDialog newGroup = new GroupOfTelephoneCardWindowDialog(parent, tempgroup);
		return newGroup.group;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Object event = ev.getSource();
		try {
			if(event == buttonAddCard || event == menuAddCard) {
				TelephoneCard card;
				card = TelephoneCardWindowDialog.creativeTelephoneCard(this);
				if(card != null) {
					group.add(card);
				}
			}
			
			if(event == buttonEditCard || event == menuEditCard) {
				int index = tableCard.getSelectedIndex();
				
				if(index >= 0) {
					Iterator<TelephoneCard> iterator = group.iterator();
					while(index-- >0) {
						iterator.next();
					}
					TelephoneCardWindowDialog.changeTelephoneCard(this, iterator.next());
				}
			}
			
			if(event == buttonDeleteCard || event == menuDeleteCard) {
				int index = tableCard.getSelectedIndex();
				if(index >= 0) {
					Iterator<TelephoneCard> iterator = group.iterator();
					while(index-- >=0) {
						iterator.next();
					}
					iterator.remove();
				}
			}
			
			if(event == buttonSaveCard || event == menuSaveCard) {
				int index = tableCard.getSelectedIndex();
				if(index >= 0) {
					Iterator<TelephoneCard> iterator = group.iterator();
					while(index-- >0) {
						iterator.next();
					}
					JFileChooser chooser = new JFileChooser(".");
					int value = chooser.showSaveDialog(this);
					if(value == JFileChooser.APPROVE_OPTION) {
						TelephoneCard.SaveDataInFile(chooser.getSelectedFile().getName(), iterator.next());
					}
				}
			}
			
			if(event == buttonLoadCard || event == menuLoadCard) {
				JFileChooser chooser = new JFileChooser(".");
				int value = chooser.showOpenDialog(this);
				if(value == JFileChooser.APPROVE_OPTION) {
					TelephoneCard card = TelephoneCard.UploadDataInFile(chooser.getSelectedFile().getName());
					group.add(card);
				}
			}
			
			if(event == menuSortTitle) {
				group.sortTitle();
			}
			
			if(event == menuSortSeries) {
				group.sortSeries();
			}
			
			if(event == menuSortProductionDate) {
				group.sortProductionDate();
			}
			
			if(event == menuSortPulses) {
				group.sortPulses();
			}
			
			if(event == menuSortType) {
				group.sortType();
			}
			
			if(event == menuSortPrice) {
				group.sortPrice();
			}
			
			if(event == menuChangeNameOfCollection) {
				String name = JOptionPane.showInputDialog("Podaj now¹ nazwe grupy : ");
				group.setName(name);
				labelDisplayName.setText(group.getName());
			}
	
			if(event == menuChangeTypeOfCollection) {
				Object[] temp = TypeOfGroup.values();
				
				TypeOfGroup type = (TypeOfGroup)JOptionPane.showInputDialog(this,
						"Wybierz typ kolekcji \n i sposób implementacji:",
						"Zmien typ kolekcji", 3, null, temp, null);
				
				group.setType(type);
				labelDisplayType.setText(group.getType().toString()); 
			}
			
			if(event == menuAutor ) {
				JOptionPane.showMessageDialog(this, HELLO_MES);
			}
			
		}catch(TelephoneCardException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
		}
		
		this.tableCard.refreshView();
	}
}

class TableCard extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
	private GroupOfTelephoneCard listOfCard;
	private JTable jtable;
	private DefaultTableModel tableModel;
	
	public TableCard(GroupOfTelephoneCard group, int w, int h) {
		setPreferredSize(new Dimension(w, h));
		setBorder(BorderFactory.createTitledBorder("Lista kart:"));
		this.listOfCard = group;
		
		String[] tableHead = { "Tytu³", "Seria", "Rok produkcji", "Impulsy", " typ", "Cena" };
		this.tableModel = new DefaultTableModel(tableHead, 0);
		this.jtable = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		jtable.setSelectionMode(0);
		jtable.setRowSelectionAllowed(true);
		setViewportView(jtable);
	}
	
	void refreshView() {
		tableModel.setRowCount(0);
		for(TelephoneCard group : listOfCard) {
			if( group != null) {
				String[] row = { group.getTitle(), group.getSeries(),"" + group.getProductionData(), ""
								+ group.getPulses(), group.getType().toString(),"" + group.getPrice() };
				tableModel.addRow(row);
			}
		}
	}
	
	int getSelectedIndex() {
		int index = jtable.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(this, "Nie wybrano grupy", "Blad", JOptionPane.ERROR_MESSAGE);
		}
		return index;
	}
}