package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.KomponentaUsluga;
import model.StavkaRacuna;
import controller.ControllerKomponenteUsluge;
import controller.ControllerRacuni;
import controller.ControllerStavkeRacuna;

/**
 * Prozor za unos nove stavke ra�una. Stavka ra�una se ve�e za ra�un i za
 * komponentu/uslugu. ID ra�una se proslije�uje kroz metodu setIdRacuna i �alje
 * se sa prethodng ekrana, a komponenta/usluga se bira kroz combobox.
 * @author nives
 *
 */
public class ViewStavkeRacunaUnos extends JDialog {
	
	private JLabel jLabel1 = new JLabel("Komponenta/usluga");
    private JLabel jLabel2 = new JLabel("Koli�ina");
    private JButton jButton1 = new JButton();
    private JTextField jTextField2 = new JTextField();
    private JComboBox<KomponentaUsluga> jComboBox1 = new JComboBox<KomponentaUsluga>();

    private int idRacuna;
    private ControllerStavkeRacuna controllerStavkeRacuna = new ControllerStavkeRacuna();
    private ControllerKomponenteUsluge controllerKomponenteUsluge = new ControllerKomponenteUsluge();
    private ControllerRacuni controllerRacuni = new ControllerRacuni();
    
    public ViewStavkeRacunaUnos() {
        this(null, "", false);
    }

    public ViewStavkeRacunaUnos(Frame parent, String title, boolean modal) {
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
     * Setter metoda koja postavlja ID ra�una za koji se stavka kreira.
     * @param idNarudzbe
     */
    public void setIdRacuna(int idRacuna) {
    	this.idRacuna = idRacuna;
    }
    
    
    /**
     * Dohva�a sve komponente/usluge sa baze i puni ih u combobox.
     */
    private void fillKomponenteUsluge() {
    	jComboBox1.removeAllItems();
    	ArrayList<KomponentaUsluga> listKomponenteUsluge = controllerKomponenteUsluge.dohvatSvihKomponentaUsluga();
    	for(KomponentaUsluga komponentaUsluga : listKomponenteUsluge) {
    		jComboBox1.addItem(komponentaUsluga);
    	}
    }
    
    
    /**
     * Sla�e objekt iz vrijednosti unesenih na ekranu i �alje ga u kontroler
     * na spremanje u bazu.
     * @param e
     */
    private void spremanjeStavkeNarudzbe(ActionEvent e) {
    	StavkaRacuna stavkaRacuna = new StavkaRacuna();
    	KomponentaUsluga komponentaUsluga = (KomponentaUsluga) jComboBox1.getSelectedItem();
    	
    	stavkaRacuna.setIdKomponenteUsluge(komponentaUsluga.getIdKomponenteUsluge());
    	stavkaRacuna.setIdRacuna(idRacuna);  //postavlja proslije�eni ID ra�una u objekt
    	stavkaRacuna.setKolicina(Integer.valueOf(jTextField2.getText()));
    	controllerStavkeRacuna.spremiStavkuRacuna(stavkaRacuna);
    	controllerRacuni.izracunUkupneCijene(idRacuna);
        setVisible(false);
    }
}
