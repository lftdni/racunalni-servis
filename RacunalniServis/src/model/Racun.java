package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Racun {
    
    private int idRacuna;
    private int idZaposlenika;
    private int idKupca;
    private Date datum;
    private int ukupnaCijena;
    private ArrayList<StavkaRacuna> listStavkeRacuna = new ArrayList<StavkaRacuna>();


    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdZaposlenika(int idZaposlenika) {
        this.idZaposlenika = idZaposlenika;
    }

    public int getIdZaposlenika() {
        return idZaposlenika;
    }

    public void setIdKupca(int idKupca) {
        this.idKupca = idKupca;
    }

    public int getIdKupca() {
        return idKupca;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public void setUkupnaCijena(int ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public int getUkupnaCijena() {
        return ukupnaCijena;
    }
    
    public void dodajStavku(StavkaRacuna stavkaRacuna) {
        this.listStavkeRacuna.add(stavkaRacuna);
    }
    
    public Object[] getRedak() {
    	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    	
        return  new Object[] { idRacuna, idZaposlenika, idKupca, dateFormat.format(datum), ukupnaCijena };
    }

    public void setListStavkeRacuna(ArrayList<StavkaRacuna> listStavkeRacuna) {
        this.listStavkeRacuna = listStavkeRacuna;
    }

    public ArrayList<StavkaRacuna> getListStavkeRacuna() {
        return listStavkeRacuna;
    }
}
