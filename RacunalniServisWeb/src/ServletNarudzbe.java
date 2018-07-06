

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racunalni.servis.controller.ControllerNarudzbe;
import com.racunalni.servis.model.Narudzba;
import com.racunalni.servis.model.StavkaNarudzbe;

/**
 * Servlet prilikom poziva dohvaæa id trenutno ulogiranog kupca, za njega iscrtava
 * tablicu narudžbi i stavki svake. Njih dohvaæa iz baze podataka preko kontroler metoda.
 * @author nives
 *
 */
@WebServlet("/popisNarudzbe")
public class ServletNarudzbe extends HttpServlet {
	
	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idKupca = (int) request.getSession().getAttribute("idKupca");
		ControllerNarudzbe controllerNarudzbe = new ControllerNarudzbe();
		ArrayList<Narudzba> listNarudzbe = controllerNarudzbe.dohvatNarudzbiKupca(idKupca);
		
		PrintWriter writer = response.getWriter();
		writer.write("<html><head><title>Popis narudzbi kupca</title></head>");
		writer.write("<body><h1>Popis narudzbi kupca</h1>");
		
		if(listNarudzbe.size() == 0) {
			writer.write("<p>Nemate nijednu narudžbu.</p>");
		} else {
			
			for(Narudzba narudzba : listNarudzbe) {
				writer.write("<h3>Narudzba br. " + narudzba.getIdNarudzbe() + ", " + dateFormat.format(narudzba.getDatum()) + "</h3>");
				ArrayList<StavkaNarudzbe> listStavkeNarudzbe = narudzba.getListStavkeNarudzbe();
				
				if(listStavkeNarudzbe.size() != 0) {
					writer.write("<table border='1'><tr><th>ID komponente/usluge</th><th>Kolicina</th></tr>");
					for(StavkaNarudzbe stavkaNarudzbe : listStavkeNarudzbe) {
						writer.write("<tr><td>" + stavkaNarudzbe.getIdKomponenteUsluge() + "</td>" +
								"<td>" + stavkaNarudzbe.getKolicina() + "</td></tr>");
					}
					writer.write("</table>");
				}
				writer.write("<a href='/RacunalniServisWeb/dodajStavku?idNarudzbe=" + 
						narudzba.getIdNarudzbe() +"'>Dodaj stavku</a>");
			}
		}
		writer.write("<br><br><a href='/RacunalniServisWeb/dodajNarudzbu'>Nova narudzba</a>");
		writer.write("</body></html>");
	}

}
