package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KategorieManager;
import dao.ProduktManager;
import model.Kategorie;


/** 
 * Servlet implementation class Kategorie
 */
@WebServlet("/KategorieServlet")
public class KategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProduktManager pManager = new ProduktManager();
		KategorieManager kManager = new KategorieManager();

		Collection<Kategorie> listKat = kManager.listKategorie();
		List<Object> listProd = new ArrayList<Object>();
		Object[] arr = new Object[2];
		arr[0] = listKat;
		arr[1] = listProd;
		
		for(Kategorie katId : listKat) {
			 listProd.add(pManager.findByKategorie(katId.getId()));
		}
		Jsonb jsonb = JsonbBuilder.create();
		String jsn = jsonb.toJson(arr);
		System.out.println(jsn);
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
