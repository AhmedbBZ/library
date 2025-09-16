package service;
import DAO.LibraryDB;
import DAO.RevenusDB;
import entitees.ArmoireObject;
import entitees.DVD;
import entitees.Filme;
import entitees.Livre;
import entitees.Magazine;
import presentation.SaisirLibrary;

public class GestionLibrary {
	private static int revunu;
	public static ArmoireObject[] tablibrary ;
	public static String fichier;
	public static String fichier2;
	
	public static int getRevunu() {
		return revunu;
	}

	public static void setRevunu(int revunu) {
		GestionLibrary.revunu = revunu;
	}

	
	
	public static void initialiser(String fichier) {
		GestionLibrary.fichier=fichier;
		tablibrary=LibraryDB.ReadBooks(fichier);
		
	}
	
	public static void enregistrer() {
		LibraryDB.Write(GestionLibrary.fichier, GestionLibrary.tablibrary);
	}
	
	public static void initialiserR(String fichier) {
		GestionLibrary.fichier2=fichier;
		revunu=RevenusDB.ReadRevenu(fichier);
		
	}
	
	public static void enregistrerR() {
		RevenusDB.Write(GestionLibrary.fichier2, GestionLibrary.revunu);
	}
	
	public static boolean deja_existe(int x) {
		boolean test=false;
		if(tablibrary==null) {
			return false;
		}
		for(int i=0;i<tablibrary.length;i++) {
    		if(tablibrary[i].getISBN()==x) {
    			test=true;	
    		}
    	}
		return test;
    }
	public static boolean PeutEtreLivree(ArmoireObject a ) {
		if (a.getNbr_copies()==0) {
			System.err.println("#ERROR# ce livre est HORS STOCK");
			return false;
		}
		else {
			return true;
			}
	}
 static ArmoireObject iSBNArmoireObject (int x) {
		for (int i=0;i<tablibrary.length;i++) {
			if (tablibrary[i].getISBN()==x) {
				return tablibrary[i];
			}
		}
		return null;
	}
 
