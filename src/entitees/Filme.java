package entitees;

import java.util.Objects;

public class Filme extends ArmoireObject {
	private int duree;
	public Filme(String titre, String auteurs, int annee, int iSBN, String description, String type, int nbr_copies,int duree,
			String editeur) {
		super(titre, auteurs, annee, iSBN, description, type, nbr_copies, editeur);
		this.duree=duree;
	}
	

	public Filme(ArmoireObject o, int duree) {
		super(o.getTitre(), o.getAuteurs(), o.getAnnee(),o.getISBN(),o.getDescription(),o.getType(),o.getNbr_copies(),o.getEditeur());
		this.duree = duree;
	}


	

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	@Override
	public String toString() {
		String g=super.toString();
		String f="";
		for (int i = 0; i < g.length(); i++) {
			f+=g.charAt(i);
			if(i==11) {
				f+="\"type\" : \"Filme\",";
			}
		}
		g=f;
		String h="";
		for (int i = 0; i < g.length(); i++) {
			h+=g.charAt(i);
			if(i==11) {
				h+="\"nbr\" : "+duree+",";
			}
		}
		g=h;
		return g ;
		
	}



	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return duree == other.duree;
	}

	public String lire() {
		return super.lire()+
		"-Duree de Filme :  "+duree+"\n"+
		"-> FILME\n"	+
		"*****************************";
	}

}
