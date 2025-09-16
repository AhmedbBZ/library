package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class GestionNotes {
	public static String[] nots = new String[0];
	
	
	 static public long DateDifferance(String date) throws ParseException {
		 DateTimeFormatter x=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 String l=x.format(LocalDateTime.now());
		 SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
		 long a=s.parse(l).getTime()-s.parse(date).getTime();
		 return TimeUnit.DAYS.convert(a,TimeUnit.MILLISECONDS);
	}
	 
	 
	 
	 public static void Notification() throws ParseException {
		 if(GestionClient.tabClient==null) {
			 System.err.println("#ERROR#votre liste de clients est vide");
		 }
		 else {
				for (int i=0;i<GestionClient.tabClient.length;i++) {
					System.out.println(GestionClient.tabClient[i].getLivres());
					String[]tp=GestionClient.separation(GestionClient.tabClient[i].getLivres());
					
					for(int j=0;j<tp.length;j++) {	
						if(tp[j]!="") {
							if(DateDifferance(GestionClient.separationDate(tp[j]))<14) {
								//System.out.println("No . . .");
							}
							if(DateDifferance(GestionClient.separationDate(tp[j]))==13) {
								//System.out.print("here");
								
								String[]ch= new String[nots.length+1];
								System.arraycopy(nots, 0, ch, 0, nots.length);
								ch[nots.length]="*"+GestionClient.DateAujourdhui()+"*#ATTENTION le client de CIN :"+GestionClient.tabClient[i].getCin()+
								":\n doit retourner le livre de ISBN :"+(String) tp[j].substring(0,tp[j].indexOf("+"))+" dans un jour\n";
								nots=ch;
								GestionClient.enregistrer();
							}
							if(DateDifferance(GestionClient.separationDate(tp[j]))==14) {
								//System.out.print("here");
								
								String[]ch= new String[nots.length+1];
								System.arraycopy(nots, 0, ch, 0, nots.length);
								ch[nots.length]="*"+GestionClient.DateAujourdhui()+"*#ATTENTION le client de CIN :"+GestionClient.tabClient[i].getCin()+
								":\n doit retourner le livre de ISBN :"+(String) tp[j].substring(0,tp[j].indexOf("+"))+" Aujourd'huis\n";
								nots=ch;
								GestionClient.enregistrer();
							}
							if(DateDifferance(GestionClient.separationDate(tp[j]))>14) {
								//System.out.print("here");
								
								String[]ch= new String[nots.length+1];
								System.arraycopy(nots, 0, ch, 0, nots.length);
								ch[nots.length]="*"+GestionClient.DateAujourdhui()+"*#ATTENTION le client de CIN :"+GestionClient.tabClient[i].getCin()+
								":\n Est deja retard de retourner le livre de ISBN :"+(String) tp[j].substring(0,tp[j].indexOf("+"))+"\n Il est Automatiquement BLOQUE maintenent \n";
								GestionClient.tabClient[i].setSuspendu(true);
								nots=ch;
								GestionClient.enregistrer();
							}
							
						}}
					}	  
				
		}
	  }
	 public static void NotifficationsToString() throws ParseException {
		 	Notification();
		 	System.out.println(nots.length);
		 	if(nots==null) {
		 		System.out.println("****************************************************************************************************************\n");
		 		System.out.println("Pas DE NOTIFFICATIONS");
		 		System.out.println("****************************************************************************************************************\n");
		 	}
		 	else{
		 		System.out.println("****************************************************************************************************************\n");	
		 		for(int i=0;i<nots.length;i++) {
		 		System.out.println(nots[i]+"\n");
		 	    }
		 	System.out.println("****************************************************************************************************************\n");
		 	}
	 }


}


