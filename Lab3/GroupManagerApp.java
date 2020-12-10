package Lab3;

import Lab2.*;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 30.10.2020r.
 * wersja 1.0
 * 
 * Program: Aplikacja okienkowa z GUI, która umozliwia zarzadzanie grupami obiektów typu TelephoneCard
 * 
 * Zawiera: Klase do zazadzania grupami klasy TelephoneCard
 */


public class GroupManagerApp extends JFrame implements ActionListener {

	private List<GroupOfTelephoneCard> list = new ArrayList<GroupOfTelephoneCard>();
	
	private static final long serialVersionUID = 1L;

	private static final String HELLO_MES =
			"Autor: Pawe³ Ró¿añski\n" +
			"numer indeksu: 252772\n" + 
			"Data: 30.11.2020r.\n";
	
	private static final String SAVE_DATA_FILE = "ZAPISANE_GRUPY.BIN";
	
	public static void main(String[] args) {
		new GroupManagerApp();
	}
	
	//wyswietla sie przy zamknieciu
	WindowAdapter windowListener = new WindowAdapter() {
		
		@Override
		public void windowClosed(WindowEvent e) {
			JOptionPane.showMessageDialog(null, "Zakonczenie pracy programu");
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			windowClosed(e);
		}
	};
	
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu menuChangingGroup = new JMenu("Grupy");
	
	JMenuItem menuNewGrp = new JMenuItem("Utwórz grupe");
	JMenuItem menuEditGrp = new JMenuItem("Edytuj grupe");
	JMenuItem menuDeleteGrp = new JMenuItem("Usun grupe");
	JMenuItem menuLoadGrp = new JMenuItem("Wczytaj grupe");
	JMenuItem menuSaveGrp = new JMenuItem("Zapisz grupe");
	
	
	JMenu menuChangingSperGroup = new JMenu("Grupy specjalne");
	
	JMenuItem menuPlusAddGrp = new JMenuItem("Polacz grupy (suma)");
	JMenuItem menuCommonAddGrp = new JMenuItem("Polacz grupy (czesc wspolna)");
	JMenuItem menuDiffAddGrp = new JMenuItem("Polacz grupy (roznica)");
	JMenuItem menuSymDiffAddGrp = new JMenuItem("Polaz grupy (roznica symetryczna)");
	
	
	JMenu menuInfo = new JMenu("Informacje");
	
	JMenuItem menuAutor = new JMenuItem("Autor");
	
	
	
	JButton buttonNewGrp = new JButton("Stworz");
	JButton buttonEditGrp = new JButton("Edytuj");
	JButton buttonDeleteGrp = new JButton("Usuñ");
	JButton buttonLoadGrp = new JButton("Za³aduj");
	JButton buttonSaveGrp = new JButton("Zapisz");
	
	JButton buttonPlusAddGrp = new JButton("Suma");
	JButton buttonCommonAddGrp = new JButton("Iloczyn");
	JButton buttonDiffAddGrp = new JButton("Roznica");
	JButton buttonSymDiffAddGrp = new JButton("Roznica symetryczna");
	
	
	TableList tableList;
	
	public GroupManagerApp() {
		setTitle("Zarzadzanie grupami Kart Telefonicznych");
		setSize(450, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent event) {
				try {
					SaveGroupList(SAVE_DATA_FILE);
					JOptionPane.showMessageDialog(null, "Dane zosta³y zapisane do pliku: " + SAVE_DATA_FILE);
				}catch(TelephoneCardException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				windowClosed(e);
			}
		});
		
		try {
			LoadGroupList(SAVE_DATA_FILE);
			JOptionPane.showMessageDialog(null, "Dane zosta³y wczytane z pliku: " + SAVE_DATA_FILE);
		}catch(TelephoneCardException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
		}
		
		
		setJMenuBar(menuBar);
		
		menuBar.add(menuChangingGroup);
		menuChangingGroup.add(menuNewGrp);
		menuChangingGroup.add(menuEditGrp);
		menuChangingGroup.add(menuDeleteGrp);
		menuChangingGroup.add(menuLoadGrp);
		menuChangingGroup.add(menuSaveGrp);
		
		
		menuBar.add(menuChangingSperGroup);
		menuChangingSperGroup.add(menuPlusAddGrp);
		menuChangingSperGroup.add(menuCommonAddGrp);
		menuChangingSperGroup.add(menuDiffAddGrp);
		menuChangingSperGroup.add(menuSymDiffAddGrp);
		
		
		menuBar.add(menuInfo);
		menuInfo.add(menuAutor);
		
