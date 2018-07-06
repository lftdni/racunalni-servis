

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racunalni.servis.controller.ControllerNarudzbe;
import com.racunalni.servis.model.Narudzba;

/**
 * Servlet koji sadr�i logiku za spremanje nove narud�be u bazu.
 * Prilikom njegovog poziva, u bazu se sprema narud�ba za trenutno ulogiranog kupca
 * i s dana�njim datumom. Nakon unosa prikazuje ponovno se prikazuje stranica popisa narud�bi.
 * @author nives
 *
 */
@WebServlet("/dodajNarudzbu")
public class ServletDodajNarudzbu extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idKupca = (int) request.getSession().getAttribute("idKupca"); //dohvatimo id trenutno ulogiranog kupca
		ControllerNarudzbe controllerNarudzbe = new ControllerNarudzbe();
		
		Narudzba narudzba = new Narudzba();
		narudzba.setIdKupca(idKupca);
		narudzba.setDatum(new Date(Calendar.getInstance().getTimeInMillis()));
		
		controllerNarudzbe.spremiNarudzbu(narudzba); //spremimo narud�bu
		response.sendRedirect("/RacunalniServisWeb/popisNarudzbe"); //vratimo se na str. popisa narud�bi
	}

}
