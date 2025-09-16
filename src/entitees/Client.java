package entitees;


public class Client {
	private String nom;
	private String prenom;
	private long cin;
	private String date;
	private String adress;
	private long num_tel;
	private boolean premium;
	private boolean suspendu;
	private String livres;
	private String Abannement;
	

	public Client(String nom, String prenom, long cin, String date, String adress, long num_tel, boolean premium,
			boolean suspendu, String livres,String Abannement ) {
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date = date;
		this.adress = adress;
		this.num_tel = num_tel;
		this.premium = premium;
		this.suspendu = suspendu;
		this.livres = livres;
		this.Abannement=Abannement;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public long getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(long num_tel) {
		this.num_tel = num_tel;
	}
	public boolean getPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public boolean getSuspendu() {
		return suspendu;
	}
	public void setSuspendu(boolean suspendu) {
		this.suspendu = suspendu;
	}
	public String getLivres() {
		return livres;
	}
	public void setLivres(String livres) {
		this.livres = livres;
	}
	
	public String getAbannement() {
		return Abannement;
	}
	public void setAbannement(String abannement) {
		Abannement = abannement;
	}
	@Override
    public String toString() {
        return "{\"Client\": {" +
                "\"cin\" : " + cin  +
                ", \"nom\" : \"" + nom +
                "\", \"prenom\" : \"" +prenom +
                "\", \"date_naissance\" : \"" + date +
                "\", \"adress\" : \"" + adress +
                "\", \"num_tel\" : " + num_tel  +
                ", \"premium\" :" + premium +
                ", \"suspendu\" :" + suspendu +
                ", \"livres\" : \""+livres+
                "\", \"Abannement\" : \""+Abannement+
                "\"}}";
    }
	public String lire() {
	return"*****************************\n"+
			"-CIN: "+cin+"\n"+
			"-NOM : "+nom+"\n"+ 
			"-PRENOM : "+prenom+"\n"+
			"-DATE DE NAISSANCE : "+date+"\n"+
			"-ADRESS: "+adress+"\n"+
			"-NUMERO TELEPHONE : "+num_tel+"\n"+
			"-PREMIUM : "+premium+"\n"+
			"-SUSPENDU: "+suspendu+"\n"+
			"-LIVRES: "+livres+"\n"+
			"-ABNNEMENT: "+Abannement+"\n"+
			"*****************************\n";
	}

}
