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
import javax.swing.JTextField;

import model.KomponentaUsluga;
import controller.ControllerKomponenteUsluge;

/**
 * Prozor za unos nove komponente/usluge.
 * @author nives
 *
 */
public class ViewKomponenteUslugeUnos extends JDialog {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JButton jButton1 = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();

    private ControllerKomponenteUsluge controllerKomponenteUsluge = new ControllerKomponenteUsluge();
    
    public ViewKomponenteUslugeUnos() {
        this(null, "", false);
    }

    public ViewKomponenteUslugeUnos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(340, 204));
        this.getContentPane().setLayout( null );
        jLabel1.setText("Naziv");
        jLabel1.setBounds(new Rectangle(30, 20, 115, 15));
        jLabel2.setText("Kratki opis");
        jLabel2.setBounds(new Rectangle(30, 54, 115, 15));
        jLabel3.setText("Cijena");
        jLabel3.setBounds(new Rectangle(30, 88, 115, 15));
        jButton1.setText("Spremi");
        jButton1.setBounds(new Rectangle(132, 130, 71, 23));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	spremanjeKomponenteUsluge(e);
                    }
                });
        jTextField1.setBounds(new Rectangle(155, 17, 145, 20));
        jTextField2.setBounds(new Rectangle(155, 51, 145, 21));
        jTextField3.setBounds(new Rectangle(155, 85, 145, 21));
        this.getContentPane().add(jTextField3, null);
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
    }

    
    /**
     * Slaže objekt iz vrijednosti unesenih na ekranu i šalje ga u kontroler
     * na spremanje u bazu.
     * @param e
     */
    private void spremanjeKomponenteUsluge(ActionEvent e) {
        KomponentaUsluga komponentaUsluga = new KomponentaUsluga();
        komponentaUsluga.setNaziv(jTextField1.getText());
        komponentaUsluga.setKratkiOpis(jTextField2.getText());
        komponentaUsluga.setCijena(Integer.valueOf(jTextField3.getText()));
        komponentaUsluga.setIdZaposlenika(DBHandler.idZaposlenika);
        
        controllerKomponenteUsluge.spremiKomponentuUslugu(komponentaUsluga);
        setVisible(false);
    }
}
