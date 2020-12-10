package Lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;



/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 19.10.2020r.
 * wersja 3.1 
 * Z dodaniem serializacji 
 * 
 * Program: Operacje na obiektach klasy TelephoneCard
 * 
 * Zawiera: Klasa reprezentujaca Karty telefoniczne
 */


public class TelephoneCard implements Serializable, Comparable<TelephoneCard> {
	private String title;
	private String series;
	private int productionDate;
	private int pulses;
	private TypeOfCard type;
	private double price;
	
	private static final long serialVersionUID = 1L;
	
	public TelephoneCard(String title, String series) throws TelephoneCardException {
		setTitle(title);
		setSeries(series);
	}
	
	
	
	public TelephoneCard(String title, String series, int productionData, int pulses, double price) throws TelephoneCardException{
		setTitle(title);
		setSeries(series);
		setProductionData(productionData);
		setPulses(pulses);
		setPrice(price);
		type = TypeOfCard.UNKNOWN;
	}
	
	//SERIALIZACJA zapis
	public static void writeTelephoneCard(String filename, TelephoneCard card) throws TelephoneCardException {
		try(ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream(filename))) {
			outputstream.writeObject(card);
		}catch(IOException e) {
			throw new TelephoneCardException("Coœ poszlo nie tak");
		}
	}
	
	//SERIALIZACJA odczyt
	public static TelephoneCard readTelephoneCard(String filename) throws TelephoneCardException {
		try (ObjectInputStream inputstream = new ObjectInputStream(new FileInputStream(filename))){
			TelephoneCard card = (TelephoneCard) inputstream.readObject();
			return card;
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Plik nie zostal znaleziony");
		}catch(IOException e) {
			throw new TelephoneCardException("B³ad podczas otwarcia");
		}catch(ClassNotFoundException e) {
			throw new TelephoneCardException("Coœ poszlo nie tak");
		}
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String Newtitle) throws TelephoneCardException {
		if((Newtitle == null) || Newtitle.equals("")){
			throw new TelephoneCardException("Wprowadzi³eœ b³êdny tytul, to pole nie moze byæ puste");
		}		
		this.title = Newtitle;
	}
	
	public String getSeries() {
		return series;
	}
	
	public void setSeries(String Newseries) throws TelephoneCardException {
		if((Newseries == null) || Newseries.equals("")) {
			throw new TelephoneCardException("Wprowadzi³eœ b³êdny tytul, to pole nie moze byæ puste");
		}
		this.series = Newseries;
	}
	
	public int getProductionData() {
		return productionDate;
	}
	
	//Mozna wpisac tylko od 1991 do 2004 - tylko w tych latach wychodzi³ karty
	public void setProductionData(int NewproductionData) throws TelephoneCardException {
		if((NewproductionData != 0) && (NewproductionData <= 1991 || NewproductionData >= 2004)) {
			throw new TelephoneCardException("Rok produkcji jest poza przedzia³em w ktorym byly produkowane [1991 - 2004]");
		}
		this.productionDate = NewproductionData;
	}
	
	public void setProductionData(String NewproductionData) throws TelephoneCardException {
		if(NewproductionData == null || NewproductionData.equals("")) {
			setProductionData(0);
			return;
		}
		try{
			setProductionData(Integer.parseInt(NewproductionData));
		}catch(NumberFormatException e) {
			throw new TelephoneCardException("Rok musi byæ liczba ca³kowita.");
		}
	}
	
	public int getPulses() {
		return pulses;
	}
	
	//Mo¿liwoœæ wpisania od 1 do 500 impulsów
	public void setPulses(int Newpulses) throws TelephoneCardException {
		if((Newpulses != 0) && (Newpulses < 0 || Newpulses > 501)) {
			throw new TelephoneCardException("Liczba impulsów jest poza przedzia³em [1 - 500]");
		}
		this.pulses = Newpulses;
	}
	
	//Metoda pprzeciazona gdy ktos wpisze String
	public void setPulses(String Newpulses) throws TelephoneCardException {
		if(Newpulses == null || Newpulses.equals("")) {
			setPulses(0);
			return;
		}
		try{
			setPulses(Integer.parseInt(Newpulses));
		}catch(NumberFormatException e) {
			throw new TelephoneCardException("Liczba impulsow musi byæ liczba ca³kowita.");
		}
	}
	
	public TypeOfCard getType() {
		return type;
	}
	
	public void setType(TypeOfCard Newtype) {
		this.type = Newtype;
	}
	
	//Przeci¹¿ona metoda setType gdy ktos wpisze String
	public void setType(String Newtype) throws TelephoneCardException{
		if((Newtype == null) || Newtype.equals("")) {
			this.type = TypeOfCard.UNKNOWN;
			throw new TelephoneCardException("Popraw wpisanie swojego typu (nadal pozostaje UNKNOWN)");
		}
		else {
			for(TypeOfCard TempType : TypeOfCard.values()) {
				if(TempType.type.equals(Newtype)) {
					this.type = TempType;
					return;
				}
			}
		}
		throw new TelephoneCardException("Wpisa³eœ coœ zle, nie ma takiego typu (przypisuje domyœlnie UNKNOWN)");
	}
	
	public double getPrice() {
		return price;
	}
	
	//Mo¿liwoœæ wpisania ceny wiêkszej ni¿ 0 
	public void setPrice(double Newprice) throws TelephoneCardException {
		if((Newprice != 0) && (Newprice < 0)) {
			throw new TelephoneCardException("Cena musi byæ wiêksza ni¿ 0 ");
		}
		this.price = Newprice;
	}
	
	public void setPrice(String Newprice) throws TelephoneCardException {
		if(Newprice == null || Newprice.equals("")) {
			setPrice(0);
			return;
		}
		try{
			setPrice(Double.parseDouble(Newprice));
		}catch(NumberFormatException e) {
			throw new TelephoneCardException("Rok musi byæ liczba ca³kowita.");
		}
	}
	
	@Override
	public String toString() {
		return "Tytu³: " + title + "\nserii:  " + series + "\nData produkcji: " + productionDate + "\nImpulsy: " + pulses + "\nTyp: " + type + "\nCena: " + price; 
	}
	
	
	public static void SaveData(PrintWriter writer, TelephoneCard card) {
		writer.println(card.title + "," + card.series + "," + card.productionDate + "," + card.pulses + "," + card.type + "," + card.price);
	}
	
	public static String SaveDataString(TelephoneCard card) {
		return card.title + "," + card.series + "," + card.productionDate + "," + card.pulses + "," + card.type + "," + card.price;
	}
	
	//zapis do pliku
	public static void SaveDataInFile(String filename, TelephoneCard card) throws TelephoneCardException {
		try(PrintWriter writer = new PrintWriter(filename)) {
			SaveData(writer,card);
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Nie uda³o sie zapisac w pliki: " + filename);
		}
	}
	
	public static TelephoneCard UploadDataInWindowDialogNew(String data) throws TelephoneCardException {
		try {
			String[] datasplited = data.split(",");
			TelephoneCard card = new TelephoneCard(datasplited[0], datasplited[1]);
			card.setProductionData(datasplited[2]);
			card.setPulses(datasplited[3]);
			card.setType(datasplited[4]);
			card.setPrice(datasplited[5]);
			
			return card;
		}catch(TelephoneCardException e) {
			throw new TelephoneCardException("Nie uda³o siê odczytaæ z pliku.");
		}
	}
	
	public static TelephoneCard UploadData(BufferedReader reader) throws TelephoneCardException {
		try {
			String data = reader.readLine();
			String[] datasplited = data.split(",");
			TelephoneCard card = new TelephoneCard(datasplited[0], datasplited[1]);
			card.setProductionData(datasplited[2]);
			card.setPulses(datasplited[3]);
			card.setType(datasplited[4]);
			card.setPrice(datasplited[5]);
			
			return card;
		}catch(IOException e) {
			throw new TelephoneCardException("Nie uda³o siê odczytaæ z pliku.");
		}
	}
	
	//odczyt z pliku
	public static TelephoneCard UploadDataInFile(String filename) throws TelephoneCardException {
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
			return TelephoneCard.UploadData(reader);
		}catch(FileNotFoundException e) {
			throw new TelephoneCardException("Nie ma takiego pliku: " + filename);
		}catch(IOException e) {
			throw new TelephoneCardException("Blad podczas odczytu z pliku: " + filename);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof TelephoneCard)) {
			return false;
		}
		TelephoneCard card = (TelephoneCard)obj;
		
		if(this.title != card.title) {
			return false;
		}
		
		return true;	
	}
	
	@Override
	public int hashCode() {
		return (int)(( title.hashCode() + series.hashCode() + productionDate + pulses + type.toString().hashCode()) * serialVersionUID);
	}
	
	@Override
	public int compareTo(TelephoneCard o) {
		int result = this.title.compareTo(o.title);
		
		if(result > 1) {
			return 1;
		}else if(result < -1){
			return -1;
		}else {
			return 0;
		}
	}
}