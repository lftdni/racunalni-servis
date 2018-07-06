package com.racunalni.servis.controller;

import com.racunalni.servis.helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.racunalni.servis.model.Narudzba;

/**
 * Kontroler klasa za èitanje, spremanje i brisanje narudžbe sa baze podataka.
 * @author nives
 *
 */
public class ControllerNarudzbe {

    private ControllerStavkeNarudzbe controllerStavkeNarudzbe = new ControllerStavkeNarudzbe();
    private ArrayList<Narudzba> listNarudzbe = new ArrayList<Narudzba>(); 


    /**
     * Metoda za dohvat svih narudžbi sa baze u obliku liste objekata.
     * Za svaku narudžbu se dohvati i lista njenih stavki preko kontrolera
     * za stavke narudžbi.
     * @return ArrayList
     */
    public ArrayList<Narudzba> dohvatNarudzbiKupca(int idKupca) {
        listNarudzbe.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            String sql = "select * from narudzba where idKupca = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKupca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Narudzba narudzba = new Narudzba();
                narudzba.setIdNarudzbe(rs.getInt("idNarudzbe"));
                narudzba.setIdKupca(rs.getInt("idKupca"));
                narudzba.setDatum(rs.getDate("datum"));
                narudzba.setListStavkeNarudzbe(controllerStavkeNarudzbe.dohvatStavkiNarudzbe(narudzba.getIdNarudzbe()));
                listNarudzbe.add(narudzba);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNarudzbe; 
    }
    
    
    /**
     * Metoda za dohvat narudžbe s proslijeðenim ID-em.
     * @param idNarudzbe
     * @return Narudzba
     */
    public Narudzba dohvatNarudzbe(int idNarudzbe) {
        for(Narudzba narudzba : listNarudzbe) {
            if(narudzba.getIdNarudzbe() == idNarudzbe) {
                return narudzba;
            }
        }
        return null;
    }
    
    
    /**
     * Sprema objekt narudžbe kao novi redak u bazi.
     * @param narudzba
     */
    public void spremiNarudzbu(Narudzba narudzba) {        
        String sql = "insert into narudzba (idKupca, datum) values (?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, narudzba.getIdKupca());
            ps.setDate(2, narudzba.getDatum());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Briše narudžbu sa proslijeðenim ID-em.
     * @param idNarudzbe
     */
    public void brisanjeNarudzbe(int idNarudzbe) {
        DBHandler.brisanje("delete from narudzba where idNarudzbe = ?;", idNarudzbe);
    }
}
