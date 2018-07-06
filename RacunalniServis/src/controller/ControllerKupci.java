package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Kupac;

/**
 * Kontroler klasa za èitanje, spremanje i brisanje kupaca sa baze podataka.
 * @author nives
 *
 */
public class ControllerKupci {

    private ArrayList<Kupac> listKupci = new ArrayList<Kupac>(); 


    /**
     * Metoda za dohvat svih kupaca sa baze u obliku liste objekata.
     * @return ArrayList
     */
    public ArrayList<Kupac> dohvatSvihKupaca() {
        listKupci.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from kupac;");
            while (rs.next()) {
                Kupac kupac = new Kupac();
                kupac.setIdKupca(rs.getInt("idKupca"));
                kupac.setIme(rs.getString("ime"));
                kupac.setPrezime(rs.getString("prezime"));
                kupac.setKorisnickoIme(rs.getString("korisnickoIme"));
                kupac.setLozinka(rs.getString("lozinka"));
                
                listKupci.add(kupac); //DODAJ U LISTU
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška kod dohvata kupaca.");
            e.printStackTrace();
            return null;
        }
        return listKupci; 
    }
    
    
    /**
     * Metoda za dohvat kupca s proslijeðenim ID-em.
     * @param idKupca
     * @return Kupac
     */
    public Kupac dohvatKupca(int idKupca) {
        for(Kupac Kupac : listKupci) {
            if(Kupac.getIdKupca() == idKupca) {
                return Kupac;
            }
        }
        return null;
    }
    
    
    /**
     * Sprema objekt kupca kao novi redak u bazi.
     * @param kupac
     */
    public void spremiKupca(Kupac kupac) {        
        String sql = "insert into kupac (ime, prezime, korisnickoIme, lozinka) values (?, ?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kupac.getIme());
            ps.setString(2, kupac.getPrezime());
            ps.setString(3, kupac.getKorisnickoIme());
            ps.setString(4, kupac.getLozinka());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Greška kod spremanja kupca.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Briše kupca sa proslijeðenim ID-em.
     * @param idKupca
     */
    public void brisanjeKupca(int idKupca) {
        DBHandler.brisanje("delete from kupac where idKupca = ?;", idKupca);
    }
}
