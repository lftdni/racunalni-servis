package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Narudzba;
import model.Racun;
import model.StavkaRacuna;
import controller.ControllerNarudzbe;
import controller.ControllerRacuni;
import controller.ControllerStavkeNarudzbe;
import controller.ControllerStavkeRacuna;

/**
 * Prozor za tablièni pregled raèuna i njihovih stavaka.
 * @author nives
 *
 */
public class ViewRacuniPregled extends JDialog {

	private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JLabel jLabel1 = new JLabel("Raèuni");
    private JLabel jLabel2 = new JLabel("Stavke raèuna");
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private String[] tableHeaderRacuni = { "ID raèuna", "ID kupca", "ID zaposlenika", "Datum", "Ukupna cijena" };
    private DefaultTableModel dtmRacuni = new DefaultTableModel(tableHeaderRacuni, 0);
    private JTable jTable1 = new JTable(dtmRacuni);    
    private String[] tableHeaderStavke = { "ID stavke", "ID komponente", "Kolièina" };
    private DefaultTableModel dtmStavke = new DefaultTableModel(tableHeaderStavke, 0);
    private JTable jTable2 = new JTable(dtmStavke);
    
    private ArrayList<Racun> listRacuni = new ArrayList<Racun>();
    private ControllerRacuni controllerRacuni = new ControllerRacuni();
    private ControllerStavkeRacuna controllerStavkeRacuna = new ControllerStavkeRacuna();

    public ViewRacuniPregled() {
        this(null, "", false);
    }

