package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.StavkaRacuna;

/**
 * Kontroler klasa za èitanje, spremanje i brisanje stavki raèuna sa baze podataka.
 * @author nives
 *
 */
public class ControllerStavkeRacuna {


	/**
     * Metoda za dohvat svih stavki proslijeðenog raèuna sa baze u obliku liste objekata.
     * @return ArrayList
     */
    public ArrayList<StavkaRacuna> dohvatStavkiRacuna(int idRacuna) {
        ArrayList<StavkaRacuna> listStavkeRacuna = new ArrayList<StavkaRacuna>(); 
        try {
            Connection conn = DBHandler.getConnection(); 
            String sql = "select * from stavkeRacuna where idRacuna = ?;";
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, idRacuna);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StavkaRacuna stavkaRacuna = new StavkaRacuna();
                stavkaRacuna.setIdStavkeRacuna(rs.getInt("idStavkeRacuna"));
                stavkaRacuna.setIdRacuna(rs.getInt("idRacuna"));
                stavkaRacuna.setIdKomponenteUsluge(rs.getInt("idKomponenteUsluge"));
                stavkaRacuna.setKolicina(rs.getInt("kolicina"));
                
                listStavkeRacuna.add(stavkaRacuna);//dodaj u listu
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška kod dohvata stavki raèuna.");
            e.printStackTrace();
            return null;
        }
        return listStavkeRacuna; 
    }
    
        
    /**
     * Sprema objekt stavke raèuna kao novi redak u bazi.
     * @param stavkaRacuna
     */
    public void spremiStavkuRacuna(StavkaRacuna stavkaRacuna) {        
        String sql = "insert into stavkeRacuna (idRacuna, idKomponenteUsluge, kolicina) values (?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stavkaRacuna.getIdRacuna());
            ps.setInt(2, stavkaRacuna.getIdKomponenteUsluge());
            ps.setInt(3, stavkaRacuna.getKolicina());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Greška kod spremanja stavke raèuna.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Briše stavku raèuna sa proslijeðenim ID-em.
     * @param idStavkeRacuna
     */
    public void brisanjeStavkeRacuna(int idStavkeRacuna) {
        DBHandler.brisanje("delete from stavkeRacuna where idStavkeRacuna = ?;", idStavkeRacuna);
    }
}
