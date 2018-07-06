package helper;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import view.ViewAutentikacija;
import view.ViewGlavna;

/**
 * Main klasa koja pokreæe aplikaciju.
 * Prvo se otvara prozora za autentikaciju i tek kada je prijava korisnika
 * uspješna, otvara se glavni prozor ViewGlavna.
 * @author nives
 *
 */
public class Glavna {

    public Glavna() {
    	ViewAutentikacija viewAutentikacija = new ViewAutentikacija(null, "Prijava u sustav", true);
        viewAutentikacija.setLocationRelativeTo(null);
        viewAutentikacija.setVisible(true);
        
        JFrame frame = new ViewGlavna();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    /**
     * Main metoda
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Glavna();
    }
}
