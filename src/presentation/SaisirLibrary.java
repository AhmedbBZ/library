package presentation;

import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;

import Controle.LibraryController;
import entitees.ArmoireObject;
import entitees.DVD;
import entitees.Filme;
import entitees.Livre;
import entitees.Magazine;
import service.GestionLibrary;

public class SaisirLibrary {
	public static Scanner sc = new Scanner(System.in);

	public static void menu_library(){
		//.initialiser("src/presentation/Book.json");
		 System.out.println("\n********************************************\n           ***Menu-Library***\n(1)Ajouter un article\n(2)Supprimer un article  \n(3)modeffier un article  \n(4)Chercher un article  \n(5)Afficher l'armoire\n(6)Voire tous les Revenus \n(7)Routour Menu-Generale \n********************************************\n ");
		 int choix =controle_Entier();
			while (!controle_MinMax(choix, 1, 7)) {
				 System.err.println("#ERROR# donner un entier parmie les choix disponible");
				 choix=controle_Entier();
			}
		 switch (choix) {
		case 1: {
			int c=saisir_Objet();
			ArmoireObject ao;
			
			switch(c) {
			  case 1:
				  ao=SaisirLibrary.saisirLivre();
				  break;
			  case 2:
				  ao=SaisirLibrary.saisirMagazine();
				  break;
			  case 3:
				  ao= SaisirLibrary.saisirDVD();
				  break;
			  default:
				  ao= SaisirLibrary.saisirFilme();
				  break;
			}
			LibraryController.ajouterC(ao);
			break;
			}
			
		
		case 2: {
	    	if (GestionLibrary.tablibrary==null) {
	    		System.err.println("**ERROR** L'Armoire est vide");
	    	}
	    	else {
				System.out.println("-> donner le nemero ISBN de l'article a supprimer");
				LibraryController.supprimerC(controle_Entier());
	    	}
	    	break;
		}
		case 3: {
			System.out.println("-> donner le nemero ISBN de l'article a modifier");
			LibraryController.modifierC(controle_Entier());
			break;
		}
		case 4: {
			int b=saisir_recherche();
			
			switch(b) {
			  case 1:{
				  System.out.println("-> donner le nemero ISBN de l'article a chercher");
					LibraryController.chercherC(controle_Entier());
				  break;}
			  case 2:{
				  System.out.println("-> donner le mot cle(partie de nom de l'auteur) de l'article a chercher");
					LibraryController.chercherCMC(controle_Chaine());
				  break;}
			
			}
			break;
		}
		case 5: {
			LibraryController.afficherC();
			break;
			
		}
		case 6: {System.out.println("LES REVENUS TOTALES : "+GestionLibrary.getRevunu()+"DT");
			break;
			
		}
		case 7: {
			break;
			
		}

	}
	}
	public static int controle_ISBN_Unique() {
		if (GestionLibrary.tablibrary==null) {
			return controle_Entier();
		}
		else{
			
			while(true) {
				boolean unique = true;
				int a=controle_Entier();
				sc.nextLine();
				for(int i=0; i<GestionLibrary.tablibrary.length;i++) {
					if (GestionLibrary.tablibrary[i].getISBN()==a) {
						unique=false;
						
					}
				}
				if(unique) return a;
				else {
					System.err.println("**ERROR** donner ISBN unique");
				}
			}
		  }
	}
	public static int controle_Entier() {
		while(true) {
			try {return Math.abs(sc.nextInt());
			} catch (Exception e) {
				sc.nextLine();
				System.err.println("#ERROR# donner un entier svp");
			}
		}
	}
	public static boolean controle_MinMax(int x,int a ,int b) {
		if(x>=a && x<=b) {
			return true;
			}
		return false;
	}
	
	
	public static String controle_Chaine() {
		while(true) {
			try {String a= sc.nextLine();
					return a;
				}
				
			catch (Exception e) {
				System.err.println("#ERROR# donner une chaine svp");
			 }
		}
	}

	
	public static ArmoireObject saisirArmoireObject() {
		System.out.println("donnee le titre \n ");
		String titre=controle_Chaine();
		System.out.println("donnee le nom de l'auteur\n");
		String auteur=controle_Chaine();
		System.out.println("donnee l'anner de creation \n");
		int annee=controle_Entier();
		while (!controle_MinMax(annee, 0, 2022)) {
			System.err.println("#INTERDIT# l'annee d'écriture devrait etre comprise entre [0,2022]  ");
			annee=controle_Entier();}
		sc.nextLine();
		System.out.println("donnee le iSBN \n ");
		int ISBN=controle_ISBN_Unique();
		System.out.println("donnee la description\n ");
		String description=controle_Chaine();
		System.out.println("donnee le type\n ");
		String type=controle_Chaine();
		System.out.println("donnee le nembre de copies\n");
		int nbr_copies=controle_Entier();
		sc.nextLine();
		System.out.println("donnee le nom de l'editeur \n ");
		String editeur=controle_Chaine();
		return new ArmoireObject(titre,auteur,annee,ISBN,description,type,nbr_copies,editeur);
		
		
		
	}
	public static Livre saisirLivre() {
		ArmoireObject o=saisirArmoireObject();
		System.out.println("donnee le nombre de pages \n ");
		int nbr_pages=controle_Entier();
		sc.nextLine();
		return new Livre(o, nbr_pages);
	}
	public static DVD saisirDVD() {
		ArmoireObject o=saisirArmoireObject();
		System.out.println("donnee le taille de DVD \n ");
		int taille=controle_Entier();
		sc.nextLine();
		return new DVD(o, taille);
	}
	public static Filme saisirFilme() {
		ArmoireObject o=saisirArmoireObject();
		System.out.println("donnee le duree de Filme \n ");
		int duree=controle_Entier();
		sc.nextLine();
		return new Filme(o, duree);
	}
	public static Magazine saisirMagazine() {
		ArmoireObject o=saisirArmoireObject();
		System.out.println("donnee le nombre de pages \n ");
		int nbr_pages=controle_Entier();
		sc.nextLine();
		return new Magazine(o, nbr_pages);
	}
	public static int saisir_Objet() {
		System.out.println("\n********************************************\n              ***Ajouter***\n(1)Livre\n(2)Magazine\n(3)DVD\n(4)Filme\n********************************************");
		int choitAjout =controle_Entier();
		sc.nextLine();
		while (!controle_MinMax(choitAjout, 1, 4)) {
			System.err.println("#ERROR# donner un entier parmie Les choix disponible");
			 choitAjout =controle_Entier();
			 sc.nextLine();
		}
		
		return choitAjout;
	}
	
	public static int saisir_recherche() {
		System.out.println("\n********************************************\n              ***Chercher Par***\n(1)ISBN\n(2)Mot Clé\n********************************************");
		int choitCherche =controle_Entier();
		sc.nextLine();
		while (!controle_MinMax(choitCherche, 1, 2)) {
			System.err.println("#ERROR# donner un entier parmie Les choix disponible");
			 choitCherche =controle_Entier();
			 sc.nextLine();
		}
		
		return choitCherche;
	}
	


}
