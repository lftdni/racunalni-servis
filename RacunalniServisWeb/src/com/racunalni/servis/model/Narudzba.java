package com.racunalni.servis.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Narudzba {
    
    private int idNarudzbe;
    private int idKupca;
    private Date datum;
    private ArrayList<StavkaNarudzbe> listStavkeNarudzbe = new ArrayList<StavkaNarudzbe>();


    public void setIdNarudzbe(int idNarudzbe) {
        this.idNarudzbe = idNarudzbe;
    }

    public int getIdNarudzbe() {
        return idNarudzbe;
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

    public void dodajStavku(StavkaNarudzbe stavkaNarudzbe) {
        this.listStavkeNarudzbe.add(stavkaNarudzbe);
    }

    public void setListStavkeNarudzbe(ArrayList<StavkaNarudzbe> listStavkeNarudzbe) {
        this.listStavkeNarudzbe = listStavkeNarudzbe;
    }
    
    public ArrayList<StavkaNarudzbe> getListStavkeNarudzbe() {
        return listStavkeNarudzbe;
    }

    public Object[] getRedak() {
    	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    	
        return new Object[] { idNarudzbe, idKupca, dateFormat.format(datum) };
    }
}
