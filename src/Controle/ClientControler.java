package Controle;


import java.text.ParseException;

import entitees.Client;
import service.GestionClient;


public class ClientControler {
	public static void ajouterCc (Client a) throws ParseException {
		if (GestionClient.deja_existe(a.getCin())==true) {
			System.err.println("#ERROR#le client choisie est deja existe");
		}
		else {
			GestionClient.ajouterClient(a);
		}
	}
	public static void supprimerCc(int x) {
		GestionClient.supprimerClient(x);
	}
	public static void chercherCc(int x) {
		GestionClient.chercherClient(x);
	}
	public static void afficherCc() {
		GestionClient.afficherListeClients();
	}
	public static void modifierCc(int x) {
		GestionClient.modifierClient(x);
	}
}
