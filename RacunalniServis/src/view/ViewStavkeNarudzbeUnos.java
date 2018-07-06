package view;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.KomponentaUsluga;
import model.Kupac;
import model.Narudzba;
import model.StavkaNarudzbe;
import controller.ControllerKomponenteUsluge;
import controller.ControllerKupci;
import controller.ControllerNarudzbe;
import controller.ControllerStavkeNarudzbe;

import javax.swing.JComboBox;

/**
 * Prozor za unos nove stavke narudžbe. Stavka narudžba se veže za narudžbu i za
 * komponentu/uslugu. ID narudžbe se proslijeðuje kroz metodu setIdNarudzbe i šalje
 * se sa prethodng ekrana, a komponenta/usluga se bira kroz combobox.
 * @author nives
 *
 */
public class ViewStavkeNarudzbeUnos extends JDialog {
	
	private JLabel jLabel1 = new JLabel("Komponenta/usluga");
    private JLabel jLabel2 = new JLabel("Kolièina");
    private JButton jButton1 = new JButton();
    private JTextField jTextField2 = new JTextField();
    private JComboBox<KomponentaUsluga> jComboBox1 = new JComboBox<KomponentaUsluga>();

    private int idNarudzbe;
    private ControllerStavkeNarudzbe controllerStavkeNarudzbe = new ControllerStavkeNarudzbe();
    private ControllerKomponenteUsluge controllerKomponenteUsluge = new ControllerKomponenteUsluge();
    
    public ViewStavkeNarudzbeUnos() {
        this(null, "", false);
    }

    public ViewStavkeNarudzbeUnos(Frame parent, String title, boolean modal) {
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
                    	spremanjeStavkeNarudzbe(e);
                    }
                });
        jTextField2.setBounds(new Rectangle(155, 56, 145, 21));
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        
        jComboBox1.setBounds(155, 25, 145, 20);
        getContentPane().add(jComboBox1);
        
        fillKomponenteUsluge();
    }
    
    
    /**
     * Setter metoda koja postavlja ID narudžbe za koju se stavka kreira.
     * @param idNarudzbe
     */
    public void setIdNarudzbe(int idNarudzbe) {
    	this.idNarudzbe = idNarudzbe;
    }
    
    
    /**
     * Dohvaæa sve komponente/usluge sa baze i puni ih u combobox.
     */
    private void fillKomponenteUsluge() {
    	jComboBox1.removeAllItems();
    	ArrayList<KomponentaUsluga> listKomponenteUsluge = controllerKomponenteUsluge.dohvatSvihKomponentaUsluga();
    	for(KomponentaUsluga komponentaUsluga : listKomponenteUsluge) {
    		jComboBox1.addItem(komponentaUsluga);
    	}
    }
    
    
    /**
     * Slaže objekt iz vrijednosti unesenih na ekranu i šalje ga u kontroler
     * na spremanje u bazu.
     * @param e
     */
    private void spremanjeStavkeNarudzbe(ActionEvent e) {
    	StavkaNarudzbe stavkaNarudzbe = new StavkaNarudzbe();
    	KomponentaUsluga komponentaUsluga = (KomponentaUsluga) jComboBox1.getSelectedItem();
    	
    	stavkaNarudzbe.setIdKomponenteUsluge(komponentaUsluga.getIdKomponenteUsluge());
    	stavkaNarudzbe.setIdNarudzbe(idNarudzbe);  //postavlja proslijeðeni ID narudžbe u objekt
    	stavkaNarudzbe.setKolicina(Integer.valueOf(jTextField2.getText()));
    	controllerStavkeNarudzbe.spremiStavkuNarudzbu(stavkaNarudzbe);
        setVisible(false);
    }
}
