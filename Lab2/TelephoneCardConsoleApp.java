package Lab2;

import java.util.Arrays;

/*
 * Autor: Pawe³ Ró¿añski
 * numer indeksu: 252772
 * Data: 20.10.2020r.
 * wersja 1.0
 * 
 * Program: Operacje na obiektach klasy TelephoneCard
 * 
 * Zawiera: Klase która bêdzie menu dla klasy TelephoneCard
 */

public class TelephoneCardConsoleApp {

	private static final String AUTOR =
			"Program TelephoneCard\n" +
			"Autor: Pawe³ Ró¿añski\n" +
			"Data: 20.10.2020r.\n" +
			"numer indeksu: 252772";
	
	private static final String MENU = 
			"M E N U:\n " +
			"1 - Dodaj Kartê Telefoniczn¹ \n" +
			"2 - Pokaz Kartê Telefoniczn¹ \n" +
			"3 - Usuñ Kartê Telefoniczn¹ \n" +
			"4 - Zmodyfikuj Kartê Telefoniczn¹ \n" +
			"5 - Wczytaj Kartê Telefoniczn¹ z pliku \n" + 
			"6 - Zapis Kartê Telefoniczn¹ do pliku \n" +
			"7 - Zapisz wykorzystujac serializacje\n" +
			"8 - Wczytaj wykorzystujac serializacje\n" +
			"0 - Zakoñcz dzia³anie programu";
	
	private static final String MENU_CHANGE_CARD = 
			"M E N U zmiany: co chcesz zmieniæ? \n" + 
			"1 - Tytu³ \n" +
			"2 - Serie \n" +
			"3 - Data Produkcji \n" +
			"4 - Iloœæ impulsów \n" +
			"5 - Typ \n" +
			"6 - Cena \n" +
			"0 - Wyjdz z opcji zmiany Karty Telefonicznej \n" +
			"-> ";
	
	private static Console console = new Console();
	
	private TelephoneCard card = null;
	
	public void run() {
		System.out.println(AUTOR);
		
		while(true) {
			console.ClearConsole();
			console.PrintText(MENU);
			try {
				switch(console.LoadInt("=> ")) {
				case 1:
					//towrzenie karty
					card = createNewTelephoneCard();
					break;
				case 2:
					//wyswietlenie karty
					showTelephoneCard();
					break;
				case 3:
					//usunecie karty
					card = null;
					console.PrintTextWithEnter("Karta telefoniczna zosta³a usuneta!");
					break;
				case 4:
					//modyfikacja karty
					if(card == null) throw new TelephoneCardException("Nie ma w tej chwili zadnej osoby");
					changeTelephoneCard(card);
					break;
				case 5:
					//wczytanie karty z pliku
					String filename = console.LoadString("Podaj nazwe pliku: ");
					card = TelephoneCard.UploadDataInFile(filename);
					console.PrintTextWithEnter("Dane zosta³y wczytane z pliku: " + filename);
					break;
				case 6:
					//zapisanie karty do pliku
					String filename2 = console.LoadString("Podaj nazwe pliku: ");
					TelephoneCard.SaveDataInFile(filename2, card);
					console.PrintTextWithEnter("Dane zosta³y zapisane do pliku: " + filename2);
					break;
				case 7:
					//Serializacja ZAPISZ
					System.out.println("Serializacja ZAPIS: ");
					String filename3 = console.LoadString("Podaj nazwe pliku: ");
					TelephoneCard.writeTelephoneCard(filename3, card);
					break;
				case 8:
					//Serializacja ODCZYT
					System.out.println("Serializacja ODCZYT: ");
					String filename4 = console.LoadString("Podaj nazwe pliku: ");
					card = TelephoneCard.readTelephoneCard(filename4);
					break;
				case 0:
					//zakonczenie dzialanie programu
					console.PrintTextWithEnter("\nProgram zakonczyl dzialanie!");
					System.exit(0);
					break;
				default:
					console.PrintTextWithEnter("Podana opcja nie istnieje.");
					break;
				}
			}catch(TelephoneCardException e) {
				console.PrintErrorWithEnter(e.getMessage());
			}
		}
	}
	
	public static TelephoneCard createNewTelephoneCard() {
		TelephoneCard card = null;
		String NewTitle = console.LoadString("Podaj tytu³: ");
		String NewSeries = console.LoadString("Podaj numer serii: ");
		int newProductionData = console.LoadInt("Podaj date produkcji: ");
		int newPulses = console.LoadInt("Podaj iloœæ impulsów: ");
		console.PrintText("Mo¿liwoœci typów: " + Arrays.deepToString(TypeOfCard.values()));
		String newType = console.LoadString("Podaj typ: ");
		double newPrice = console.LoadDouble("Podaj cene: ");
		try {
			card = new TelephoneCard(NewTitle, NewSeries);
			card.setProductionData(newProductionData);
			card.setPulses(newPulses);
			card.setType(newType);
			card.setPrice(newPrice);
			}catch(TelephoneCardException e) {
				console.PrintErrorWithEnter(e.getMessage());
		}
		return card;
	}
	
	private void showTelephoneCard() {
		console.ClearConsole();
		
		if(card == null) {
			console.PrintTextWithEnter("Brak danych o karcie");
			return;
		}
		console.PrintTelephoneCard(card);
	}
	
	static void changeTelephoneCard(TelephoneCard card) {
		
		while(true) {
			console.ClearConsole();
			console.PrintText(MENU_CHANGE_CARD);
			
			try {
				switch(console.LoadInt("")){
				case 1:
					card.setTitle(console.LoadString("Podaj tytul: "));
					break;
				case 2:
					card.setSeries(console.LoadString("Podaj numer serii: "));
					break;
				case 3:
					card.setProductionData(console.LoadInt("Podaj date produkcji: "));
					break;
				case 4:
					card.setPulses(console.LoadInt("Podaj iloœæ impulsów: "));
					break;
				case 5:
					console.PrintText("Mo¿liwoœci typów: " + Arrays.deepToString(TypeOfCard.values()));
					card.setType(console.LoadString("Podaj typ: "));
					break;
				case 6:
					card.setPrice(console.LoadDouble("Podaj cene: "));
					break;
				case 0:
					return;
				default:
					console.PrintTextWithEnter("Podana opcja nie istnieje.");
					break;
				}
			}catch(TelephoneCardException e) {
				console.PrintErrorWithEnter(e.getMessage());
			}
		}
	}
}
