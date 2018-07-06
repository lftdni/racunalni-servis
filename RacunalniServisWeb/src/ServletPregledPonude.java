

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racunalni.servis.controller.ControllerKomponenteUsluge;
import com.racunalni.servis.model.KomponentaUsluga;

/**
 * Servlet iscrtava tablicu sa svim kompoenntama i uslugama unesenima u bazi.
 * @author nives
 *
 */
@WebServlet(urlPatterns = { "/ponuda" })
public class ServletPregledPonude extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerKomponenteUsluge controllerKomponenteUsluge =
				new ControllerKomponenteUsluge();
		ArrayList<KomponentaUsluga> listKomponenteUsluge =
				controllerKomponenteUsluge.dohvatSvihKomponentaUsluga();
		
		PrintWriter writer = response.getWriter();
		writer.write("<html><head><title>Popis komponenti i usluga</title></head>");
		writer.write("<body><h1>Popis komponenti i usluga</h1>");
		writer.write("<table border='1'><tr><th>ID</th><th>Naziv</th><th>Kratki opis</th><th>Cijena</th></tr>");
		for(KomponentaUsluga komponentaUsluga : listKomponenteUsluge) {
			writer.write("<tr><td>" + komponentaUsluga.getIdKomponenteUsluge() + "</td>" +
					"<td>" + komponentaUsluga.getNaziv() + "</td>" +
					"<td>" + komponentaUsluga.getKratkiOpis() + "</td>" +
					"<td>" + komponentaUsluga.getCijena() + "</td></tr>");
		}
		writer.write("</table></body></html>");
		writer.write("<br><a href='/RacunalniServisWeb/menu.jsp'>Povratak na izbornik</a>");
	}

}