    public static void modifierArmoire(int x) {
		if(deja_existe(x)==false) {
			System.err.println("**ERROR** L'exemplaire choisie a modifier n'est pas exite dans l'armoire");
		}
		else {    		
			for(int i=0;i<tablibrary.length;i++) {
				if(tablibrary[i].getISBN()==x) {
					if(  tablibrary[i] instanceof Magazine ){
		    			System.out.println("1-modifier titre\n2-modifier auteur\n3-modifier type\n4-modifier annee\n5-modifier editeur\n6-modifier nbr copies\n7-modifier description\n8-modifier nbr pages\n");
		    			int choix=SaisirLibrary.controle_Entier();
		    			SaisirLibrary.sc.nextLine();
		    			while(choix>8||choix<1) {
		    				System.err.println("#ERROR# donner un entier parmie Les choix disponibles");
		    				choix=SaisirLibrary.controle_Entier();
		    				SaisirLibrary.sc.nextLine();
		    			}
		    			
		    			switch (choix) {
						case 1: {
							System.out.println("donner le nouveau titre");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setTitre(ch1);
							break;
						}
						case 2: {
							System.out.println("donner le nouveau auteur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setAuteurs(ch1);
							break;
						}
						case 3: {
							System.out.println("donner le nouveau type");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setType(ch1);
							break;
						}
						case 4: {
							System.out.println("donner la nouvelle annee");
							int a=SaisirLibrary.controle_Entier();
							while (!SaisirLibrary.controle_MinMax(a, 0, 2022)) {
								System.err.println("#INTERDIT# l'annee d'écriture devrait etre comprise entre [0,2022]  ");
								a=SaisirLibrary.controle_Entier();}
							tablibrary[i].setAnnee(a);
							break;
						}
						case 5: {
							System.out.println("donner le nouveau editeur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setEditeur(ch1);
							break;
						}
						case 6: {
							System.out.println("donner la nouvelle nombre de copies");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setNbr_copies(a);
							break;
						}
						case 7: {
							System.out.println("donner le nouvelle discription");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setDescription(ch1);
							break;
						}
						case 8: {
							System.out.println("donner la nouvelle nombre de pages");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							((Magazine) tablibrary[i]).setNbr_pages(a);
							break;
						}
		    			}
						
						}
		    			if( tablibrary[i] instanceof Livre ){
		    			System.out.println("1-modifier titre\n2-modifier auteur\n3-modifier type\n4-modifier annee\n5-modifier editeur\n6-modifier nbr copies\n7-modifier description\n8-modifier nbr pages\n");
		    			int choix1=SaisirLibrary.controle_Entier();
		    			SaisirLibrary.sc.nextLine();
		    			while(choix1>8||choix1<1) {
		    				System.err.println("#ERROR# donner un entier parmie Les choix disponibles");
		    				choix1=SaisirLibrary.controle_Entier();
		    				SaisirLibrary.sc.nextLine();
		    			}
		    			
		    			switch (choix1) {
						case 1: {
							System.out.println("donner le nouveau titre");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setTitre(ch1);
							break;
						}
						case 2: {
							System.out.println("donner le nouveau auteur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setAuteurs(ch1);
							break;
						}
						case 3: {
							System.out.println("donner le nouveau type");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setType(ch1);
							break;
						}
						case 4: {
							System.out.println("donner la nouvelle annee");
							int a=SaisirLibrary.controle_Entier();
							while (!SaisirLibrary.controle_MinMax(a, 0, 2022)) {
								System.err.println("#INTERDIT# l'annee d'écriture devrait etre comprise entre [0,2022]  ");
								a=SaisirLibrary.controle_Entier();}
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setAnnee(a);
							break;
						}
						case 5: {
							System.out.println("donner le nouveau editeur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setEditeur(ch1);
							break;
						}
						case 6: {
							System.out.println("donner la nouvelle nombre de copies");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setNbr_copies(a);
							break;
						}
						case 7: {
							System.out.println("donner le nouvelle discription");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setDescription(ch1);
							break;
						}
						case 8: {
							System.out.println("donner la nouvelle nombre de pages");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							((Livre) tablibrary[i]).setNbr_pages(a);
							enregistrer();
							break;
						}
						
						
						}
		    			}
		    			if( tablibrary[i] instanceof DVD ){
		    			System.out.println("1-modifier titre\n2-modifier auteur\n3-modifier type\n4-modifier annee\n5-modifier editeur\n6-modifier nbr copies\n7-modifier description\n8-modifier taille\n");
		    			int choix1=SaisirLibrary.controle_Entier();
		    			SaisirLibrary.sc.nextLine();
		    			switch (choix1) {
						case 1: {
							System.out.println("donner le nouveau titre");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setTitre(ch1);
							break;
						}
						case 2: {
							System.out.println("donner le nouveau auteur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setAuteurs(ch1);
							break;
						}
						case 3: {
							System.out.println("donner le nouveau type");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setType(ch1);
							break;
						}
						case 4: {
							System.out.println("donner la nouvelle annee");
							int a=SaisirLibrary.controle_Entier();
							while (!SaisirLibrary.controle_MinMax(a, 0, 2022)) {
								System.err.println("#INTERDIT# l'annee d'écriture devrait etre comprise entre [0,2022]  ");
								a=SaisirLibrary.controle_Entier();}
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setAnnee(a);
							break;
						}
						case 5: {
							System.out.println("donner le nouveau editeur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setEditeur(ch1);
							break;
						}
						case 6: {
							System.out.println("donner la nouvelle nombre de copies");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setNbr_copies(a);
							break;
						}
						case 7: {
							System.out.println("donner le nouvelle discription");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setDescription(ch1);
							break;
						}
						case 8: {
							System.out.println("donner la nouvelle taille");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							((DVD) tablibrary[i]).setTaille(a);
							break;
						}
						
						}
		    			}
		    			if( tablibrary[i] instanceof Filme ){
		    			System.out.println("1-modifier titre\n2-modifier auteur\n3-modifier type\n4-modifier annee\n5-modifier editeur\n6-modifier nbr copies\n7-modifier description\n8-modifier Duree\n");
		    			int choix1=SaisirLibrary.controle_Entier();
		    			SaisirLibrary.sc.nextLine();
		    			switch (choix1) {
						case 1: {
							System.out.println("donner le nouveau titre");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setTitre(ch1);
							break;
						}
						case 2: {
							System.out.println("donner le nouveau auteur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setAuteurs(ch1);
							break;
						}
						case 3: {
							System.out.println("donner le nouveau type");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setType(ch1);
							break;
						}
						case 4: {
							System.out.println("donner la nouvelle annee");
							int a=SaisirLibrary.controle_Entier();
							while (!SaisirLibrary.controle_MinMax(a, 0, 2022)) {
								System.err.println("#INTERDIT# l'annee d'écriture devrait etre comprise entre [0,2022]  ");
								a=SaisirLibrary.controle_Entier();}
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setAnnee(a);
							break;
						}
						case 5: {
							System.out.println("donner le nouveau editeur");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setEditeur(ch1);
							break;
						}
						case 6: {
							System.out.println("donner la nouvelle nombre de copies");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							tablibrary[i].setNbr_copies(a);
							break;
						}
						case 7: {
							System.out.println("donner la nouvelle discription");
							String ch1=SaisirLibrary.controle_Chaine();
							tablibrary[i].setDescription(ch1);
							break;
						}
						case 8: {
							System.out.println("donner la nouvelle duree");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							((Filme) tablibrary[i]).setDuree(a);
							break;
						}
						
						}
		    			}
	    		}
	    	}
		}
    	
	enregistrer();	
    }

	

    public static void afficherArmoire() {
    	if (tablibrary.length==0) {
    		System.err.println("**ERROR** L'Armoire est vide");
    	}
    	else {
	    	for(int i=0;i<tablibrary.length;i++) {
	    		System.out.println("\n"+tablibrary[i].lire()+"\n");
	    	}
    	}
    }
    public static void supprimerArmoireObject(int x) {
    	int a=0;
    	
    		if(deja_existe(x)==false) {
    			System.err.println("**ERROR** L'exemplaire choisie a supprimer n'est pas exite dans l'armoire");
    		}
    		else {
    			ArmoireObject[] tmp=new ArmoireObject[tablibrary.length-1];

        		
    			for(int i=0;i<tablibrary.length;i++) {
    	    		if(tablibrary[i].getISBN()==x) {
    	    			tablibrary[i]=null;
    	    			
    	    		}
    	    	}
    	    	for(int i =0;i<tablibrary.length;i++) {
    				if(tablibrary[i]!=null) {
    					tmp[a]=tablibrary[a];
    					a++;
    				}
    				
    			}
    	    	tablibrary=tmp;
			}
    	enregistrer();	
     }

	
	public static void chercherArmoireObject(int x) {
	  	if (tablibrary==null) {
    		System.err.println("**ERROR** L'Armoire est vide");
    	}
    	else {
    		if(deja_existe(x)==false) {
    			System.err.println("**ERROR** L'exemplaire a rechercher n'est pas exite dans l'armoire");
    		}
    		else {
    			for(int i=0;i<tablibrary.length;i++) {
    	    		if(tablibrary[i].getISBN()==x) {
    	    			System.out.println("\n"+tablibrary[i].lire()+"\n");
    	    		}
    			}
			}
    	}	
	}
	
	public static void chercherArmoireObjectMotCle(String x) {
		int test=0;
	  	if (tablibrary==null) {
    		System.err.println("**ERROR** L'Armoire est vide");
    	}
    	else {
    		for(int i=0;i<tablibrary.length;i++) {
    	    	if(tablibrary[i].getAuteurs().contains(x)) {
    	    		System.out.println("\n"+tablibrary[i].lire()+"\n");
    	    		test++;
    	    	}
    		}
    		if(test==0) {
			System.err.println("Pas de Livre contient ["+x+"] comme parite de nom de son Auteur");}
    	}	
	}
	
	public static void ajouterArmoireObject(ArmoireObject ao) {
			

			if(tablibrary==null) {
				tablibrary = new ArmoireObject[1];
				tablibrary[0] =ao;
			}
			else {
				ArmoireObject[]temp = new ArmoireObject[tablibrary.length+1];
				System.arraycopy(tablibrary, 0, temp, 0, tablibrary.length);
				temp[tablibrary.length] = ao;
				tablibrary = temp;
				temp = null;// garbage collector ye5dem 5edemtou
			}
			enregistrer();
		}



}