		menuNewGrp.addActionListener(this);
		menuEditGrp.addActionListener(this);
		menuDeleteGrp.addActionListener(this);
		menuLoadGrp.addActionListener(this);
		menuSaveGrp.addActionListener(this);
		menuPlusAddGrp.addActionListener(this);
		menuCommonAddGrp.addActionListener(this);
		menuDiffAddGrp.addActionListener(this);
		menuSymDiffAddGrp.addActionListener(this);
		menuAutor.addActionListener(this);
		
		buttonNewGrp.addActionListener(this);
		buttonEditGrp.addActionListener(this);
		buttonDeleteGrp.addActionListener(this);
		buttonLoadGrp.addActionListener(this);
		buttonSaveGrp.addActionListener(this);
		buttonPlusAddGrp.addActionListener(this);
		buttonCommonAddGrp.addActionListener(this);
		buttonDiffAddGrp.addActionListener(this);
		buttonSymDiffAddGrp.addActionListener(this);
		
		
		tableList = new TableList(list, 400, 250);
		tableList.refreshView();
		
		JPanel panel = new JPanel();
		
		panel.add(tableList);
		panel.add(buttonNewGrp);
		panel.add(buttonEditGrp);
		panel.add(buttonDeleteGrp);
		panel.add(buttonLoadGrp);
		panel.add(buttonSaveGrp);
		panel.add(buttonPlusAddGrp);
		panel.add(buttonCommonAddGrp);
		panel.add(buttonDiffAddGrp);
		panel.add(buttonSymDiffAddGrp);
		
		setContentPane(panel);
		
		setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void LoadGroupList(String filename) throws TelephoneCardException {
		try(ObjectInputStream load = new ObjectInputStream(new FileInputStream(filename))){
			list = (List<GroupOfTelephoneCard>)load.readObject();
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Nie ma takiego pliku: " + filename);
		}catch(Exception e) {
			throw new TelephoneCardException("Blad podczas odczytu z pliku: " + filename);
		}
	}
	
