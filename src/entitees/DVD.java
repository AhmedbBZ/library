package entitees;

import java.util.Objects;

public class DVD extends ArmoireObject {
	private int taille;
	public DVD(String titre, String auteurs, int annee, int iSBN, String description, String type, int nbr_copies,
			int taille,String editeur) {
		super(titre, auteurs, annee, iSBN, description, type, nbr_copies, editeur);	
		this.taille=taille;
	}
	
	public DVD(ArmoireObject o, int taille) {
		super(o.getTitre(), o.getAuteurs(), o.getAnnee(),o.getISBN(),o.getDescription(),o.getType(),o.getNbr_copies(),o.getEditeur());
		this.taille = taille;
	}	
	

	

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	@Override
	public String toString() {
		String g=super.toString();
		String f="";
		for (int i = 0; i < g.length(); i++) {
			f+=g.charAt(i);
			if(i==11) {
				f+="\"type\" : \"DVD\",";
			}
		}
		g=f;
		String h="";
		for (int i = 0; i < g.length(); i++) {
			h+=g.charAt(i);
			if(i==11) {
				h+="\"nbr\" : "+taille+",";
			}
		}
		g=h;
		return g ;
		
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DVD other = (DVD) obj;
		return taille == other.taille;
	}
	@Override
	public String lire() {
		return super.lire()+
		"-Taille de DVD :  "+taille+"\n"+
		"-> DVD\n"	+
		"*****************************";
	}
}
