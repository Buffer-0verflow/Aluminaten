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
    
    BewertungManager bManager = new BewertungManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Produkt p = null;		// still to fix 
		Collection<Bewertung> comments = bManager.getBewertungByProdukt(p);	
		Jsonb jsonb = JsonbBuilder.create();
		String jsn = jsonb.toJson(comments);
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
