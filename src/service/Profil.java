package service;
import model.*;
import dao.*;
import java.io.IOException;

import java.util.Collection;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    KundeManager kManager = new KundeManager();
    BestellpositionManager bManager = new BestellpositionManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* To Solve: Date issue, Session*/
		Cookie[] cookies = request.getCookies();		
		
		Collection<Bestellposition> bestellung = bManager.getByKunde(Long.parseLong(cookies[0].getValue()));
		
		Kunde kunde = kManager.findById(Long.parseLong(cookies[0].getValue()));  	//connect to Session later
		Object[] arr = new Object[2];
		arr[0] = kunde;
		arr[1] = bestellung;
		//System.out.println(kunde);							//for debugging
		Jsonb jsonb = JsonbBuilder.create();
		String jsn = jsonb.toJson(arr);
		System.out.println(jsn);							//for debugging
		response.setContentType("application/json");
		response.getWriter().append(jsn);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
