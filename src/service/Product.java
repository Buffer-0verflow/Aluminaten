package service;

import java.io.IOException;
import java.util.Collection;


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
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    ProduktManager pManager = new ProduktManager();
    BewertungManager bManager = new BewertungManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Array erzeugen
		Object[] array = new Object[4];
		
		String id = request.getParameter("id");
		
		System.out.println("ID: " + id);
		
		Produkt prod = pManager.findProdById(Long.parseLong(id));
		
		Collection<Bewertung> comments = bManager.getBewertungByProdukt(prod);	
		Produkt interests0 = pManager.findProdById(4001);
		Produkt interests1 = pManager.findProdById(4002);

		System.out.println(prod);
		
		// Produkte und Bewertung in Array speichern
		array[0] = comments;
		array[1] = interests0;
		array[2] = interests1;
		array[3] = prod;
		
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
