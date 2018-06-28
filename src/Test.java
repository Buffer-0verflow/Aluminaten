import model.*;
import dao.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		ProduktManager p = new ProduktManager();
		Collection<Produkt> q = p.findByKategorie(3000);
		System.out.println(q.isEmpty());
		for(Produkt etwas : q)
			System.out.println(etwas);
	}
	

}
