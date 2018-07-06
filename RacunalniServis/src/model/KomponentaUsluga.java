package model;

public class KomponentaUsluga {
    
    private int idKomponenteUsluge;
    private int idZaposlenika;
    private String naziv;
    private String kratkiOpis;
    private int cijena;


    public void setIdKomponenteUsluge(int idKomponenteUsluge) {
        this.idKomponenteUsluge = idKomponenteUsluge;
    }

    public int getIdKomponenteUsluge() {
        return idKomponenteUsluge;
    }

    public void setIdZaposlenika(int idZaposlenika) {
        this.idZaposlenika = idZaposlenika;
    }

    public int getIdZaposlenika() {
        return idZaposlenika;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setKratkiOpis(String kratkiOpis) {
        this.kratkiOpis = kratkiOpis;
    }

    public String getKratkiOpis() {
        return kratkiOpis;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getCijena() {
        return cijena;
    }
    
    public Object[] getRedak() {
        return new Object[] { idKomponenteUsluge, idZaposlenika, naziv, kratkiOpis, cijena };
    }
    
    public String toString() {
    	return naziv;
    }
}
