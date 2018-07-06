package controller;

import helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Zaposlenik;

/**
 * Kontroler klasa za èitanje, spremanje i brisanje zaposlenika sa baze podataka.
 * @author nives
 *
 */
public class ControllerZaposlenici {

    private ArrayList<Zaposlenik> listZaposlenici = new ArrayList<Zaposlenik>(); 


    /**
     * Metoda za dohvat svih zaposlenika sa baze u obliku liste objekata.
     * @return ArrayList
     */
    public ArrayList<Zaposlenik> dohvatSvihZaposlenika() {
        listZaposlenici.clear();
        try {
            Connection conn = DBHandler.getConnection(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from zaposlenik;");
            while (rs.next()) {
                Zaposlenik zaposlenik = new Zaposlenik();
                zaposlenik.setIdZaposlenika(rs.getInt("idZaposlenika"));
                zaposlenik.setIme(rs.getString("ime"));
                zaposlenik.setPrezime(rs.getString("prezime"));
                zaposlenik.setKorisnickoIme(rs.getString("korisnickoIme"));
                zaposlenik.setLozinka(rs.getString("lozinka"));
                zaposlenik.setMjestoStanovanja(rs.getString("mjestoStanovanja"));
                zaposlenik.setAdministrator(rs.getBoolean("administrator"));
                listZaposlenici.add(zaposlenik);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška kod dohvata zaposlenika.");
            e.printStackTrace();
            return null;
        }
        return listZaposlenici; 
    }
    
    
    /**
     * Metoda za dohvat raèuna s proslijeðenim ID-em.
     * @param idZaposlenika
     * @return Zaposlenik
     */
    public Zaposlenik dohvatZaposlenika(int idZaposlenika) {
        for(Zaposlenik zaposlenik : listZaposlenici) {
            if(zaposlenik.getIdZaposlenika() == idZaposlenika) {
                return zaposlenik;
            }
        }
        return null;
    }
    
    
    /**
     * Sprema objekt zaposlenika kao novi redak u bazi.
     * @param zaposlenik
     */
    public void spremiZaposlenika(Zaposlenik zaposlenik) {        
        String sql = "insert into zaposlenik (ime, prezime, korisnickoIme, lozinka, mjestoStanovanja, administrator) values (?, ?, ?, ?, ?, ?);";
        Connection conn = DBHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, zaposlenik.getIme());
            ps.setString(2, zaposlenik.getPrezime());
            ps.setString(3, zaposlenik.getKorisnickoIme());
            ps.setString(4, zaposlenik.getLozinka());
            ps.setString(5, zaposlenik.getMjestoStanovanja());
            ps.setBoolean(6, zaposlenik.isAdministrator());
            ps.execute();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Greška kod spremanja zaposlenika.");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Briše zaposlenika sa proslijeðenim ID-em.
     * @param idZaposlenika
     */
    public void brisanjeZaposlenika(int idZaposlenika) {
        DBHandler.brisanje("delete from zaposlenik where idZaposlenika = ?;", idZaposlenika);
    }
    
    
    /**
     * Metoda prima korisnièo ime i lozinku te provjerava postoji li zaposlenik 
     * s tom kombinacijom. Ako da, prijava u sustav je uspješna i pronaðeni zaposlenik
     * postaje korisnik sustava. Ovdje se bilježi njegova uloga (da li je administrator
     * ili ne) u globalnu varijablu DBHandler.administrator (vidljivu u svim klasama).
     * U globalno varijablu DBHandler.idZaposlenika postavlja se njegov ID i on se koristi
     * u nekim od ostalih prozora prilikom spremanja podataka.
     * @param korisnickoIme
     * @param lozinka
     * @return true za uspjeh, false za neuspjeh
     */
    public boolean prijava(String korisnickoIme, String lozinka) {
        for(Zaposlenik zaposlenik : listZaposlenici) {
            if(zaposlenik.getKorisnickoIme().equals(korisnickoIme) && zaposlenik.getLozinka().equals(lozinka)) {
                DBHandler.idZaposlenika = zaposlenik.getIdZaposlenika();
                DBHandler.administrator = zaposlenik.isAdministrator();
                return true;
            }
        }
        return false;
    }
}
