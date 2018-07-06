

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racunalni.servis.controller.ControllerKomponenteUsluge;
import com.racunalni.servis.controller.ControllerNarudzbe;
import com.racunalni.servis.controller.ControllerStavkeNarudzbe;
import com.racunalni.servis.model.KomponentaUsluga;
import com.racunalni.servis.model.Narudzba;
import com.racunalni.servis.model.StavkaNarudzbe;

/**
 * Servlet za prikaz forme za unos nove stavke narudžbe (unutar doGet metode) i
 * metode za spremanje stavke u bazu (doPost). doGet metoda iscrtava html formu
 * koja nakon unosa poziva doPost i šalje joj parametre unesene u formi.
 * Parametri slažu novi objekt stavke koji se potom sprema u bazu.
 * @author nives
 *
 */
@WebServlet("/dodajStavku")
public class ServletDodajStavku extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ControllerKomponenteUsluge controllerKomponenteUsluge = new ControllerKomponenteUsluge();
		ArrayList<KomponentaUsluga> listKomponenteUsluge = controllerKomponenteUsluge.dohvatSvihKomponentaUsluga();
		
		int idNarudzbe = Integer.valueOf(request.getParameter("idNarudzbe"));
		
		PrintWriter writer = response.getWriter();
		writer.write("<html><head><title>Dodaj stavku u narudzbu " + idNarudzbe + "</title></head>");
		writer.write("<body><h1>Dodaj stavku</h1>");
		writer.write("<form action='dodajStavku' method='post'>");
		writer.write("<br>Komponenta/usluga:<select name='idKomponenteUsluge'>"); //napunimo combobox sa komponentama uslugama
		for(KomponentaUsluga komponentaUsluga : listKomponenteUsluge) {
			writer.write("<option value='" + komponentaUsluga.getIdKomponenteUsluge() + "'>" +
					komponentaUsluga.getNaziv() + "</option>");
		}
		writer.write("</select>");
		writer.write("<input type='hidden' name='idNarudzbe' value='" + idNarudzbe + "'>");
		writer.write("<br>Kolicina:<input name='kolicina'>");
		writer.write("<br><input type='submit' value='Dodaj'>");
		writer.write("</form></body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerStavkeNarudzbe controllerStavkeNarudzbe =
				new ControllerStavkeNarudzbe();
		
		int idKomponenteUsluge = Integer.valueOf(request.getParameter("idKomponenteUsluge"));
		int idNarudzbe = Integer.valueOf(request.getParameter("idNarudzbe"));
		int kolicina = Integer.valueOf(request.getParameter("kolicina"));
		
		StavkaNarudzbe stavkaNarudzbe = new StavkaNarudzbe();
		stavkaNarudzbe.setIdKomponenteUsluge(idKomponenteUsluge);
		stavkaNarudzbe.setIdNarudzbe(idNarudzbe);
		stavkaNarudzbe.setKolicina(kolicina);
		
		controllerStavkeNarudzbe.spremiStavkuNarudzbu(stavkaNarudzbe);
		
		response.sendRedirect("/RacunalniServisWeb/popisNarudzbe"); //nakon unosa vraæamo se na prikaz narudžbi
	}
}
