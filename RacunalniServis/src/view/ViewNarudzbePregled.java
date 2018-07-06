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
import model.StavkaNarudzbe;
import controller.ControllerNarudzbe;
import controller.ControllerStavkeNarudzbe;

/**
 * Prozor za tablièni pregled narudžbi i njihovih stavaka.
 * @author nives
 *
 */
public class ViewNarudzbePregled extends JDialog {
	
	private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JLabel jLabel1 = new JLabel("Narudžbe");
    private JLabel jLabel2 = new JLabel("Stavke narudžbe");
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private String[] tableHeaderNarduzbe = { "ID narudžbe", "ID kupca", "Datum" };
    private DefaultTableModel dtmNarduzbe = new DefaultTableModel(tableHeaderNarduzbe, 0);
    private JTable jTable1 = new JTable(dtmNarduzbe);    
    private String[] tableHeaderStavke = { "ID stavke", "ID komponente", "Kolièina" };
    private DefaultTableModel dtmStavke = new DefaultTableModel(tableHeaderStavke, 0);
    private JTable jTable2 = new JTable(dtmStavke);
    
    private ArrayList<Narudzba> listNarudzbe = new ArrayList<Narudzba>();
    private ControllerNarudzbe controllerNarudzbe = new ControllerNarudzbe();
    private ControllerStavkeNarudzbe controllerStavkeNarudzbe = new ControllerStavkeNarudzbe();

    public ViewNarudzbePregled() {
        this(null, "", false);
    }

    public ViewNarudzbePregled(Frame parent, String title, boolean modal) {
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
                    	unosNarudzbe(e);
                    }
                });
        jButton2.setText("Brisanje");
        jButton2.setBounds(new Rectangle(85, 230, 71, 25));
        jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	brisanjeNarudzbe(e);
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
        
        //akcija koja se poziva kod odabira retka u tablici narudžbi
        //ovjde konkretno: odabirom narudžbe pokreæe punjenje tablice njenih stavaka
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
        
        fillTableNarudzbe();
    }
    
    
    /**
     * Puni tablicu narudžbi retcima sa baze.
     */
    private void fillTableNarudzbe() {
        listNarudzbe = controllerNarudzbe.dohvatSvihNarudzbi();
        dtmNarduzbe.setNumRows(0);
        for(Narudzba narudzba : listNarudzbe) {
        	dtmNarduzbe.addRow(narudzba.getRedak());
        }
    }
    
    
    /**
     * Puni tablicu stavaka narudžbi retcima sa baze.
     * To radi tako da gleda koji je selektirani ID narudžbe u gornjoj
     * tablici i dohvaæa sve stavka vezane za taj ID.
     */
    private void fillTableStavke() {
    	listNarudzbe = controllerNarudzbe.dohvatSvihNarudzbi();
    	
        int redak = jTable1.getSelectedRow();
        int idNarudzbe = Integer.valueOf(dtmNarduzbe.getValueAt(redak, 0).toString());    	
        Narudzba narudzba = controllerNarudzbe.dohvatNarudzbe(idNarudzbe);
        
        ArrayList<StavkaNarudzbe> listStavkeNarudzbe = narudzba.getListStavkeNarudzbe();
        dtmStavke.setNumRows(0);
        for(StavkaNarudzbe stavkaNarudzbe : listStavkeNarudzbe) {
        	dtmStavke.addRow(stavkaNarudzbe.getRedak());
        }
    }


    /**
     * Otvara prozor za unos nove narudžbe.
     * @param e
     */
    private void unosNarudzbe(ActionEvent e) {
    	ViewNarudzbeUnos viewNarudzbeUnos = new ViewNarudzbeUnos(null, "Unos narudžbe", true);
        viewNarudzbeUnos.setLocationRelativeTo(this);
        viewNarudzbeUnos.setVisible(true);
        fillTableNarudzbe();
    }


    /**
     * Dohvaæa ID iz selektiranog retka narudžbe i poziva njeno brisanje.
     * @param e
     */
    private void brisanjeNarudzbe(ActionEvent e) {
        int redak = jTable1.getSelectedRow();
        int idNarudzbe = Integer.valueOf(dtmNarduzbe.getValueAt(redak, 0).toString());
        controllerNarudzbe.brisanjeNarudzbe(idNarudzbe);
        fillTableNarudzbe();
    }


    /**
     * Otvara prozor za unos nove stavke narudžbe.
     * @param e
     */
    private void unosStavke(ActionEvent e) {
    	int redak = jTable1.getSelectedRow();
    	if(redak == -1) {
    		JOptionPane.showMessageDialog(null, "Molim oznaèite narudžbu.");
    		return;
    	}
    	int idNarudzbe = Integer.valueOf(dtmNarduzbe.getValueAt(redak, 0).toString());
    	
    	ViewStavkeNarudzbeUnos viewStavkeNarudzbeUnos = 
    			new ViewStavkeNarudzbeUnos(null, "Unos stavke narudžbe", true);
        viewStavkeNarudzbeUnos.setIdNarudzbe(idNarudzbe);  //proslijedimo selektirani ID narudžbe
        viewStavkeNarudzbeUnos.setLocationRelativeTo(this);
        viewStavkeNarudzbeUnos.setVisible(true);
        fillTableStavke();
    }


    /**
     * Dohvaæa ID iz selektiranog retka stavke narudžbe i poziva njeno brisanje.
     * @param e
     */
    private void brisanjeStavke(ActionEvent e) {
        int redak = jTable2.getSelectedRow();
        int idStavkeNarudzbe = Integer.valueOf(dtmStavke.getValueAt(redak, 0).toString());
        controllerStavkeNarudzbe.brisanjeStavkeNarudzbe(idStavkeNarudzbe);
        fillTableStavke();
    }
}
