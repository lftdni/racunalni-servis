package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Narudzba;

/**
 * Kontroler klasa za �itanje, spremanje i brisanje narud�be sa baze podataka.
 * @author nives
 *
 */
public class ControllerNarudzbe {

    private ControllerStavkeNarudzbe controllerStavkeNarudzbe = new ControllerStavkeNarudzbe();
    private ArrayList<Narudzba> listNarudzbe = new ArrayList<Narudzba>(); 


    /**
     * Metoda za dohvat svih narud�bi sa baze u obliku liste objekata.
     * Za svaku narud�bu se dohvati i lista njenih stavki preko kontrolera
     * za stavke narud�bi.
     * @return ArrayList
     */
    
    public ArrayList<Narudzba> dohvatSvihNarudzbi() {
        listNarudzbe.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from narudzba;");
            while (rs.next()) {
                Narudzba narudzba = new Narudzba();
                narudzba.setIdNarudzbe(rs.getInt("idNarudzbe"));
                narudzba.setIdKupca(rs.getInt("idKupca"));
                narudzba.setDatum(rs.getDate("datum"));
                narudzba.setListStavkeNarudzbe(controllerStavkeNarudzbe.dohvatStavkiNarudzbe(narudzba.getIdNarudzbe()));
                listNarudzbe.add(narudzba);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod dohvata narud�bi.");
            e.printStackTrace();
            return null;
        }
        return listNarudzbe; 
    }
    
    
    /**
     * Metoda za dohvat narud�be s proslije�enim ID-em.
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
     * Sprema objekt narud�be kao novi redak u bazi.
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
            JOptionPane.showMessageDialog(null, "Gre�ka kod spremanja narud�be.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Bri�e narud�bu sa proslije�enim ID-em.
     * @param idNarudzbe
     */
    public void brisanjeNarudzbe(int idNarudzbe) {
    	
        DBHandler.brisanje("delete from narudzba where idNarudzbe = ?;", idNarudzbe);
    }
}
