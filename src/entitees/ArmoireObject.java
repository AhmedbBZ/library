package entitees;
import java.util.Objects;

public class ArmoireObject {
	public ArmoireObject() {
		super();
	}

	private  String titre;
	private String auteurs;
	private int annee;
	private int ISBN;
	private String description;
	private String type;
	private int nbr_copies;
	private String editeur;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteurs() {
		return auteurs;
	}
	public void setAuteurs(String auteurs) {
		this.auteurs = auteurs;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNbr_copies() {
		return nbr_copies;
	}
	public void setNbr_copies(int nbr_copies) {
		this.nbr_copies = nbr_copies;
	}
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}
	public ArmoireObject(String titre, String auteurs, int annee, int iSBN, String description, String type,
			int nbr_copies, String editeur) {

		this.titre = titre;
		this.auteurs = auteurs;
		this.annee = annee;
		ISBN = iSBN;
		this.description = description;
		this.type = type;
		this.nbr_copies = nbr_copies;
		this.editeur = editeur;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArmoireObject other = (ArmoireObject) obj;
		return ISBN == other.ISBN && annee == other.annee && Objects.equals(auteurs, other.auteurs)
				&& Objects.equals(description, other.description) && Objects.equals(editeur, other.editeur)
				&& nbr_copies == other.nbr_copies && Objects.equals(titre, other.titre)
				&& Objects.equals(type, other.type);
	}
	@Override
    public String toString() {
        return "{\"Entite\": {" +
                "\"titre\" : \"" + titre  +
                "\", \"auteur\" : \"" + auteurs +
                "\", \"anne\" : " +annee +
                ", \"ISBN\" : " + ISBN +
                ", \"description\" : \"" + description  +
                "\", \"genre\" : \"" + type  +
                "\", \"nbr_copie\" :" + nbr_copies +
                ", \"editeur\" :\"" + editeur +
                "\"}}";
    }
	public String lire() {
	return"*****************************\n"+
			"-ISBN: "+ISBN+"\n"+
			"-Titre : "+titre+"\n"+ 
			"-Auteur : "+auteurs+"\n"+
			"-Editeur : "+editeur+"\n"+
			"-Genre : "+type+"\n"+
			"-Description : "+description+"\n"+
			"-Nombre de copies : "+nbr_copies+"\n"+
			"-Annee de production : "+annee+"\n";
	}
   
}
