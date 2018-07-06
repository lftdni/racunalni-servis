package view;

import helper.DBHandler;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Kupac;
import model.Narudzba;
import model.Racun;
import controller.ControllerKupci;
import controller.ControllerNarudzbe;
import controller.ControllerRacuni;

/**
 * Prozor za unos novog raèuna. Raèun se veže za kupca pa je
 * njegov izbor realiziran kroz combobox.
 * @author nives
 *
 */
public class ViewRacuniUnos extends JDialog {

	private JLabel jLabel1 = new JLabel("Kupac");
    private JLabel jLabel2 = new JLabel("Datum");
    private JButton jButton1 = new JButton();
    private JTextField jTextField2 = new JTextField();
    private JComboBox<Kupac> jComboBox1 = new JComboBox<Kupac>();

    private ControllerRacuni controllerRacuni = new ControllerRacuni();
    private ControllerKupci controllerKupci = new ControllerKupci();
    
    public ViewRacuniUnos() {
        this(null, "", false);
    }

    public ViewRacuniUnos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(332, 180));
        this.getContentPane().setLayout(null);
        jLabel1.setBounds(new Rectangle(30, 28, 115, 15));
        jLabel2.setBounds(new Rectangle(30, 59, 115, 15));
        jButton1.setText("Spremi");
        jButton1.setBounds(new Rectangle(122, 107, 71, 23));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	spremanjeRacuna(e);
                    }
                });
        jTextField2.setBounds(new Rectangle(155, 56, 145, 21));
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        
        jComboBox1.setBounds(155, 25, 145, 20);
        getContentPane().add(jComboBox1);
        
        fillKupci();
    }
    
    
    /**
     * Dohvaæa sve kupce sa baze i puni ih u combobox.
     */
    private void fillKupci() {
    	jComboBox1.removeAllItems();
    	ArrayList<Kupac> listKupci = controllerKupci.dohvatSvihKupaca();
    	for(Kupac kupac : listKupci) {
    		jComboBox1.addItem(kupac);
    	}
    }
    
    
    /**
     * Slaže objekt iz vrijednosti unesenih na ekranu i šalje ga u kontroler
     * na spremanje u bazu.
     * @param e
     */
    private void spremanjeRacuna(ActionEvent e) {
    	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    	
    	Racun racun = new Racun();
    	Kupac kupac = (Kupac) jComboBox1.getSelectedItem();
    	racun.setIdKupca(kupac.getIdKupca());
    	racun.setIdZaposlenika(DBHandler.idZaposlenika);
        try {
        	racun.setDatum(new Date(dateFormat.parse(jTextField2.getText()).getTime()));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Neispravan format datuma (dd.mm.gggg).");
			return;
		}
    	controllerRacuni.spremiRacun(racun);
        setVisible(false);
    }
}
