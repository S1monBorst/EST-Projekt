package Programm;

import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class Test {

	public static void main (String[] args) {

		/**
		 * This class serves as a demonstration to show the basic functionality of the code
		 * @author Maja Wandura
		 */
		        // Init

		        Speicher database = new Speicher();
		        Kunde siemens = new Kunde("Siemens","Teststadt","Testecke" , 2,78,87866);
		        Kunde bimbo = new Kunde("Bimbo","Teststadt","Testecke", 3, 58,87866);
		        Kunde phillips = new Kunde("Phillips","Teststadt","Testecke",4, 76,87866);
		        Kunde daimler = new Kunde("Daimler","Teststadt","Testecke", 5 ,745,87866);

		        Angebot angebot1 = new Angebot(siemens,"Schrankt�r");
		        Angebot angebot2 = new Angebot(bimbo,"Tisch mit vier Ecken");
		        Angebot angebot3 = new Angebot(phillips,"K�chentheke");
		        Angebot angebot4 = new Angebot(daimler,"Werkbank");

		        database.bestandsKundeAufnehmen(siemens);
		        database.bestandsKundeAufnehmen(bimbo);
		        database.bestandsKundeAufnehmen(phillips);
		        database.bestandsKundeAufnehmen(daimler);
		        database.angebotSpeichern(angebot1);
		        database.angebotSpeichern(angebot2);
		        database.angebotSpeichern(angebot3);
		        database.angebotSpeichern(angebot4);

		        // Kunde kommt rein

		        String customerName = "Bosch";
		        Kunde bosch;

		        // Kunde bereits vorhanden? Dann aus Datenbank holen, sonst neu erzeugen
		        try {
		            bosch = database.getCustomerbyName(customerName);
		        } catch (NoSuchElementException e) {
		            bosch = new Kunde("Bosch", "Teststadt", "Teststra�e", 6, 45, 324324);
		        }

		        // Angebot wird erstellt

		        String betreff = "Neue T�ren";
		        Angebot newOffer = new Angebot(bosch, betreff);

		        // Materialposten erstellen
		        //EinzelPosten einzelPosten = new EinzelPosten("Holzplatten 2x1m", 100);
		        MaterialKosten materialKosten = new MaterialKosten("Holzplatten 2x1m" , 100);
		        newOffer.angebotsPostenErstellen(materialKosten);

		        // Arbeitszeitposten erstellen
		        //ArbeitsZeit arbeitsZeit = new ArbeitsZeit(100, 60);
		        ArbeitsKosten arbeitsKosten = new ArbeitsKosten("Zu Materialposten 1", 2, 80);
		        newOffer.angebotsPostenErstellen(arbeitsKosten);

		        // Im Speicher ablegen
		        database.angebotSpeichern(newOffer);

		        // Zweites Angebot erstellen
		        Angebot secondOffer = new Angebot(
		                new Kunde("AMG GmbH", "Testdorf", "Testweg", 7 , 34, 345436), "Armaturen"
		        );

		        secondOffer.angebotsPostenErstellen(new ArbeitsKosten("Zu Materialposten 2", 3, 150));
		        secondOffer.angebotsPostenErstellen(new MaterialKosten("Eichenfurnier", 20000));

		        database.angebotSpeichern(secondOffer);

		        // Zweites Angebot abschlie�en
		        try {
					secondOffer.ausdrucken();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        // Berichte erzeugen

		        System.out.println("Bericht:");
		        database.getAngebote().forEach(System.out::println);
		        
	}

}
