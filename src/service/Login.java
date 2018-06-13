package service;
import model.*;
import dao.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String benutzer = request.getParameter("benutzer");
		String password = request.getParameter("password");
		
		KundeManager manager = new KundeManager();
		try {
			Kunde kunde = manager.findByUsername(benutzer);
			if(kunde!=null & kunde.getPasswort().equals(password)){
				Cookie cookie = new Cookie("UserID",Long.toString(kunde.getId()));
				cookie.setMaxAge(60 * 60 * 24); 
				response.addCookie(cookie);
				response.sendRedirect("profil.html");
			}
			else {
				response.sendRedirect("login.html");
			}
		} catch (Exception e) {
			response.sendRedirect("login.html");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
