package service;

import model.*;
import dao.*;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rate
 */
@WebServlet("/Rate")
public class Rate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        	      
	    BewertungManager bManager = new BewertungManager();
	    KundeManager kManager = new KundeManager();
	    
	    Cookie[] cookies = request.getCookies();

	    
		Kunde kunde = kManager.findById(Long.parseLong(cookies[0].getValue()));
	    Produkt p = null;
	    
	    try {	    	
	    	bManager.insertBewertung(getNextID(bManager), Integer.parseInt(request.getParameter("Rating")), 
	    			request.getParameter("Comment"), getTime(), kunde, p);	   	
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    response.sendRedirect("home.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Timestamp getTime() {		
		return(new Timestamp(System.currentTimeMillis()));
	}
	
	public int getNextID(BewertungManager bManager) {
		return(bManager.list().size() + 1);	
	}

}
