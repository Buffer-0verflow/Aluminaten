package service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdresseManager;
import dao.KundeManager;
import model.Adresse;
import model.Bestellposition;
import model.Kunde;

/**asdhfkl
 * Servlet implementation class Bearbeiten
 */
@WebServlet("/Bearbeiten")
public class Bearbeiten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bearbeiten() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		AdresseManager aManager = new AdresseManager();
	    KundeManager kManager = new KundeManager();
	    
	    DateFormat format = new SimpleDateFormat("MM-DD-YYYY");
	    Cookie[] cookies = request.getCookies();		
		Kunde kunde = kManager.findById(Long.parseLong(cookies[0].getValue()));
		Adresse addr = aManager.findById(kunde.getAdresse().getId());
	    
	    try {
	    	kManager.updateKunde(kunde.getId(), request.getParameter("vorname"), request.getParameter("nachname"), 
	    			request.getParameter("email"),format.parse(request.getParameter("bday")), request.getParameter("benutzername"),
	    			request.getParameter("password"), aManager.updateAdresse(addr.getId(), request.getParameter("strasse"), request.getParameter("plz"), request.getParameter("wohnort")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    	response.sendRedirect("profil.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
