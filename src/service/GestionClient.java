package service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.CellEditor;

import DAO.ClientDB;

import entitees.ArmoireObject;
import entitees.Client;
import exeptions.PasDeClientExeption;
import presentation.SaisirClient;
import presentation.SaisirLibrary;

public class GestionClient {
	public static Client[] tabClient ;
	public static String fichier;
	
	Scanner sc= new Scanner(System.in);
	
	public static void initialiser(String fichier) {
		GestionClient.fichier=fichier;
		tabClient=ClientDB.ReadClient(fichier);
		
	}
	
	public static void enregistrer() {
		ClientDB.Write(GestionClient.fichier, GestionClient.tabClient);
	}
	
	
	public static boolean date_toString(String input) {//controler si le string de date et vrai
        String formatString = "dd/MM/yyyy";				//stack over flow hhhh
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
	public static boolean deja_existe(long x) {
		boolean test=false;
		if(tabClient==null) {
			return false;
		}
		for(int i=0;i<tabClient.length;i++) {
    		if(tabClient[i].getCin()==x) {
    			test=true;	
    		}
    	}
		return test;
    }
	public static String AjoutJours(String ch,int x) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(ch));  
        }catch(ParseException e){  
            e.printStackTrace();  
         }  
        cal.add(Calendar.DAY_OF_MONTH,x);  
        return sdf.format(cal.getTime()); 
        
	}
	public static Client CINClien (long x) {
		for (int i=0;i<tabClient.length;i++) {
			if (tabClient[i].getCin()==x) {
				return tabClient[i];
			}
		}
		return null;
	}
	public static String[] separation(String s) {   //stack overflow hhhhh
		return s.split("#"); 
	}
	public static String separationDate(String ch) {   
			String tp=ch.substring(ch.indexOf("+")+1);
			return tp;
	}
	public static boolean LivreExist(Client c,int isbn) throws PasDeClientExeption {
		String[]tab=separation(c.getLivres());
		if((tab[0]=="")||(tab.length==0)) {
		throw new PasDeClientExeption("#ERROR# ce client n'xiste pas dans le SYSTEME ");
		}
		for(int i=0;i<tab.length;i++) {
			if(Integer.parseInt((String) tab[i].substring(0,tab[i].indexOf("+")))==isbn) {
				return true;
			}
		}
		return false;
		
		
	}
	public static void AnnulerLivre(Client c,int isbn) {
		String ch ="";
		String[]tab=separation(c.getLivres());
		for(int i=0;i<tab.length;i++) {
			if(Integer.parseInt((String) tab[i].subSequence(0,tab[i].indexOf("+")))==isbn) {
				tab[i]=null;
				break;
			}	
		}

		for(int i=0;i<tab.length;i++) {
			if(tab[i]!=null) {
				ch+=tab[i]+"#";
			}
		}
		
		c.setLivres(ch);
		enregistrer();
		
}
	
	
	public static boolean PeutPrend(Client c) throws ParseException {
		if (c.getSuspendu()) {
			System.err.println("#BLOQUE# vous etes suspendu ");
			return false;
		}
		else {	
			String[] tp =separation(c.getAbannement());
			String type=tp[0];
			String date=tp[1];
			if (Objects.equals(type, "m")) {
				if (GestionNotes.DateDifferance(date)>30) {
					System.err.println("#INTERDIT# votre abannement est exspireé");
					return false;
				}
			}
			if (Objects.equals(type, "t")) {
				if (GestionNotes.DateDifferance(date)>90) {
					System.err.println("#INTERDIT# votre abannement est exspireé");
					return false;
				}			

			}	
			else {
				if(c.getPremium()) {
					if(separation(c.getLivres()).length==5) {
						System.err.println("#INTERDIT# vous aver deja atteint le limite");
						return false;
					}
					else {
						return true;
					}
				}
				else {
					if(separation(c.getLivres()).length==3) {
						System.err.println("#INTERDIT# vous aver deja atteint le limite");
						return false;
					}
					else {
						return true;
					}
				}	
			}
		}
		return false;
		
	}
	public static String DateAujourdhui() {
		DateTimeFormatter d=DateTimeFormatter.ofPattern("dd/MM/yyyy");//stack overflow again hhhh
		String ld=d.format(LocalDateTime.now());
		return ld;
	}
	
	

	public static void supprimerClient(long x) {
    	
    	
		if(deja_existe(x)==false) {
			System.err.println("**ERROR** Le Client choisie a supprimer n'est pas exite dans e Systeme");
		}
		else {
			Client[] tmp=new Client[tabClient.length-1];

    		
			for(int i=0;i<tabClient.length;i++) {
	    		if(tabClient[i].getCin()==x) {
	    			tabClient[i]=null;
	    			
	    		}
	    	}
			int a=0;
	    	for(int i =0;i<tabClient.length;i++) {
				if(tabClient[i]!=null) {
					tmp[a]=tabClient[i];
					a++;
				}
				
			}
	    	tabClient=tmp;
	    	enregistrer();
		}
	
		
	}
	public static void chercherClient(long x) {
	  	if (tabClient==null) {
	  		System.err.println("**ERROR** Pas de Clients dans le Systeme");
    	}
	  	if (tabClient.length==0) {
	  		System.err.println("**ERROR** Pas de Clients dans le Systeme");
    	}
    	else {
    		if(deja_existe(x)==false) {
    			System.err.println("**ERROR** Le Client a rechercher n'est pas exite dans le Systeme");
    		}
    		else {
    			for(int i=0;i<tabClient.length;i++) {
    	    		if(tabClient[i].getCin()==x) {
    	    			System.out.println("\n"+tabClient[i].lire()+"\n");
    	    		}
    			}
			}
    	}	
	}
	public static void afficherListeClients() {
    	if (tabClient.length==0) {
    		System.err.println("**ERROR** Pas de Clients dans le Systeme");
    	}
    	else {
	    	for(int i=0;i<tabClient.length;i++) {
	    		System.out.println("\n"+tabClient[i].lire()+"\n");
	    	}
    	}
    }
		
	

	    public static void modifierClient(long x) {
			if(deja_existe(x)==false) {
				System.err.println("**ERROR** Le Clients a modifier n'est pas exist dans le Systeme");
			}
			else {    		
				for(int i=0;i<tabClient.length;i++) {
		    		if(tabClient[i].getCin()==x) {
		    			
		    			System.out.println("1-modifier nom \n2-modifier prenom\n3-modifier date de naissance\n4-modifier adress\n5-modifier num telephone\n6-modifier l'etat suspendu\n");
		    			int choix=SaisirLibrary.controle_Entier();
		    			SaisirLibrary.sc.nextLine();
		    			while(choix>7||choix<1) {
		    				System.err.println("#ERROR# donner un entier parmie Les choix disponibles");
		    				choix=SaisirLibrary.controle_Entier();
		    			}
		    			switch (choix) {
						case 1: {
							System.out.println("donner le nouveau nom");
							String ch1=SaisirLibrary.controle_Chaine();
							tabClient[i].setNom(ch1);
							break;
						}
						case 2: {
							System.out.println("donner le nouveau prenom");
							String ch1=SaisirLibrary.controle_Chaine();
							tabClient[i].setPrenom(ch1);
	
							break;
						}
						case 3: {
							System.out.println("donnee la date de naissance**format (jj/mm/aaaa)** \n ");
							String ch1=SaisirLibrary.sc.nextLine();
							while (!GestionClient.date_toString(ch1)){
					            System.err.println("#ERROR# donner une date valide **format (jj/mm/aaaa)**");
					            ch1 = SaisirLibrary.sc.nextLine();
							tabClient[i].setDate(ch1);
							
						         }
							break;
							}
						case 4: {
							System.out.println("donner la nouvelle adress");
							String ch1=SaisirLibrary.controle_Chaine();
							tabClient[i].setDate(ch1);
							break;
						}

						case 5: {
							System.out.println("donner le nouveau num telephone");
							int a=SaisirLibrary.controle_Entier();
							SaisirLibrary.sc.nextLine();
							tabClient[i].setNum_tel(a);
							break;
						}

						case 6: {
							System.out.println("donner le nouvelle etat(s pour suspendu )\n***autre chose considerer comme non suspendu*** ");
							String ch1=SaisirLibrary.controle_Chaine();
							tabClient[i].setSuspendu(Objects.equals(ch1, "s")) ;
						
							break;
						}
						
						
						}
		    			
		    			}
		    		}
			}
		enregistrer();
	
	}
	
	
	public static void DonneLivre() throws ParseException {
		System.out.println("taper le num CIN de client qu'il veut pendre un livre");
		int cin=SaisirLibrary.controle_Entier();
		SaisirLibrary.sc.nextLine();
		Client c = CINClien(cin);
		if(c==null) {
			System.err.println("#ERROR# ce client n'xiste pas dans le SYSTEME ");
		}
		else{if (PeutPrend(c)) {
			System.out.println("taper le ISBN de Livre a Donée");
			int isbn=SaisirLibrary.controle_Entier();
			ArmoireObject a=GestionLibrary.iSBNArmoireObject(isbn);
			if(a==null) {
				System.err.println("#ERROR# ce livre n'existe pas dans le SYSTEME ");
			}

			else {if(GestionLibrary.PeutEtreLivree(a)) {
				for(int i=0;i<GestionLibrary.tablibrary.length;i++) {
					if (GestionLibrary.tablibrary[i].getISBN()==isbn) {
						GestionLibrary.tablibrary[i].setNbr_copies(GestionLibrary.tablibrary[i].getNbr_copies()-1);
						GestionLibrary.enregistrer();
						enregistrer();
					}
				}
				for(int i=0;i<tabClient.length;i++) {
					if (tabClient[i].getCin()==cin) {
						tabClient[i].setLivres(tabClient[i].getLivres()+isbn+"+"+DateAujourdhui()+"#");
					}
				}	
				
				
			}
		}}
	}
		enregistrer();
		GestionLibrary.enregistrer();
	}	
	public static void RecevoireLivre() throws PasDeClientExeption {
		System.out.println("taper le num CIN de client q'il veut retourner un livre");
		int cin=SaisirLibrary.controle_Entier();
		SaisirLibrary.sc.nextLine();
		Client c = CINClien(cin);
		if(c==null) {
			System.err.println("#ERROR# ce client n'xiste pas dans le SYSTEME ");
		}
		else{
			System.out.println("taper le ISBN de Livre a Donée");
			int isbn=SaisirLibrary.controle_Entier();
			ArmoireObject a=GestionLibrary.iSBNArmoireObject(isbn);
			if(a==null) {
				System.err.println("#INTERDIT# ce livre n'existe pas dans le SYSTEME \n tu ne peut pas le rendre ");
			}
			else {
				if(LivreExist(c, isbn)) {
					AnnulerLivre(c, isbn);
					a.setNbr_copies(a.getNbr_copies()+1);
				}
				else {
					System.err.println("#ERROR# ce client n'a pas pris ce livre ");
				}
				
			}

			
		enregistrer();
		GestionLibrary.enregistrer();
	    }	
		
	}

	public static void ajouterClient(Client a) throws ParseException{
		AjoutAbannement(a);
		if(tabClient==null) {
			tabClient = new Client[1];
			tabClient[0] =a;
			
		}
		else {
			Client[]temp = new Client[tabClient.length+1];
			System.arraycopy(tabClient, 0, temp, 0, tabClient.length);
			temp[tabClient.length] = a;
			tabClient = temp;
			temp = null;
			// garbage collector ye5dem 5edemtou marra o5ra hhh 
		}
		enregistrer();
		
	}
	public static void AjoutAbannement(Client c) throws ParseException {
		if( c!=null){	
			if (Objects.equals(c.getAbannement(), "")) {
				
				
				System.out.println("Abannement: mois-5DT-/trimaistre-10DT- (taper m: pour le choix [par mois])\n***autre choix considerer comme [ par trimaistre]***");
				String ch1=SaisirLibrary.controle_Chaine();
				
				
				if(Objects.equals(ch1, "m")) {
					c.setAbannement("m"+"#"+DateAujourdhui());
					GestionLibrary.setRevunu(GestionLibrary.getRevunu()+5);
					enregistrer();
					GestionLibrary.enregistrerR();
				}
				else {
					c.setAbannement("t"+"#"+DateAujourdhui());
					GestionLibrary.setRevunu(GestionLibrary.getRevunu()+10);
					enregistrer();
					GestionLibrary.enregistrerR();
				}
				
				System.out.println("l'Abannement est premium +3DT ? (p pour Abannement premium )\n***autre chose considerer comme [Abannement Normal]*** ");
				String ch2=SaisirLibrary.controle_Chaine();
				boolean premium =(Objects.equals(ch2, "p")) ;
				if (premium){
					c.setPremium(premium);
					GestionLibrary.setRevunu(GestionLibrary.getRevunu()+3);
					enregistrer();
					GestionLibrary.enregistrer();
					GestionLibrary.enregistrerR();
				}
				
			
			}
			
			else {
				String[] tp =separation(c.getAbannement());
				String type=tp[0];
				String date=tp[1];
				if (Objects.equals(type, "m")) {
					if (GestionNotes.DateDifferance(date)<30) {
						System.err.println("#INTERDIT# Client a deja un abannement courant jusqua :"+AjoutJours(date,30));
					}
					else {
						System.out.println("Abannement: mois-5DT-/trimaistre-10DT- (taper m: pour le choix [par mois])\n***autre choix considerer comme [ par trimaistre]***");
						String ch1=SaisirLibrary.controle_Chaine();
						System.err.println(ch1);
						if(Objects.equals(ch1,"m")) {
							c.setAbannement("m"+"#"+DateAujourdhui());
							
							GestionLibrary.setRevunu(GestionLibrary.getRevunu()+5);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();
						}
						else {
							c.setAbannement("t"+"#"+DateAujourdhui());
							
							GestionLibrary.setRevunu(GestionLibrary.getRevunu()+10);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();
						}
						enregistrer();
						GestionLibrary.enregistrerR();
					
						System.out.println("l'Abannement est premium +3DT ? (p pour Abannement premium )\n***autre chose considerer comme [Abannement Normal]*** ");
						String ch2=SaisirLibrary.controle_Chaine();
						boolean premium =(Objects.equals(ch2, "p")) ;
						if (premium){
							c.setPremium(true);
							GestionLibrary.setRevunu(GestionLibrary.getRevunu()+3);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();}
						else {
							c.setPremium(false);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();
						}
					}
				}
				if (Objects.equals(type, "t")) {
					
					if (GestionNotes.DateDifferance(date)<90) {
						System.err.println("#INTERDIT# Client a deja un abannement courant jusqua :"+AjoutJours(date,90));
					}
					else {
						System.out.println("Abannement: mois-5DT-/trimaistre-10DT- (taper m: pour le choix [par mois])\n***autre choix considerer comme [ par trimaistre]***");
						String ch1=SaisirLibrary.controle_Chaine();
						if(Objects.equals(ch1, "t")) {
						c.setAbannement("t"+"#"+DateAujourdhui());
						GestionLibrary.setRevunu(GestionLibrary.getRevunu()+10);
						enregistrer();
						GestionLibrary.enregistrer();
						GestionLibrary.enregistrerR();
						}
						else {
							c.setAbannement("m"+"#"+DateAujourdhui());
							GestionLibrary.setRevunu(GestionLibrary.getRevunu()+5);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();
						}
						System.out.println("l'Abannement est premium +3DT ? (p pour Abannement premium )\n***autre chose considerer comme [Abannement Normal]*** ");
						String ch2=SaisirLibrary.controle_Chaine();
						boolean premium =(Objects.equals(ch2, "p")) ;
						if (premium){
							c.setPremium(premium);
							GestionLibrary.setRevunu(GestionLibrary.getRevunu()+3);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();}
						else {
							c.setPremium(false);
							enregistrer();
							GestionLibrary.enregistrer();
							GestionLibrary.enregistrerR();
						}
					}
				}
				enregistrer();
				GestionLibrary.enregistrer();
				GestionLibrary.enregistrerR();
			}
		}
	}
}
	
