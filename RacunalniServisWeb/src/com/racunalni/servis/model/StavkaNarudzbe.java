package com.racunalni.servis.model;

public class StavkaNarudzbe {
    
    private int idStavkeNarudzbe;
    private int idNarudzbe;
    private int idKomponenteUsluge;
    private int kolicina;


    public void setIdStavkeNarudzbe(int idStavkeNarudzbe) {
        this.idStavkeNarudzbe = idStavkeNarudzbe;
    }

    public int getIdStavkeNarudzbe() {
        return idStavkeNarudzbe;
    }

    public void setIdNarudzbe(int idNarudzbe) {
        this.idNarudzbe = idNarudzbe;
    }

    public int getIdNarudzbe() {
        return idNarudzbe;
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
        return new Object[] { idStavkeNarudzbe, idKomponenteUsluge, kolicina };
    }
}
