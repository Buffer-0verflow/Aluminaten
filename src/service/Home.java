package service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dao.*;
import model.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BestellpositionManager bm = new BestellpositionManager();
		System.out.println("Test");
		Collection<Bestellposition> bp = bm.listBestellposition();
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		
		for(Bestellposition b : bp) {
			if(map.containsKey(b.getId())) {
				map.put(b.getId(), map.get(b.getId()+b.getMenge()));
			}else {
				map.put(b.getId(), b.getMenge());
			}
		}
		
		Long first = 7000L;
		Long second = 7002L;
		
		for (Long id :map.keySet()) {
			if(map.get(id) > map.get(first)) {
				first = id; 
			} else if (map.get(id) > map.get(second)) {
				second = id;
			}
		}
		
		Object[] arr = new Object[2];
		arr[0] = bm.findById(first).getProdukt();
		arr[1] = bm.findById(second).getProdukt();
		
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
