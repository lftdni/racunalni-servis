package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Kupac;
import controller.ControllerKupci;

/**
 * Prozor za tablièni pregled kupaca.
 * @author nives
 *
 */
public class ViewKupciPregled extends JDialog {

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private String[] tableHeader = { "ID", "Ime", "Prezime", "Korisnièko ime", "Lozinka" };
    private DefaultTableModel dtm = new DefaultTableModel(tableHeader, 0);
    private JTable jTable1 = new JTable(dtm);
    
    private ControllerKupci controllerKupci = new ControllerKupci();

    public ViewKupciPregled() {
        this(null, "", false);
    }

    public ViewKupciPregled(Frame parent, String title, boolean modal) {
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
                        unosZaposlenika(e);
                    }
                });
        jButton2.setText("Brisanje");
        jButton2.setBounds(new Rectangle(85, 230, 71, 25));
        jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        brisanjeZaposlenika(e);
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
        ArrayList<Kupac> listKupci = controllerKupci.dohvatSvihKupaca();
        dtm.setNumRows(0);
        for(Kupac kupac : listKupci) {
            dtm.addRow(kupac.getRedakTablice());
        }
    }


    /**
     * Otvara prozor za unos novog kupca.
     * @param e
     */
    private void unosZaposlenika(ActionEvent e) {
    	ViewKupciUnos viewKupciUnos = new ViewKupciUnos(null, "Unos kupca", true);
        viewKupciUnos.setLocationRelativeTo(this);
        viewKupciUnos.setVisible(true);
        fillTable();
    }


    /**
     * Dohvaæa ID iz selektiranog retka i poziva njegovo brisanje.
     * @param e
     */
    private void brisanjeZaposlenika(ActionEvent e) {
        int redak = jTable1.getSelectedRow();
        int idKupca = Integer.valueOf(dtm.getValueAt(redak, 0).toString());
        controllerKupci.brisanjeKupca(idKupca);
        fillTable();
    }
}
