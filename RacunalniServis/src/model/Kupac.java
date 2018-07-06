package model;

public class Kupac {
    
    private int idKupca;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    
    public void setIdKupca(int idKupca) {
        this.idKupca = idKupca;
    }

    public int getIdKupca() {
        return idKupca;
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
    
    public Object[] getRedakTablice() {
        return new Object[] { idKupca, ime, prezime, korisnickoIme, lozinka };
    }
    
    public String toString() {
    	return ime + " " + prezime;
    }
}
