package Lab3;

import Lab2.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;


/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 30.10.2020r.
 * wersja 1.0
 * 
 * Program: Aplikacja okienkowa z GUI, która umozliwia zarzadzanie grupami obiektów typu TelephoneCard
 * 
 * Zawiera: Typ enum do przochowywania typów grup oraz Klasê reprezentujaca grupy card telefonicznych
 */

enum TypeOfGroup{
	VECTOR("Lista (klasa Vector)"),
	ARRAY_LIST("Lista (klasa ArrayList)"),
	LINKED_LIST("Lista (klasa LinkedList"),
	TREE_SET("Zbior (klasa TreeSet)"),
	HASH_SET("Zbior (klasa HashSet)");
	
	
	private String typeName;
	
	private TypeOfGroup(String type) {
		this.typeName = type;
	}
	
	@Override
	public String toString() {
		return typeName;
	}
	
	public static TypeOfGroup find(String newtype) {
		for(TypeOfGroup type : values()) {
			if(type.typeName.equals(newtype))
				return type;
		}
		return null;
	}
	
	public Collection<TelephoneCard> createCollection() throws TelephoneCardException{
		switch(this) {
		case VECTOR:		 return new Vector<TelephoneCard>();
		case ARRAY_LIST:	 return new ArrayList<TelephoneCard>();
		case LINKED_LIST:	 return new LinkedList<TelephoneCard>();
		case TREE_SET:		 return new TreeSet<TelephoneCard>();
		case HASH_SET:		 return new HashSet<TelephoneCard>();
		default:			 throw new TelephoneCardException("Wprowadzi³eœ typ kolekcji ktory nie istnieje");
		}
	}
}


