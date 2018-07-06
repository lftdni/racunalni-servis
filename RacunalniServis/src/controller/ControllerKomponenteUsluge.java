package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.KomponentaUsluga;

/**
 * Kontroler klasa za èitanje, spremanje i brisanje komponenti/usluga sa baze podataka.
 * @author nives
 *
 */
public class ControllerKomponenteUsluge {

    private ArrayList<KomponentaUsluga> listKomponenteUsluge = new ArrayList<KomponentaUsluga>(); 


    /**
     * Metoda za dohvat svih komponenti/usluga sa baze u obliku liste objekata.
     * @return ArrayList
     */
    public ArrayList<KomponentaUsluga> dohvatSvihKomponentaUsluga() {
        listKomponenteUsluge.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from komponenteUsluge;");
            while (rs.next()) {
                KomponentaUsluga komponentaUsluga = new KomponentaUsluga();
                komponentaUsluga.setIdKomponenteUsluge(rs.getInt("idKomponenteUsluge"));
                komponentaUsluga.setIdZaposlenika(rs.getInt("idZaposlenika"));
                komponentaUsluga.setNaziv(rs.getString("naziv"));
                komponentaUsluga.setKratkiOpis(rs.getString("kratkiOpis"));
                komponentaUsluga.setCijena(rs.getInt("cijena"));
                listKomponenteUsluge.add(komponentaUsluga);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška kod dohvata komponenta/usluga.");
            e.printStackTrace();
            return null;
        }
        return listKomponenteUsluge; 
    }
    
    
    /**
     * Metoda za dohvat komponente/usluge s proslijeðenim ID-em.
     * @param idKomponentaUsluga
     * @return KomponentaUsluga
     */
    public KomponentaUsluga dohvatKomponenteUsluge(int idKomponentaUsluga) {
        for(KomponentaUsluga komponentaUsluga : listKomponenteUsluge) {
            if(komponentaUsluga.getIdKomponenteUsluge() == idKomponentaUsluga) {
                return komponentaUsluga;
            }
        }
        return null;
    }
    
    
    /**
     * Sprema objekt komponente/usluge kao novi redak u bazi.
     * @param komponentaUsluga
     */
    public void spremiKomponentuUslugu(KomponentaUsluga komponentaUsluga) {        
        String sql = "insert into komponenteUsluge (idZaposlenika, naziv, kratkiOpis, cijena) values (?, ?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, komponentaUsluga.getIdZaposlenika());
            ps.setString(2, komponentaUsluga.getNaziv());
            ps.setString(3, komponentaUsluga.getKratkiOpis());
            ps.setInt(4, komponentaUsluga.getCijena());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Greška kod spremanja komponente/usluge.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Briše komponentu/uslugu sa proslijeðenim ID-em.
     * @param idKomponentaUsluga
     */
    public void brisanjeKomponenteUsluge(int idKomponentaUsluga) {
        DBHandler.brisanje("delete from komponenteUsluge where idKomponenteUsluge = ?;", idKomponentaUsluga);
    }
}
