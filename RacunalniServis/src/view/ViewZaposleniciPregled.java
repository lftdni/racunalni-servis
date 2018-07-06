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

import model.Zaposlenik;
import controller.ControllerZaposlenici;

/**
 * Prozor za tablièni pregled zaposlenika.
 * @author nives
 *
 */
public class ViewZaposleniciPregled extends JDialog {

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private String[] tableHeader = {"ID", "Ime", "Prezime", "Korisnièko ime", "Lozinka", "Mjesto stanovanja", "Administrator"};
    private DefaultTableModel dtm = new DefaultTableModel(tableHeader, 0);
    private JTable jTable1 = new JTable(dtm);
    
    private ControllerZaposlenici controllerZaposlenici = new ControllerZaposlenici();

    public ViewZaposleniciPregled() {
        this(null, "", false);
    }

    public ViewZaposleniciPregled(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(721, 300));
        this.getContentPane().setLayout( null );
        jScrollPane1.setBounds(new Rectangle(5, 5, 690, 215));
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
        ArrayList<Zaposlenik> listZaposlenici = controllerZaposlenici.dohvatSvihZaposlenika();
        dtm.setNumRows(0);
        for(Zaposlenik zaposlenik : listZaposlenici) {
            dtm.addRow(zaposlenik.getRedakTablice());
        }
    }


    /**
     * Otvara prozor za unos novog zaposlenika.
     * @param e
     */
    private void unosZaposlenika(ActionEvent e) {
    	ViewZaposleniciUnos viewZaposleniciUnos = new ViewZaposleniciUnos(null, "Unos zaposlenika", true);
        viewZaposleniciUnos.setLocationRelativeTo(this);
        viewZaposleniciUnos.setVisible(true);
        fillTable();
    }

    
    /**
     * Dohvaæa ID iz selektiranog retka i poziva njegovo brisanje.
     * @param e
     */
    private void brisanjeZaposlenika(ActionEvent e) {
        int redak = jTable1.getSelectedRow();
        int idZaposlenika = Integer.valueOf(dtm.getValueAt(redak, 0).toString());
        controllerZaposlenici.brisanjeZaposlenika(idZaposlenika);
        fillTable();
    }
}
