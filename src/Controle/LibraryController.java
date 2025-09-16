package Controle;

import entitees.ArmoireObject;
import presentation.SaisirLibrary;
import service.GestionLibrary;
	
	public class LibraryController {
	
		public static void ajouterC(ArmoireObject a) {
			if (GestionLibrary.deja_existe(a.getISBN())==true) {
				System.err.println("l'exemplaire choisie est deja existe");
			}
			else {
				GestionLibrary.ajouterArmoireObject(a);
			}
		}
	public static void supprimerC(int x) {
			GestionLibrary.supprimerArmoireObject(x);
	}
	public static void chercherC(int x) {
			GestionLibrary.chercherArmoireObject(x);
	}
	public static void chercherCMC(String x) {
		GestionLibrary.chercherArmoireObjectMotCle(x);
}
	public static void afficherC() {
		GestionLibrary.afficherArmoire();
	}
	public static void modifierC(int x) {
		GestionLibrary.modifierArmoire(x);
	}
	}