	private void SaveGroupList(String filename) throws TelephoneCardException {
		try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(filename))){
			save.writeObject(list);
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Nie ma takiego pliku: " + filename);
		}catch(Exception e) {
			throw new TelephoneCardException("Blad podczas zapisu do pliku: " + filename);
		}
	}
	
	private GroupOfTelephoneCard selectGroup(Window win, String mess) {
		Object[] groups = list.toArray();
		GroupOfTelephoneCard group = (GroupOfTelephoneCard)JOptionPane.showInputDialog(
				win, mess, "Wybierz grupe", JOptionPane.QUESTION_MESSAGE, null, groups, null);
		return group;	
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		
		try {
			if(source == menuNewGrp || source == buttonNewGrp) {
				GroupOfTelephoneCard group = GroupOfTelephoneCardWindowDialog.createNewGroupOfTelephoneCard(this);
				if(group != null) {
					list.add(group);
				}
			}
			
			if(source == menuEditGrp || source == buttonEditGrp) {
				int index = tableList.getSelectedIndex();
				if(index >= 0) {
					Iterator<GroupOfTelephoneCard> iterator = list.iterator();
					while(index-- > 0) {
						iterator.next();
					}
					GroupOfTelephoneCardWindowDialog.changeGroupOfTelephoneCard(this, iterator.next());
				}
			}
			
			if(source == menuDeleteGrp || source == buttonDeleteGrp) {
				int index = tableList.getSelectedIndex();
				if(index >= 0) {
					Iterator<GroupOfTelephoneCard> iterator = list.iterator();
					while(index-- >= 0) {
						iterator.next();
					}
					iterator.remove();
				}	
			}
			
			if(source == menuSaveGrp || source == buttonSaveGrp) {
				int index = tableList.getSelectedIndex();
				if(index >= 0) {
					Iterator<GroupOfTelephoneCard> iterator = list.iterator();
					while(index-- > 0) {
						iterator.next();
					}
					GroupOfTelephoneCard group = iterator.next();
					
					JFileChooser chooser = new JFileChooser(".");
					int value = chooser.showSaveDialog(this);
					if(value == JFileChooser.APPROVE_OPTION) {
						GroupOfTelephoneCard.SaveDataInFile(chooser.getSelectedFile().getName(), group);
					}
				}
			}
			
			if(source ==  menuLoadGrp || source == buttonLoadGrp) {
				JFileChooser chooser = new JFileChooser(".");
				int value = chooser.showOpenDialog(this);
				if(value == JFileChooser.APPROVE_OPTION) {
					GroupOfTelephoneCard group = GroupOfTelephoneCard.UploadDataInFile(chooser.getSelectedFile().getName());
					list.add(group);
				}
			}
			
			if(source == menuPlusAddGrp || source == buttonPlusAddGrp) {
				String messege1 = 
						"SUMA GRUP\n"+
						"Tworzenie grupy zawierajacej karty z pierszej grupy\n" +
						"oraz wszystkie karty z grupy drugiej.\n" +
						"Wybierz pierwsza grupe: ";
				String messege2 = 
						"SUMA GRUP\n"+
						"Tworzenie grupy zawierajacej karty z pierszej grupy\n" +
						"oraz wszystkie karty z grupy drugiej.\n" +
						"Wybierz druga grupe: ";
				
				GroupOfTelephoneCard group1 = selectGroup(this, messege1);
				if(group1 == null)
					return;
				GroupOfTelephoneCard group2 = selectGroup(this, messege2);
				if(group2 == null)
					return;
				
				list.add(GroupOfTelephoneCard.createGroupUnion(group1, group2));
			}
			
			if(source == menuCommonAddGrp || source == buttonCommonAddGrp) {
				String messege1 = 
						"ILOCZYN GRUP\n"+
						"Tworzenie grupy kart, ktore naleza do pierszej grupy\n" +
						"jak i zarówno do drugiej grupy.\n" +
						"Wybierz pierwsza grupe: ";
				String messege2 = 
						"ILOCZYN GRUP\n"+
						"Tworzenie grupy kart, ktore naleza do pierszej grupy\n" +
						"jak i zarówno do drugiej grupy.\n" +
						"Wybierz druga grupe: ";
				GroupOfTelephoneCard group1 = selectGroup(this, messege1);
				if(group1 == null)
					return;
				GroupOfTelephoneCard group2 = selectGroup(this, messege2);
				if(group2 == null)
					return;
				
				list.add(GroupOfTelephoneCard.createGroupIntersection(group1, group2));
			}
			
			if(source == menuDiffAddGrp || source == buttonDiffAddGrp) {
				String messege1 = 
						"RÓZNICA GRUP\n"+
						"Tworzenie grupy kart, ktore naleza do pierszej grupy\n" +
						"i nie naleza do drugiej grupy.\n" +
						"Wybierz pierwsza grupe: ";
				String messege2 = 
						"RÓZNICA GRUP\n"+
						"Tworzenie grupy kart, ktore naleza do pierszej grupy\n" +
						"i nie naleza do drugiej grupy.\n" +
						"Wybierz drugiej grupe: ";
				
				GroupOfTelephoneCard group1 = selectGroup(this, messege1);
				if(group1 == null)
					return;
				GroupOfTelephoneCard group2 = selectGroup(this, messege2);
				if(group2 == null)
					return;
				
				list.add(GroupOfTelephoneCard.createGroupDifference(group1, group2));
			}
			
			if(source == menuSymDiffAddGrp || source == buttonSymDiffAddGrp) {
				String messege1 = 
						"RÓZNICA SYMETRYCZNA GRUP\n"+
						"Tworzenie grupy kart, ktore naleza tylko do jednej\n" +
						"z dwóch grupy\n" +
						"Wybierz pierwsza grupe: ";
				String messege2 = 
						"RÓZNICA SYMETRYCZNA GRUP\n"+
						"Tworzenie grupy kart, ktore naleza tylko do jednej\n" +
						"z dwóch grupy\n" +
						"Wybierz druga grupe: ";
				
				GroupOfTelephoneCard group1 = selectGroup(this, messege1);
				if(group1 == null)
					return;
				GroupOfTelephoneCard group2 = selectGroup(this, messege2);
				if(group2 == null)
					return;
				
				list.add(GroupOfTelephoneCard.createGroupSymmetricDiff(group1, group2));
			}
			
			if(source == menuAutor) {
				JOptionPane.showMessageDialog(this, HELLO_MES);
			}
			
		}catch (TelephoneCardException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Blad", JOptionPane.ERROR_MESSAGE);
		}

		this.tableList.refreshView();
	}

}
//Klasa dla wyœwietlania listy grup w tabeli w oknie
class TableList extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
	private List<GroupOfTelephoneCard> listOfGroup;
	private JTable jtable;
	private DefaultTableModel tableModel;
	
	public TableList(List<GroupOfTelephoneCard> list, int w, int h) {
		this.listOfGroup = list;
		setPreferredSize(new Dimension(w, h));
		setBorder(BorderFactory.createTitledBorder("Lista grup:"));
		
		String[] tableHead = { "Nazwa", "Typ kolekcji", "Liczba osób" };
		tableModel = new DefaultTableModel(tableHead, 0);
		jtable = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable.setRowSelectionAllowed(true);
		setViewportView(jtable);
	}
	
	void refreshView() {
		tableModel.setRowCount(0);
		for(GroupOfTelephoneCard group : listOfGroup) {
			if( group != null) {
				String[] row = { group.getName(), group.getType().toString(), "" + group.size() };
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
