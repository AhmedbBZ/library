package entitees;

import java.util.Objects;

public class Magazine extends ArmoireObject{
	int nbr_pages;

	public Magazine(String titre, String auteurs, int annee, int iSBN, String description, String type,
			int nbr_copies, int nbr_pages,String editeur) {
		super(titre, auteurs, annee, iSBN, description, type, nbr_copies, editeur);
		this.nbr_pages=nbr_pages;
	}

	public Magazine(ArmoireObject o, int nbr_pages) {
		super(o.getTitre(), o.getAuteurs(), o.getAnnee(),o.getISBN(),o.getDescription(),o.getType(),o.getNbr_copies(),o.getEditeur());
		this.nbr_pages=nbr_pages;
		
	}
	

	public int getNbr_pages() {
		return nbr_pages;
	}

	public void setNbr_pages(int nbr_pages) {
		this.nbr_pages = nbr_pages;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazine other = (Magazine) obj;
		return nbr_pages == other.nbr_pages;
	}

	@Override
	public String toString() {
		String g=super.toString();
		String f="";
		for (int i = 0; i < g.length(); i++) {
			f+=g.charAt(i);
			if(i==11) {
				f+="\"type\" : \"Magazine\",";
			}
		}
		g=f;
		String h="";
		for (int i = 0; i < g.length(); i++) {
			h+=g.charAt(i);
			if(i==11) {
				h+="\"nbr\" : "+nbr_pages+",";
			}
		}
		g=h;
		return g ;
		
	}
	
	public String lire() {
		return super.lire()+
		"-Nombre des Pages : "+nbr_pages+"\n"+
		"-> MAGAZINE\n"	+
		"*****************************";
	}
}