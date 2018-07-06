package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.StavkaNarudzbe;

/**
 * Kontroler klasa za �itanje, spremanje i brisanje stavki narud�be sa baze podataka.
 * @author nives
 *
 */
public class ControllerStavkeNarudzbe {
	/**
     * Metoda za dohvat svih stavki proslije�ene narud�be sa baze u obliku liste objekata.
     * @return ArrayList
     */
	
    public ArrayList<StavkaNarudzbe> dohvatStavkiNarudzbe(int idNarudzbe) {
        ArrayList<StavkaNarudzbe> listStavkeNarudzbe = new ArrayList<StavkaNarudzbe>(); 
        try {
            Connection conn = DBHandler.getConnection(); 
            String sql = "select * from stavkeNarudzbe where idNarudzbe = ?;";
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, idNarudzbe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StavkaNarudzbe stavkaNarudzbe = new StavkaNarudzbe();
                stavkaNarudzbe.setIdStavkeNarudzbe(rs.getInt("idStavkeNarudzbe"));
                stavkaNarudzbe.setIdNarudzbe(rs.getInt("idNarudzbe"));
                stavkaNarudzbe.setIdKomponenteUsluge(rs.getInt("idKomponenteUsluge"));
                stavkaNarudzbe.setKolicina(rs.getInt("kolicina"));
                listStavkeNarudzbe.add(stavkaNarudzbe);
            }
            rs.close();
            ps.close();
            
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod dohvata stavki narud�be.");
            e.printStackTrace();
            return null;
        }
        return listStavkeNarudzbe; 
    }
    
        
    /**
     * Spremanje objekta stavke narud�be 
     * @param stavkaNarudzbe
     */
    public void spremiStavkuNarudzbu(StavkaNarudzbe stavkaNarudzbe) {        
        String sql = "insert into stavkeNarudzbe (idNarudzbe, idKomponenteUsluge, kolicina) values (?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stavkaNarudzbe.getIdNarudzbe());
            ps.setInt(2, stavkaNarudzbe.getIdKomponenteUsluge());
            ps.setInt(3, stavkaNarudzbe.getKolicina());
            
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Gre�ka kod spremanja stavke narud�be.");
            e.printStackTrace();
        }
    }    
    
    /**
     * Brisanje stavke narud�be sa proslije�enim ID-em
     * @param idStavkeNarudzbe
     */
    
    public void brisanjeStavkeNarudzbe(int idStavkeNarudzbe) {
        DBHandler.brisanje("delete from stavkeNarudzbe where idStavkeNarudzbe = ?", idStavkeNarudzbe);
    }
}
