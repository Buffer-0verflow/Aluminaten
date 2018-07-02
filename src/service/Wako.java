package service;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BewertungManager;
import dao.ProduktManager;
import model.Bewertung;
import model.Produkt;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Wako")
public class Wako extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wako() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    ProduktManager pManager = new ProduktManager();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Array erzeugen
		Object[] array = new Object[1];

		Produkt p = null;		// still to fix 
		
			
		Produkt warenkorbTest = pManager.findProdById(4034);
		
		
		// Produkte und Bewertung in Array speichern
		array[0] = warenkorbTest;
		
		Jsonb jsonb = JsonbBuilder.create();
		
		// Array in JSON umwandeln
		String json = jsonb.toJson(array);
		
		System.out.println(json);
		
		response.setContentType("application/json");
		response.getWriter().append(json);
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
