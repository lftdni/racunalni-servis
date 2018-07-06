

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.racunalni.servis.controller.ControllerKupci;
import com.racunalni.servis.controller.ControllerStavkeNarudzbe;
import com.racunalni.servis.model.KomponentaUsluga;
import com.racunalni.servis.model.Kupac;
import com.racunalni.servis.model.StavkaNarudzbe;

/**
 * Servlet za prikaz forme za prikaz podataka o kupcu (unutar doGet metode) i
 * metode za spremanje promjena o kupcu u bazu (doPost). doGet metoda iscrtava html formu
 * koja nakon unosa poziva doPost i šalje joj parametre unesene u formi.
 * Parametri slažu novi objekt kupca koji se potom sprema u bazu. Kupac je u ovom kontekstu
 * trenutno ulogirani korisnik aplikacije.
 * @author nives
 *
 */
@WebServlet("/mojProfil")
public class ServletMojProfil extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idKupca = (int) request.getSession().getAttribute("idKupca");
		
		ControllerKupci controllerKupci = new ControllerKupci();
		controllerKupci.dohvatSvihKupaca();
		
		Kupac kupac = controllerKupci.dohvatKupca(idKupca);
	
		/*css za dodati*/
		PrintWriter writer = response.getWriter();
		writer.write("<html><head><title>Moj profil</title><NPR ovjde bi išao css ok?></head>");
		writer.write("<body><h1>Moj profil</h1>");
		writer.write("<form action='mojProfil' method='post'>");
		writer.write("<table>");
		writer.write("<tr><td>Ime:</td><td><input name='ime' value='" + kupac.getIme() + "'></td></tr>");
		writer.write("<tr><td>Prezime:</td><td><input name='prezime' value='" + kupac.getPrezime() + "'></td></tr>");
		writer.write("<tr><td>Korisnicko ime:</td><td><input name='korisnickoIme' value='" + kupac.getKorisnickoIme() + "'></td></tr>");
		writer.write("<tr><td>Lozinka:</td><td><input name='lozinka' value='" + kupac.getLozinka() + "'></td></tr>");
		writer.write("<tr><td colspan='2'><input type='submit' value='Spremi promjene'></td></tr>");
		writer.write("</table></form></body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idKupca = (int) request.getSession().getAttribute("idKupca");
		
		ControllerKupci controllerKupci = new ControllerKupci();
		
		Kupac kupac = new Kupac();
		kupac.setIdKupca(idKupca);
		kupac.setIme(request.getParameter("ime"));
		kupac.setPrezime(request.getParameter("prezime"));
		kupac.setKorisnickoIme(request.getParameter("korisnickoIme"));
		kupac.setLozinka(request.getParameter("lozinka"));
		
		controllerKupci.azurirajKupca(kupac);
		
		response.sendRedirect("/RacunalniServisWeb/menu.jsp"); //vraæamo se na izbornik
	}
}
