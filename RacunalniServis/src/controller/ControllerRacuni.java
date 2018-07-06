package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Racun;

/**
 * Kontroler klasa za �itanje, spremanje i brisanje ra�una sa baze podataka.
 * @author nives
 *
 */
public class ControllerRacuni {

    private ControllerStavkeRacuna controllerStavkeRacuna = new ControllerStavkeRacuna();
    private ArrayList<Racun> listRacuni = new ArrayList<Racun>(); 


    /**
     * Metoda za dohvat svih ra�una sa baze u obliku liste objekata.
     * Za svaki ra�un se dohvati i lista njegovih stavki preko kontrolera
     * za stavke ra�una.
     * @return ArrayList
     */
    public ArrayList<Racun> dohvatSvihRacuna() {
        listRacuni.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from racun;");
            while (rs.next()) {
                Racun racun = new Racun();
                racun.setIdRacuna(rs.getInt("idRacuna"));
                racun.setIdKupca(rs.getInt("idKupca"));
                racun.setIdZaposlenika(rs.getInt("idZaposlenika"));
                racun.setDatum(rs.getDate("datum"));
                racun.setUkupnaCijena(rs.getInt("ukupnaCijena"));
                racun.setListStavkeRacuna(controllerStavkeRacuna.dohvatStavkiRacuna(racun.getIdRacuna()));
                
                listRacuni.add(racun); //dodavanje u listu
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod dohvata ra�una.");
            e.printStackTrace();
            return null;
        }
        return listRacuni; 
    }
    
    
    /**
     * Metoda za dohvat ra�una s proslije�enim ID-em.
     * @param idRacuna
     * @return Racun
     */
    public Racun dohvatRacuna(int idRacuna) {
        for(Racun racun : listRacuni) {
            if(racun.getIdRacuna() == idRacuna) {
                return racun;
            }
        }
        return null;
    }
    
    
    /**
     * Sprema objekt ra�una kao novi redak u bazi.
     * @param racun
     */
    public void spremiRacun(Racun racun) {        
        String sql = "insert into racun (idKupca, idZaposlenika, datum, ukupnaCijena) values (?, ?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, racun.getIdKupca());
            ps.setInt(2, racun.getIdZaposlenika());
            ps.setDate(3, racun.getDatum());
            ps.setInt(4, racun.getUkupnaCijena());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod spremanja ra�una.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Izra�unava vrijednost polja ukupnaCijena na ra�unu.
     * Formula je: pomno�i cijenu svake komponenteUsluge na stavci sa njezinom koli�inom
     * i sve skupa zbroji. Rezultat se update u redak ra�una s proslije�enim ID-em.
     * @param idRacuna
     */
    public void izracunUkupneCijene(int idRacuna) {        
        String sql = "update racun set ukupnaCijena = " +
        		     "(select sum(kolicina * cijena) from stavkeRacuna s, komponenteUsluge k where s.idKomponenteUsluge = k.idKomponenteUsluge and s.idRacuna = ?) " +
        		     "where idRacuna = ?;";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idRacuna);
            ps.setInt(2, idRacuna);
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod izra�una ukupne cijene ra�una.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Dohva�a vrijednost polja ukupnaCijena iz redka ra�una s proslije�enim ID-em.
     * @param idRacuna
     */
    public int dohvatUkupneCijene(int idRacuna) {
    	int ukupnaCijena = 0;
    	
    	String sql = "select ukupnaCijena from racun where idRacuna = ?;";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idRacuna);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	ukupnaCijena = rs.getInt("ukupnaCijena");
            }
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod dohvata ukupne cijene ra�una.");
            e.printStackTrace();
            return 0;
        }
        return ukupnaCijena;
    }
    
    
    /**
     * Bri�e ra�un sa proslije�enim ID-em.
     * @param idRacuna
     */
    public void brisanjeRacuna(int idRacuna) {
        DBHandler.brisanje("delete from racun where idRacuna = ?;", idRacuna);
    }
}
