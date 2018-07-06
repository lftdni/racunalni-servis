package model;

public class Zaposlenik {

    private int idZaposlenika;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String mjestoStanovanja;
    private boolean administrator;
    
    public void setIdZaposlenika(int idZaposlenika) {
        this.idZaposlenika = idZaposlenika;
    }

    public int getIdZaposlenika() {
        return idZaposlenika;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setMjestoStanovanja(String mjestoStanovanja) {
        this.mjestoStanovanja = mjestoStanovanja;
    }

    public String getMjestoStanovanja() {
        return mjestoStanovanja;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isAdministrator() {
        return administrator;
    }
    
    public Object[] getRedakTablice() {
    	String isAdmin = "Ne";
    	if(administrator) {
    		isAdmin = "Da";
    	}
        return new Object[] { idZaposlenika, ime, prezime, korisnickoIme, lozinka, mjestoStanovanja, isAdmin };
    }
}
