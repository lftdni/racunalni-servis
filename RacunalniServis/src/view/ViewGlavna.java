package view;

import helper.DBHandler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Glavni prozor iz kojega se radi navigacija prema ostalim prozorima.
 * @author nives
 *
 */
public class ViewGlavna extends JFrame {
	
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenuItem jMenuItem2 = new JMenuItem();
    private JMenuItem jMenuItem3 = new JMenuItem();
    private JMenuItem jMenuItem4 = new JMenuItem();
    private JMenuItem jMenuItem5 = new JMenuItem();

    public ViewGlavna() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "Raèunalni servis" );
        menuFile.setText("Datoteka");
        menuFileExit.setText("Izlaz");
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        jMenuItem1.setText("Zaposlenici");
        jMenuItem1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pregledZaposlenika(e);
                    }
                });
        jMenuItem2.setText("Kupci");
        jMenuItem2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pregledKupaca(e);
                    }
                });
        jMenuItem3.setText("Komponente i usluge");
        jMenuItem3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pregledKomponenteUsluge(e);
                    }
                });
        jMenuItem4.setText("Narudžbe");
        jMenuItem4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pregledNarudzbe(e);
                    }
                });
        jMenuItem5.setText("Raèuni");
        jMenuItem5.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pregledRacuni(e);
                    }
                });
        menuFile.add(jMenuItem1);
        menuFile.add(jMenuItem2);
        menuFile.add(jMenuItem3);
        menuFile.add(jMenuItem4);
        menuFile.add(jMenuItem5);
        menuFile.add( menuFileExit );
        menuBar.add(menuFile);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    
    /**
     * Otvara prozor za pregled zaposlenika. Potrebne admin ovlasti.
     * @param e
     */
    private void pregledZaposlenika(ActionEvent e) {
        if(DBHandler.administrator) {
        	ViewZaposleniciPregled viewZaposleniciPregled = new ViewZaposleniciPregled(this, "Pregled zaposlenika", false);
            viewZaposleniciPregled.setLocationRelativeTo(this);
            viewZaposleniciPregled.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Potrebne su administratorske ovlasti za pristup ovom ekranu.");
        }
    }

    
    /**
     * Otvara prozor za pregled kupaca.
     * @param e
     */
    private void pregledKupaca(ActionEvent e) {
    	ViewKupciPregled viewKupciPregled = new ViewKupciPregled(this, "Pregled kupaca", false);
        viewKupciPregled.setLocationRelativeTo(this);
        viewKupciPregled.setVisible(true);
    }

    
    /**
     * Otvara prozor za pregled komponenti/usluga.
     * @param e
     */
    private void pregledKomponenteUsluge(ActionEvent e) {
    	ViewKomponenteUslugePregled viewKomponenteUslugePregled = 
            new ViewKomponenteUslugePregled(this, "Pregled komponenti i usluga", false);
        viewKomponenteUslugePregled.setLocationRelativeTo(this);
        viewKomponenteUslugePregled.setVisible(true);
    }

    
    /**
     * Otvara prozor za pregled narudžbi.
     * @param e
     */
    private void pregledNarudzbe(ActionEvent e) {
    	ViewNarudzbePregled viewNarudzbePregled = new ViewNarudzbePregled(this, "Pregled narudžbi", false);
        viewNarudzbePregled.setLocationRelativeTo(this);
        viewNarudzbePregled.setVisible(true);
    }

    
    /**
     * Otvara prozor za pregled raèuna.
     * @param e
     */
    private void pregledRacuni(ActionEvent e) {
    	ViewRacuniPregled viewRacuniPregled = new ViewRacuniPregled(this, "Pregled raèuna", false);
        viewRacuniPregled.setLocationRelativeTo(this);
        viewRacuniPregled.setVisible(true);
    }
}
