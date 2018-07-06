package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Kupac;
import controller.ControllerKupci;

/**
 * Prozor za unos novog kupca.
 * @author nives
 *
 */
public class ViewKupciUnos extends JDialog {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JButton jButton1 = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();

    private ControllerKupci controllerKupci = new ControllerKupci();
    
    public ViewKupciUnos() {
        this(null, "", false);
    }

    public ViewKupciUnos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(332, 239));
        this.getContentPane().setLayout( null );
        jLabel1.setText("Ime");
        jLabel1.setBounds(new Rectangle(30, 28, 115, 15));
        jLabel2.setText("Prezime");
        jLabel2.setBounds(new Rectangle(30, 59, 115, 15));
        jLabel3.setText("Korisnièko ime");
        jLabel3.setBounds(new Rectangle(30, 92, 115, 15));
        jLabel4.setText("Lozinka");
        jLabel4.setBounds(new Rectangle(30, 124, 115, 15));
        jButton1.setText("Spremi");
        jButton1.setBounds(new Rectangle(128, 170, 71, 23));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spremanjeZaposlenika(e);
                    }
                });
        jTextField1.setBounds(new Rectangle(155, 25, 145, 20));
        jTextField2.setBounds(new Rectangle(155, 56, 145, 21));
        jTextField3.setBounds(new Rectangle(155, 89, 145, 21));
        jTextField4.setBounds(new Rectangle(155, 121, 145, 21));
        this.getContentPane().add(jTextField4, null);
        this.getContentPane().add(jTextField3, null);
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
    }
    
    /**
     * Slaže objekt iz vrijednosti unesenih na ekranu i šalje ga u kontroler
     * na spremanje u bazu.
     * @param e
     */
    private void spremanjeZaposlenika(ActionEvent e) {
        Kupac kupac = new Kupac();
        kupac.setIme(jTextField1.getText());
        kupac.setPrezime(jTextField2.getText());
        kupac.setKorisnickoIme(jTextField3.getText());
        kupac.setLozinka(jTextField4.getText());
        
        controllerKupci.spremiKupca(kupac);
        setVisible(false);
    }
}
