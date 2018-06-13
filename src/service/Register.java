package service;

import dao.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	            
	    DateFormat format = new SimpleDateFormat("MM/DD/YYYY");
	      
	    AdresseManager aManager = new AdresseManager();
	    KundeManager kManager = new KundeManager();
	    try {
	    	kManager.insertKunde(request.getParameter("vorname"), request.getParameter("nachname"), 
	    			request.getParameter("email"),format.parse(request.getParameter("bday")), request.getParameter("benutzername"),
	    			request.getParameter("password"), aManager.insertAdresse(request.getParameter("strasse"), request.getParameter("plz"), request.getParameter("wohnort")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    	response.sendRedirect("login.html");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
