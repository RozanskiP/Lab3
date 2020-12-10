package Lab2;

import java.util.Scanner;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 20.10.2020r.
 * wersja 1.0
 * 
 * Program: Operacje na obiektach klasy TelephoneCard
 * 
 * Zawiera: Klasa zawieraj¹ca metody do komunikacji z uzytkownikiem w Consoli
 */

public class Console {
	
	private final String ERROR = 
			"B³¹d wprowadzania. Spróbuj ponownie!";
	
	private Scanner scanner = new Scanner(System.in);
	
	public String LoadString(String text) {
		System.out.println(text);
		return scanner.nextLine();
	}
	
	public void PrintText(String text) {
		System.out.println(text);
	}
	
	public void PrintTextWithEnter(String text) {
		System.out.println(text);
		System.err.println("Aby przejœæ dalej naciœnij ENTER");
		LoadString("");
	}
	
	public void PrintErrorWithEnter(String error) {
		System.err.println(error);
		System.err.println("Aby przejœæ dalej naciœnij ENTER");
		LoadString("");
	}
	
	public void PrintTelephoneCard(TelephoneCard card) {
		System.out.println(card);
	}
	
	public void ClearConsole() {
		System.out.println("\n\n");
	}
	
	public int LoadInt(String text) {
		boolean isError;
		int i = 0;
		do {
			isError = false;
			try {
				i = Integer.parseInt(LoadString(text));
			}catch(NumberFormatException e) {
				System.err.println(ERROR);
				isError = true;
			}
		}while(isError);
		return i;
	}
	
	public double LoadDouble(String text) {
		boolean isError;
		double d = 0;
		do {
			isError = false;
			try {
				d = Double.parseDouble(LoadString(text));
			}catch(NumberFormatException e) {
				System.err.println(ERROR);
				isError = true;
			}
		}while(isError);
		return d;
	}
}
