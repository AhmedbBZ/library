package presentation;
import java.text.ParseException;
import java.util.Scanner;

import DAO.RevenusDB;
import exeptions.PasDeClientExeption;
import service.GestionClient;
import service.GestionLibrary;
import service.GestionNotes;
public class Main {
static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws ParseException, PasDeClientExeption
    {
    	
    	while(menu()) {
    	
    	}
    	
    }
 public static boolean menu() throws ParseException, PasDeClientExeption {

	//
	 System.out.println("\n********************************************\n           ***Menu-Generale***\n(1)Gestion L'armoire de la Bibliotheque\n(2)Gestion Client et Abonnement\n(3)Voire notiffications\n(4)EXIT DE L'APPLICATION\n********************************************\n ");
	 int choix =SaisirLibrary.controle_Entier();
	 	GestionLibrary.initialiser("src/DAO/Book.json");
	 	GestionClient.initialiser("src/DAO/Client.json");
	 	GestionLibrary.initialiserR("src/DAO/Revenus.json");
		while (!SaisirLibrary.controle_MinMax(choix, 1, 4)) {
			System.err.println("#ERROR# donner un entier parmie Les choix disponible");
			 choix =SaisirLibrary.controle_Entier();
			 SaisirLibrary.sc.nextLine();
			 
		}
		/* for(int i=0;i<GestionClient.tabClient.length;i++) {
			 System.out.println(GestionClient.tabClient[i].getNom());
		 }*/
		
	switch (choix) {
	case 1 : {
		SaisirLibrary.menu_library();
		return true;
		}
	case 2 : {
		SaisirClient.menu_Client();
		return true;
		}
	case 3 : {
		GestionNotes.NotifficationsToString();
		return true;
		}
	case 4 : {
		System.out.println("MERCIE D'utiliser L'application ♥♥AHMED LIBRARY MANAGER ♥♥");
		return false;
		}
	default:{
		return true;
	}
	}
 
}}