    public ViewRacuniPregled(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(579, 484));
        this.getContentPane().setLayout( null );
        jScrollPane1.setBounds(new Rectangle(5, 26, 555, 194));
        jButton1.setText("Unos");
        jButton1.setBounds(new Rectangle(5, 230, 70, 25));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	unosRacuna(e);
                    }
                });
        jButton2.setText("Brisanje");
        jButton2.setBounds(new Rectangle(85, 230, 71, 25));
        jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	brisanjeRacuna(e);
                    }
                });
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        jScrollPane1.setViewportView(jTable1);
        this.getContentPane().add(jScrollPane1, null);
        
        jLabel1.setBounds(5, 10, 46, 14);
        getContentPane().add(jLabel1);
        
        jScrollPane2.setBounds(5, 287, 555, 109);
        getContentPane().add(jScrollPane2);
        
        jScrollPane2.setViewportView(jTable2);
        
        jLabel2.setBounds(5, 270, 81, 14);
        getContentPane().add(jLabel2);
        
        jButton3.setText("Unos");
        jButton3.setBounds(new Rectangle(5, 230, 70, 25));
        jButton3.setBounds(5, 407, 70, 25);
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	unosStavke(e);
            }
        });
        getContentPane().add(jButton3);
        
        jButton4.setText("Brisanje");
        jButton4.setBounds(new Rectangle(85, 230, 71, 25));
        jButton4.setBounds(85, 407, 71, 25);
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	brisanjeStavke(e);
            }
        });
        getContentPane().add(jButton4);
        
        //akcija koja se poziva kod odabira retka u tablici raèuna
        //ovjde konkretno: odabirom raèuna pokreæe punjenje tablice njegovih stavaka
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if(!event.getValueIsAdjusting()) {
            		int redak = jTable1.getSelectedRow();
                    if(redak == -1) {
                    	dtmStavke.setNumRows(0);
                    	return;
                    } else {
                    	fillTableStavke();
                    }
            	}
            }
        });
        
        fillTableRacuni();
    }
    
    
    /**
     * Puni tablicu raèuna retcima sa baze.
     */
    private void fillTableRacuni() {
        listRacuni = controllerRacuni.dohvatSvihRacuna();
        dtmRacuni.setNumRows(0);
        for(Racun racun : listRacuni) {
        	dtmRacuni.addRow(racun.getRedak());
        }
    }
    
    
    /**
     * Puni tablicu stavaka raèuna retcima sa baze.
     * To radi tako da gleda koji je selektirani ID raèuna u gornjoj
     * tablici i dohvaæa sve stavka vezane za taj ID.
     */
    private void fillTableStavke() {
    	listRacuni = controllerRacuni.dohvatSvihRacuna();
    	
        int redak = jTable1.getSelectedRow();
        int idRacuna = Integer.valueOf(dtmRacuni.getValueAt(redak, 0).toString());    	
        Racun racun = controllerRacuni.dohvatRacuna(idRacuna);
        
        ArrayList<StavkaRacuna> listStavkeRacuna = racun.getListStavkeRacuna();
        dtmStavke.setNumRows(0);
        for(StavkaRacuna stavkaRacuna : listStavkeRacuna) {
        	dtmStavke.addRow(stavkaRacuna.getRedak());
        }
    }


    /**
     * Otvara prozor za unos novog raèuna.
     * @param e
     */
    private void unosRacuna(ActionEvent e) {
    	ViewRacuniUnos viewRacuniUnos = new ViewRacuniUnos(null, "Unos raèuna", true);
    	viewRacuniUnos.setLocationRelativeTo(this);
    	viewRacuniUnos.setVisible(true);
        fillTableRacuni();
    }


    /**
     * Dohvaæa ID iz selektiranog retka raèuna i poziva njeno brisanje.
     * @param e
     */
    private void brisanjeRacuna(ActionEvent e) {
        int redak = jTable1.getSelectedRow();
        int idRacuna = Integer.valueOf(dtmRacuni.getValueAt(redak, 0).toString());
        controllerRacuni.brisanjeRacuna(idRacuna);
        fillTableRacuni();
    }


    /**
     * Otvara prozor za unos nove stavke raèuna.
     * Nakon unosa osvježava se ukupna cijena raèuna na kojoj je stavka.
     * @param e
     */
    private void unosStavke(ActionEvent e) {
    	int redak = jTable1.getSelectedRow();
    	if(redak == -1) {
    		JOptionPane.showMessageDialog(null, "Molim oznaèite raèun.");
    		return;
    	}
    	int idRacuna = Integer.valueOf(dtmRacuni.getValueAt(redak, 0).toString());
    	
    	ViewStavkeRacunaUnos viewStavkeRacunaUnos = 
    			new ViewStavkeRacunaUnos(null, "Unos stavke raèuna", true);
    	viewStavkeRacunaUnos.setIdRacuna(idRacuna);  //proslijedimo selektirani ID raèuna
    	viewStavkeRacunaUnos.setLocationRelativeTo(this);
    	viewStavkeRacunaUnos.setVisible(true);
    	
    	//nakon unosa stavke raèuna, nužno je promijeniti ukupnu cijenu na raèunu
    	int ukupnaCijena = controllerRacuni.dohvatUkupneCijene(idRacuna);  //dohvatimo ju
    	dtmRacuni.setValueAt(ukupnaCijena, redak, 4); //i postavimo na prikaz u specifièno polje tabele
        fillTableStavke();
    }


    /**
     * Dohvaæa ID iz selektiranog retka stavke raèuna i poziva njeno brisanje.
     * Nakon brisanja osvježava se ukupna cijena raèuna na kojoj je stavka.
     * @param e
     */
    private void brisanjeStavke(ActionEvent e) {
        int redakStavke = jTable2.getSelectedRow();
        int idStavkeRacuna = Integer.valueOf(dtmStavke.getValueAt(redakStavke, 0).toString());
        controllerStavkeRacuna.brisanjeStavkeRacuna(idStavkeRacuna);
        
        int redakRacuna = jTable1.getSelectedRow();
        int idRacuna = Integer.valueOf(dtmRacuni.getValueAt(redakRacuna, 0).toString());
    	controllerRacuni.izracunUkupneCijene(idRacuna);
    	
    	//nakon brisanja stavke raèuna, nužno je promijeniti ukupnu cijenu na raèunu
    	int ukupnaCijena = controllerRacuni.dohvatUkupneCijene(idRacuna);
    	dtmRacuni.setValueAt(ukupnaCijena, redakRacuna, 4);
        fillTableStavke();
    }
}
