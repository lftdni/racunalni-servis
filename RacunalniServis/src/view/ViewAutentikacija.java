package view;

import helper.DBHandler;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ControllerZaposlenici;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Prozor za prijavu korisnika.
 * @author nives
 *
 */
public class ViewAutentikacija extends JDialog {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JButton jButton1 = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    
    private ControllerZaposlenici controllerZaposlenici = new ControllerZaposlenici();
    
    public ViewAutentikacija() {
        this(null, "", false);
    }

    public ViewAutentikacija(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  //a ovo izgleda radi neznam ja sam krivo nes napravila a bo ok! mogu sad to vidit u bazi možeš
    private void jbInit() throws Exception {
        this.setSize(new Dimension(308, 163));
        this.getContentPane().setLayout( null );
        jLabel1.setText("Korisnièko ime");
        jLabel1.setBounds(new Rectangle(25, 20, 90, 15));
        jLabel2.setText("Lozinka");
        jLabel2.setBounds(new Rectangle(25, 50, 65, 15));
        jButton1.setText("Prijava");
        jButton1.setBounds(new Rectangle(116, 85, 71, 23));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        prijava(e);
                    }
                });
        jTextField1.setText("ivo");
        jTextField1.setBounds(new Rectangle(120, 20, 140, 20));
        jTextField2.setText("123");
        jTextField2.setBounds(new Rectangle(120, 47, 140, 21));
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getRootPane().setDefaultButton(jButton1);
        
        //u sluèaju da se prozor pokuša zatvoriti na Xiæ, prekidamo izvoðenje app
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent arg0) {
    			System.exit(0);
    		}
    	});
    }

    
    
    /**
     * Metoda pokreæe proces prijave u sustav.
     * Šalje upisano korisnièko ime i lozinku u kontroler i provjerava rezultat.
     * @param e
     */
    private void prijava(ActionEvent e) {
        String korisnickoIme = jTextField1.getText();
        String lozinka = jTextField2.getText();
        
        controllerZaposlenici.dohvatSvihZaposlenika();
        boolean uspjeh = controllerZaposlenici.prijava(korisnickoIme, lozinka);
        
        //fiktivni korisnik s kojim se možemo ulogirati kada nema zaposlenika u bazi
        if(korisnickoIme.equals("admin") && lozinka.equals("123")) {
        	DBHandler.administrator = true;
        	setVisible(false);
        	return; //return napušta metodu - sprijeèavamo izvoðenje koda ispod
        }
        
        if(uspjeh) {
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Pogrešno korisnièko ime ili lozinka.");
        }
    }
}
