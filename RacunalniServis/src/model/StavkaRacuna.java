package model;

public class StavkaRacuna {
    
    private int idStavkeRacuna;
    private int idRacuna;
    private int idKomponenteUsluge;
    private int kolicina;


    public void setIdStavkeRacuna(int idStavkeRacuna) {
        this.idStavkeRacuna = idStavkeRacuna;
    }

    public int getIdStavkeRacuna() {
        return idStavkeRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdKomponenteUsluge(int idKomponenteUsluge) {
        this.idKomponenteUsluge = idKomponenteUsluge;
    }

    public int getIdKomponenteUsluge() {
        return idKomponenteUsluge;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getKolicina() {
        return kolicina;
    }
    
    public Object[] getRedak() {
        return new Object[] { idStavkeRacuna, idKomponenteUsluge, kolicina };
    }
}
