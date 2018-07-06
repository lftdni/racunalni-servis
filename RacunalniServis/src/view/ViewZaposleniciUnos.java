package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Zaposlenik;
import controller.ControllerZaposlenici;

/**
 * Prozor za unos novog zaposlenika.
 * @author nives
 *
 */
public class ViewZaposleniciUnos extends JDialog {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JCheckBox jCheckBox1 = new JCheckBox();
    private JButton jButton1 = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JTextField jTextField5 = new JTextField();
    
    private ControllerZaposlenici controllerZaposlenici = new ControllerZaposlenici();

    public ViewZaposleniciUnos() {
        this(null, "", false);
    }

    public ViewZaposleniciUnos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(340, 300));
        this.getContentPane().setLayout( null );
        jLabel1.setText("Ime");
        jLabel1.setBounds(new Rectangle(30, 20, 115, 15));
        jLabel2.setText("Prezime");
        jLabel2.setBounds(new Rectangle(30, 54, 115, 15));
        jLabel3.setText("Korisnièko ime");
        jLabel3.setBounds(new Rectangle(30, 88, 115, 15));
        jLabel4.setText("Lozinka");
        jLabel4.setBounds(new Rectangle(30, 122, 115, 15));
        jLabel5.setText("Mjesto stanovanja");
        jLabel5.setBounds(new Rectangle(30, 156, 115, 15));
        jCheckBox1.setText("Administrator");
        jCheckBox1.setBounds(new Rectangle(30, 190, 115, 20));
        jButton1.setText("Spremi");
        jButton1.setBounds(new Rectangle(132, 230, 71, 23));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spremanjeZaposlenika(e);
                    }
                });
        jTextField1.setBounds(new Rectangle(155, 25, 145, 20));
        jTextField2.setBounds(new Rectangle(155, 56, 145, 21));
        jTextField3.setBounds(new Rectangle(155, 89, 145, 21));
        jTextField4.setBounds(new Rectangle(155, 121, 145, 21));
        jTextField5.setBounds(new Rectangle(155, 153, 145, 21));
        this.getContentPane().add(jTextField5, null);
        this.getContentPane().add(jTextField4, null);
        this.getContentPane().add(jTextField3, null);
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jCheckBox1, null);
        this.getContentPane().add(jLabel5, null);
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
        Zaposlenik zaposlenik = new Zaposlenik();
        zaposlenik.setIme(jTextField1.getText());
        zaposlenik.setPrezime(jTextField2.getText());
        zaposlenik.setKorisnickoIme(jTextField3.getText());
        zaposlenik.setLozinka(jTextField4.getText());
        zaposlenik.setMjestoStanovanja(jTextField5.getText());
        zaposlenik.setAdministrator(jCheckBox1.isSelected());
        
        controllerZaposlenici.spremiZaposlenika(zaposlenik);
        setVisible(false);
    }
}
