package Lab2;

/*
 * Autor: Pawe� R�a�ski
 * numer indeksu: 252772
 * Data: 20.10.2020r.
 * wersja 1.0
 * 
 * Program: Operacje na obiektach klasy TelephoneCard
 * 
 * Zawiera: Klasa wyjatk�w dla klasy TelephoneCard
 */

public class TelephoneCardException extends Exception{
	
	private static final long serialVersionUID = 42L;
	
	public TelephoneCardException(String messege) {
		super(messege);
	}
}
