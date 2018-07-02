package dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Bestellposition;
import model.Bestellung;
import model.Produkt;

public class BestellpositionManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	public void insertBestellposition(long id, int menge, String groesse, Bestellung b, Produkt p) {
		em.getTransaction().begin();
		
		Bestellposition bp = new Bestellposition();
		bp.setId(id);
		bp.setMenge(menge);
		bp.setGroesse(groesse);
		bp.setBestellung(b);
		bp.setProdukt(p);
		
		em.persist(bp);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	/* NEW */
	@SuppressWarnings("unchecked")
	public Collection<Bestellposition> getByKunde(long id) {
		Query query = em.createQuery("Select bp  "
				+ "from Bestellposition as bp JOIN bp.bestellung as b JOIN bp.produkt as p"
				+ " JOIN b.kunde as k WHERE k.id=:kundeID");
		query.setParameter("kundeID", id);
		Collection<Bestellposition> bestellungCollection = new ArrayList<Bestellposition>();
		for (Bestellposition bestellung : (ArrayList<Bestellposition>) query.getResultList())
			bestellungCollection.add(bestellung);
		return bestellungCollection;
	}
	
	/*
	public void updateBestellposition(long id, int menge, String groesse, Bestellung b, Produkt p) {
		em.getTransaction().begin();
		
		Bestellposition bp = em.find(Bestellposition.class, id);
		if(bp != null) {
			bp.setMenge(menge);
			bp.setGroesse(groesse);
			bp.setBestellung(b);
			bp.setProdukt(p);
		}
		else {
			bp = new Bestellposition();
			bp.setId(id);
			bp.setMenge(menge);
			bp.setGroesse(groesse);
			bp.setBestellung(b);
			bp.setProdukt(p);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	*/
	
	public void updateBestellposition(long id, int menge, String groesse, Bestellung b, Produkt p) {
		em.getTransaction().begin();
		
		Bestellposition best = new Bestellposition();
		best.setId(id);
		best.setMenge(menge);
		best.setGroesse(groesse);
		best.setBestellung(b);
		best.setProdukt(p);
		
		Bestellposition bep = em.find(Bestellposition.class, best.getId());
		
		if(bep == null) {
			em.persist(best);
		} else {
			em.merge(best);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public void deleteBestellposition(long id) {
		Bestellposition bp = em.find(Bestellposition.class, id);
		if(bp != null) {
			em.remove(bp);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bestellposition> listBestellposition() {
		Query query = em.createQuery("Select b from Bestellposition b");
		Collection<Bestellposition> bestCollection = new ArrayList<Bestellposition>();
		for(Bestellposition best : (ArrayList<Bestellposition>) query.getResultList())
			bestCollection.add(best);
		return bestCollection;
	}
}