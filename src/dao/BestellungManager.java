package dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Bestellung;
import model.Kunde;


public class BestellungManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	
	public void insertBestellung(long id, Date bestellzeitpunkt, int status, Kunde k) {
		em.getTransaction().begin();
		if(status>1) {
			status=1;
		}
		
		Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setBestellzeitpunkt(bestellzeitpunkt);
		bestellung.setStatus(status);
		bestellung.setKunde(k);
		
		em.persist(bestellung);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	public void updateBestellung(long id, Date bestellzeitpunkt, int status, Kunde k) {
		em.getTransaction().begin();
		
		Bestellung best = new Bestellung();
		best.setId(id);
		best.setBestellzeitpunkt(bestellzeitpunkt);
		best.setStatus(status);
		best.setKunde(k);
		
		Bestellung bt = em.find(Bestellung.class, best.getId());
		if(bt == null) {
			em.persist(best);
		} else {
			em.merge(best);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();		
	}
	
	
	public void deleteBestellung(long id) {
		em.getTransaction().begin();
		Bestellung bestellung = em.find(Bestellung.class, id);
		if(bestellung != null) {
			em.remove(bestellung);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bestellung> listBestellung() {
		Query query = em.createQuery("Select b from Bestellung b");
		Collection<Bestellung> bestellungCollection = new ArrayList<Bestellung>();
		for (Bestellung bestellung : (ArrayList<Bestellung>) query.getResultList())
			bestellungCollection.add(bestellung);
		return bestellungCollection;
	}
	
	
	public Bestellung findBestellungById(long id) {
		Query query = em.createQuery("Select b from Bestellung b where b.id = :id");
		query.setParameter("id", id);
		return (Bestellung) query.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bestellung> findBestellungByBestellzeitpunkt(Date bestZeit) {
		Query query = em.createQuery("Select b from Bestellung where b.Bestellzeitpunkt = :bestZeit");
		query.setParameter("bestZeit", bestZeit);
		Collection<Bestellung> bestellungCollection = new ArrayList<Bestellung>();
		for (Bestellung bestellung : (ArrayList<Bestellung>) query.getResultList())
			bestellungCollection.add(bestellung);
		return bestellungCollection;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bestellung> findBestellungByStatus(int status) {
		Query query = em.createQuery("Select b from Bestellung where b.Status = :status");
		query.setParameter("status", status);
		Collection<Bestellung> bestellungCollection = new ArrayList<Bestellung>();
		for (Bestellung bestellung : (ArrayList<Bestellung>) query.getResultList())
			bestellungCollection.add(bestellung);
		return bestellungCollection;
	}
}