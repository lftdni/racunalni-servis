package helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

/**
 * Klasa DHandler sadrži metodu za komunikaciju i spajanje sa bazom.
 * Tu su definirani i parametri za spajanje na bazu, kao i globalne
 * varijable za idZaposlenik i administratorsku ulogu trenutno ulogiranog 
 * korisnika. Èesto korištena metoda za brisanje iz baze je takoðer tu.
 * @author nives
 *
 */
public class DBHandler {

    private static final String SERVER = "localhost";
    private static final String DB_NAME = "racunalni_servis";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static int idZaposlenika;
    public static boolean administrator;


    /**
     * Metoda se spaja na MySQL bazu podataka sa zadanim parametrima i vraæa objekt
     * konekcije s kojim kontroler metode rade.
     * @return Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try { 
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":3306/" + DB_NAME, USERNAME, PASSWORD); //spajanje: (hostname/baza,user,pass)
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return conn;
    }
        
    
    
    /**
     * Metoda za brisanje prima sql string u obliku:
     * delete from tavlica where id = ?
     * Na mjesto upitnika upisuje se ID retka za brisanje.
     * @param sql
     * @param id
     */
    public static void brisanje(String sql, int id) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška prilikom brisanja.");
            e.printStackTrace();
        }
    }
}
