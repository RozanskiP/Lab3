package Lab2;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 19.10.2020r.
 * wersja 1.1
 * 
 * Program: Operacje na obiektach klasy TelephoneCard
 * 
 * Zawiera: Klasa reprezentuje za pomoc¹ tylu enum typ carty
 */

//Gatunek filmowy
public enum TypeOfCard {
	UNKNOWN("------"),
	MAGNETIC("Magnetic"),
	CHIP("CHIP");
	
	String type;
	
	private TypeOfCard(String type1) {
		type = type1;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