public class GroupOfTelephoneCard implements Iterable<TelephoneCard>, Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;
	protected TypeOfGroup type;
	protected Collection<TelephoneCard> collection;
	
	public GroupOfTelephoneCard(TypeOfGroup newtype, String name) throws TelephoneCardException{
		setName(name);
		if(newtype==null)
			throw new TelephoneCardException("Typ grupy nie zosta³ wybrany w poprawny sposób");
		this.type = newtype;
		collection = this.type.createCollection();
	}
	
	public GroupOfTelephoneCard(String newtype, String name) throws TelephoneCardException{
		setName(name);
		TypeOfGroup type = TypeOfGroup.find(newtype);
		if(newtype==null)
			throw new TelephoneCardException("Typ grupy nie zosta³ wybrany w poprawny sposób");
		this.type = type;
		collection = this.type.createCollection();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws TelephoneCardException {
		if((name == null) || name.equals(""))
			throw new TelephoneCardException("Nazwa grupy nie zosta³a podana w prawid³owy sposób");
		this.name = name;
	}
	
	public TypeOfGroup getType() {
		return type;
	}
	
	public void setType(TypeOfGroup type) throws TelephoneCardException {
		if(type == null) {
			throw new TelephoneCardException("Typ kolekcji nie zosta³ wybrany w poprawny sposób");
		}
		if(this.type == type)
			return;
		this.type = type;
		Collection<TelephoneCard> tempcollection = collection;
		collection = type.createCollection();
		for(TelephoneCard card : tempcollection) {
			collection.add(card);
		}
	}
	
	
	public void setType(String newtype) throws TelephoneCardException {
		for(TypeOfGroup type : TypeOfGroup.values()) {
			if(type.toString().equals(newtype)) {
				setType(type);
				return;
			}
		}
		throw new TelephoneCardException("Nazwa typu kolekcji nie zosta³a podana w prawid³owy sposób");
	}
	
	public boolean add(TelephoneCard card) {
		return collection.add(card);
	}
	
	@Override
	public Iterator<TelephoneCard> iterator() {
		return collection.iterator();
	}
	
	public int size() {
		return collection.size();
	}
	
	//Sortowania w zale¿noœci od typu jakis chcemy:
	//1. nazwa
	//2. seria
	//3. rok produkcji
	//4. liczba impulsów
	//5. typ karty
	//6. cena

	public void sortTitle() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
		Collections.sort((List<TelephoneCard>) collection);
	}
	
	public void sortSeries() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
		Collections.sort((List<TelephoneCard>) collection, new Comparator<TelephoneCard>() {
			
			@Override
			public int compare(TelephoneCard card1, TelephoneCard card2) {
				int result = card1.getSeries().compareTo(card2.getSeries());
				
				if(result > 0) {
					return 1;
				}else if(result < 0 ) {
					return -1;
				}else {
					return 0;
				}
			}
		});
	}
	
	public void sortProductionDate() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
			Collections.sort((List<TelephoneCard>) collection, new Comparator<TelephoneCard>() {
			
			@Override
			public int compare(TelephoneCard card1, TelephoneCard card2) {
				if(card1.getProductionData() > card2.getProductionData()) {
					return 1;
				}else if(card1.getProductionData() < card2.getProductionData()) {
					return -1;
				}else {
					return 0;
				}
			}
		});
	}
	
	public void sortPulses() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
		Collections.sort((List<TelephoneCard>) collection, new Comparator<TelephoneCard>() {
			
			@Override
			public int compare(TelephoneCard card1, TelephoneCard card2) {
				if(card1.getPulses() > card2.getPulses()) {
					return 1;
				}else if(card1.getPulses() < card2.getPulses()) {
					return -1;
				}else {
					return 0;
				}
			}
		});
	}
	
	public void sortType() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
		Collections.sort((List<TelephoneCard>) collection, new Comparator<TelephoneCard>() {
			
			@Override
			public int compare(TelephoneCard card1, TelephoneCard card2) {
				return card1.getType().toString().compareTo(card2.getType().toString());
			}
		});
	}
	
	public void sortPrice() throws TelephoneCardException {
		if(type==TypeOfGroup.HASH_SET || type==TypeOfGroup.TREE_SET) {
			throw new TelephoneCardException("Kolekcji bêdace zbiorami nie mog¹ byæ sortowane");
		}
		Collections.sort((List<TelephoneCard>) collection, new Comparator<TelephoneCard>() {
			
			@Override
			public int compare(TelephoneCard card1, TelephoneCard card2) {
				if(card1.getPrice() > card2.getPrice()) {
					return 1;
				}else if(card1.getPrice() < card2.getPrice()) {
					return -1;
				}else {
					return 0;
				}
			}
		});
	}
	
	@Override
	public String toString() {
		return name + "  [" + type + "]";
	}
	
	public static void SaveDataInFile(PrintWriter writer, GroupOfTelephoneCard group) {
		writer.println(group.getName());
		writer.println(group.getType());
		for(TelephoneCard card : group.collection) {
			TelephoneCard.SaveData(writer, card);
		}
	}
	
	public static void SaveDataInFile(String filename, GroupOfTelephoneCard group) throws TelephoneCardException {
		try(PrintWriter writer = new PrintWriter(filename)) {
			SaveDataInFile(writer, group);
		}catch(IOException e) {
			throw new TelephoneCardException("Nie uda³o sie zapisac w pliki: " + filename);
		}
	}
	
	public static GroupOfTelephoneCard UploadDataInFile(BufferedReader reader) throws TelephoneCardException {
		try {
			String name = reader.readLine();
			String type = reader.readLine();
			GroupOfTelephoneCard group = new GroupOfTelephoneCard(type, name);
			String line;
			TelephoneCard card;
			while((line = reader.readLine()) != null) {
				card = TelephoneCard.UploadDataInWindowDialogNew(line);
				System.out.println(line);
				group.collection.add(card);
			}
			return group;	
		}catch(IOException e) {
			throw new TelephoneCardException("Nie uda³o sie odczytaæ z pliku.");
		}
	}
	
	public static GroupOfTelephoneCard UploadDataInFile(String filename) throws TelephoneCardException {
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))){
			return GroupOfTelephoneCard.UploadDataInFile(reader);
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Nie ma takiego pliku: " + filename);
		}catch(IOException e) {
			throw new TelephoneCardException("B³ad podczas odczytu z pliku: " + filename);
		}
	}
	
	public static GroupOfTelephoneCard createGroupUnion(GroupOfTelephoneCard group1, GroupOfTelephoneCard group2) throws TelephoneCardException {
		String name = "(" + group1.getName() + " OR " + group2.getName() + ")";
		TypeOfGroup type;
		if(group1.collection instanceof Set && !(group2.collection instanceof Set)) {
			type = group2.type;
		}else {
			type = group1.type;
		}
		GroupOfTelephoneCard group = new GroupOfTelephoneCard(type, name);
		group.collection.addAll(group1.collection);
		group.collection.addAll(group2.collection);
		return group;
	}
	
	public static GroupOfTelephoneCard createGroupIntersection(GroupOfTelephoneCard group1, GroupOfTelephoneCard group2) throws TelephoneCardException {
		String name = "(" + group1.getName() + " AND " + group2.getName() + ")";
		TypeOfGroup type;
		if(group1.collection instanceof Set && !(group2.collection instanceof Set)) {
			type = group2.type;
		}else {
			type = group1.type;
		}
		GroupOfTelephoneCard group = new GroupOfTelephoneCard(type, name);
		for(TelephoneCard tempgroup1 : group1.collection) {
			for(TelephoneCard tempgroup2 : group2.collection) {
				if(tempgroup1.equals(tempgroup2)) {
					group.add(tempgroup1);
				}
			}
		}
		return group;
	}
	
	public static GroupOfTelephoneCard createGroupDifference(GroupOfTelephoneCard group1, GroupOfTelephoneCard group2) throws TelephoneCardException {
		String name = "(" + group1.getName() + " SUB " + group2.getName() + ")";
		TypeOfGroup type;
		if(group1.collection instanceof Set && !(group2.collection instanceof Set)) {
			type = group2.type;
		}else {
			type = group1.type;
		}
		GroupOfTelephoneCard group = new GroupOfTelephoneCard(type, name);
		for(TelephoneCard tempgroup1 : group1.collection) {
			for(TelephoneCard tempgroup2 : group2.collection) {
				if(!tempgroup1.equals(tempgroup2)) {
					group.add(tempgroup1);
				}
			}
		}
		return group;
	}
	
	public static GroupOfTelephoneCard createGroupSymmetricDiff(GroupOfTelephoneCard group1, GroupOfTelephoneCard group2) throws TelephoneCardException {
		String name = "(" + group1.getName() + " XOR " + group2.getName() + ")";
		TypeOfGroup type;
		if(group1.collection instanceof Set && !(group2.collection instanceof Set)) {
			type = group2.type;
		}else {
			type = group1.type;
		}
		GroupOfTelephoneCard group = new GroupOfTelephoneCard(type, name);
		for(TelephoneCard tempgroup1 : group1.collection) {
			for(TelephoneCard tempgroup2 : group2.collection) {
				if(!tempgroup1.equals(tempgroup2)) {
					group.add(tempgroup1);
				}
			}
		}
		for(TelephoneCard tempgroup2 : group1.collection) {
			for(TelephoneCard tempgroup1 : group2.collection) {
				if(!tempgroup1.equals(tempgroup2)) {
					group.add(tempgroup1);
				}
			}
		}
		return group;
	}
}