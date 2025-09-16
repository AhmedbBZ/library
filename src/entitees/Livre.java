package entitees;


public class Livre extends ArmoireObject{
	private int nbr_pages;
	
	
	public Livre(String titre, String auteurs, int annee, int iSBN, String description, String type, int nbr_copies,int nbr_pages,
			String editeur) {
		super(titre, auteurs, annee, iSBN, description, type, nbr_copies, editeur);
		this.nbr_pages=nbr_pages;
	}
	public Livre(ArmoireObject o, int nbr_pages) {
		super(o.getTitre(), o.getAuteurs(), o.getAnnee(),o.getISBN(),o.getDescription(),o.getType(),o.getNbr_copies(),o.getEditeur());
		this.nbr_pages = nbr_pages;
	}
	public int getNbr_pages() {
		return nbr_pages;
	}
	public void setNbr_pages(int nbr_pages) {
		this.nbr_pages = nbr_pages;
	}
	@Override
	public String toString() {
		String g=super.toString();
		String f="";
		for (int i = 0; i < g.length(); i++) {
			f+=g.charAt(i);
			if(i==11) {
				f+="\"type\" : \"Livre\",";
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		return nbr_pages == other.nbr_pages;
	}

		public String lire() {
			return super.lire()+
			"-NOMBRE DE PAGES : "+nbr_pages+"\n"+
			"-> LIVRE\n"	+
			"*****************************";
		}
}