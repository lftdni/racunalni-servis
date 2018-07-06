
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.racunalni.servis.controller.ControllerKupci;


/**
 * Servlet se poziva prilikom klika na gumb Prijava na poèetnoj stranici.
 * Dohvaæa sve kupce i traži onoga sa proslijeðenom kombinacijom korisnièkog
 * imena i lozinke. Ukoliko je prijava uspješna, id kupca (ulogiranog korisnika)
 * sprema se kao sesijski parametar koji æe se koristiti na ostalim stranicama,
 * a servlet usmjeruje na stranicu izbornika.
 * @author nives
 *
 */
@WebServlet(urlPatterns = { "/prijava" })
public class ServletPrijava extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");
		
		ControllerKupci controlerKupci = new ControllerKupci();
		controlerKupci.dohvatSvihKupaca();
		int idKupca = controlerKupci.prijavaKupca(korisnickoIme, lozinka);
		
		if(idKupca != 0) {
			HttpSession session = request.getSession();
		    session.setAttribute("idKupca", idKupca);		    
		    response.sendRedirect("/RacunalniServisWeb/menu.jsp");
		} else {
			response.getWriter().write("Neispravna lozinka ili korisnièko ime.");
		}
	}

}
