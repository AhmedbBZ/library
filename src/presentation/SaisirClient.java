package presentation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;

import Controle.ClientControler;
import Controle.LibraryController;
import entitees.ArmoireObject;
import entitees.Client;
import exeptions.PasDeClientExeption;
import service.GestionClient;
import service.GestionLibrary;
public class SaisirClient {
	static Scanner sc = new Scanner(System.in);


		
		public static int controle_CIN_Unique() {
			if (GestionClient.tabClient==null) {
				return SaisirLibrary.controle_Entier();
			}
			else{
				
				while(true) {
					boolean unique = true;
					int a=SaisirLibrary.controle_Entier();
					sc.nextLine();
					for(int i=0; i<GestionClient.tabClient.length;i++) {
						if (GestionClient.tabClient[i].getCin()==a) {
							unique=false;
						}
					}
					if(unique) return a;
					else {
						System.err.println("**ERROR** donner CIN unique");
					}
				}
			  }
		}
	
	public static Client saisirClient() {
		System.out.println("donnee le nom de client \n ");
		String nom=sc.next();
		System.out.println("donnee le prenom de client \n ");
		String prenom=sc.next();
		System.out.println("donnee le num CIN de client \n ");
		int cin=controle_CIN_Unique();
		SaisirLibrary.sc.nextLine();
		System.out.println("donnee la date de naissance**format (jj/mm/aaaa)** \n ");
		String date=sc.next();
		while (!GestionClient.date_toString(date)){
            System.err.println("#ERROR# donner une date valide **format (jj/mm/aaaa)**");
            date = SaisirLibrary.controle_Chaine();
        }
		System.out.println("donnee l'adress de client \n ");
		String adress=sc.next();
		System.out.println("donnee le num telephone de client \n ");
		int num_tel=SaisirLibrary.controle_Entier();
		SaisirLibrary.sc.nextLine();
		return new Client(nom, prenom, cin, date, adress, num_tel, false , false,"","");
		}
	

	public static void menu_Client() throws ParseException, PasDeClientExeption{
		GestionClient.initialiser("src/DAO/Client.json");
		 System.out.println("\n********************************************\n           ***Menu-Client***\n(1)Ajouter un Client\n(2)Supprimer un Client  \n(3)modeffier un Client  \n(4)Chercher un Client  \n(5)Afficher tous les Clients\n(6)Donneé un livre\n(7)Recevoire un livre\n(8)Renouvellement d'un abannement\n(9)Routour Menu-Generale  \n********************************************\n ");
		 int choix =SaisirLibrary.controle_Entier();
			while (!SaisirLibrary.controle_MinMax(choix, 1, 9)) {
				 System.err.println("#ERROR# donner un entier parmie les choix disponible");
				 choix=SaisirLibrary.controle_Entier();
			}
		 switch (choix) {
		case 1: {
			
			ClientControler.ajouterCc(saisirClient());
			break;
			}
			
		
		case 2: {
	    	if (GestionClient.tabClient.length==0||GestionClient.tabClient==null) {
	    		System.err.println("**ERROR** Pas de Cliens dans le Systeme");
	    	}
	    	else {
				System.out.println("-> donner le nemero CIN de Client a supprimer");
				ClientControler.supprimerCc(SaisirLibrary.controle_Entier());
	    	}
	    	break;
		}
		case 3: {
	    	if (GestionClient.tabClient.length==0||GestionClient.tabClient==null) {
	    		System.err.println("**ERROR** Pas de Cliens dans le Systeme");
	    	}
	    	else {
				System.out.println("-> donner le nemero CIN de Client a modifier");
				ClientControler.modifierCc(SaisirLibrary.controle_Entier());
	    	}
			break;
		}
		case 4: {
	    	if (GestionClient.tabClient.length==0||GestionClient.tabClient==null) {
	    		System.err.println("**ERROR** Pas de Cliens dans le Systeme");
	    	}
	    	else {
				System.out.println("-> donner le nemero CIN de Client a chercher");
				ClientControler.chercherCc(SaisirLibrary.controle_Entier());
	    	}

			break;
		}
		case 5: {
	    	if (GestionClient.tabClient.length==0||GestionClient.tabClient==null) {
	    		System.err.println("**ERROR** Pas de Cliens dans le Systeme");
	    	}
	    	else {
	    		ClientControler.afficherCc();
	    	}
			
			break;
			
		}
		case 6: {GestionClient.DonneLivre();
			break;
			
		}
		case 7: {GestionClient.RecevoireLivre();
			break;
			
		}
		case 8: {
			System.out.println("taper le num CIN de client ");
			int cin=SaisirLibrary.controle_Entier();
			SaisirLibrary.sc.nextLine();
			Client c = GestionClient.CINClien(cin);
			if(c==null) {
				System.err.println("#ERROR# ce client n'xiste pas dans le SYSTEME ");
			}
			else {
			GestionClient.AjoutAbannement(c);}
			break;
			
		}
		case 9: {
			break;
			
		}
	}
	}

}
