package view;

import helper.DBHandler;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.KomponentaUsluga;
import controller.ControllerKomponenteUsluge;

/**
 * Prozor za tablièni pregled komponenti/usluga.
 * @author nives
 *
 */
public class ViewKomponenteUslugePregled extends JDialog {

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private String[] tableHeader = { "ID", "ID zaposlenika", "Naziv", "Kratki opis", "Cijena" };
    private DefaultTableModel dtm = new DefaultTableModel(tableHeader, 0);
    private JTable jTable1 = new JTable(dtm);
    
    private ControllerKomponenteUsluge controllerKomponenteUsluge = new ControllerKomponenteUsluge();
    
    public ViewKomponenteUslugePregled() {
        this(null, "", false);
    }

    public ViewKomponenteUslugePregled(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(569, 300));
        this.getContentPane().setLayout( null );
        jScrollPane1.setBounds(new Rectangle(5, 5, 555, 215));
        jButton1.setText("Unos");
        jButton1.setBounds(new Rectangle(5, 230, 70, 25));
        jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	unosKomponenteUsluge(e);
                    }
                });
        jButton2.setText("Brisanje");
        jButton2.setBounds(new Rectangle(85, 230, 71, 23));
        jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        brisanjeKomponenteUsluge(e);
                    }
                });
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        jScrollPane1.setViewportView(jTable1);
        this.getContentPane().add(jScrollPane1, null);
        
        fillTable();
    }
        
        
    /**
     * Puni tablicu retcima sa baze.
     */
    private void fillTable() {
        ArrayList<KomponentaUsluga> listKomponentaUsluga = 
            controllerKomponenteUsluge.dohvatSvihKomponentaUsluga();
        dtm.setNumRows(0);
        for(KomponentaUsluga komponentaUsluga : listKomponentaUsluga) {
            dtm.addRow(komponentaUsluga.getRedak());
        }
    }


    
    /**
     * Otvara prozor za unos nove komponente/usluge.
     * @param e
     */
    private void unosKomponenteUsluge(ActionEvent e) {
        if(DBHandler.administrator) {
        	ViewKomponenteUslugeUnos viewKomponenteUslugeUnos = 
                new ViewKomponenteUslugeUnos(null, "Unos komponente/usluge", true);
            viewKomponenteUslugeUnos.setLocationRelativeTo(this);
            viewKomponenteUslugeUnos.setVisible(true);
            fillTable();
        } else {
            JOptionPane.showMessageDialog(this, "Potrebne su administratorske ovlasti za pristup ovom ekranu.");
        }
    }

    
    /**
     * Dohvaæa ID iz selektiranog retka i poziva njegovo brisanje.
     * @param e
     */
    private void brisanjeKomponenteUsluge(ActionEvent e) {
        int redak = jTable1.getSelectedRow();
        int idKomponenteUsluge = Integer.valueOf(dtm.getValueAt(redak, 0).toString());
        controllerKomponenteUsluge.brisanjeKomponenteUsluge(idKomponenteUsluge);
        fillTable();
    }
}
