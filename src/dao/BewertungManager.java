package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Bewertung;
import model.Kunde;
import model.Produkt;

public class BewertungManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	public void insertBewertung(long id, int punkte, String kommentar, Timestamp zeitstempel, Kunde k, Produkt p) {
		em.getTransaction().begin();
		
		Bewertung bew = new Bewertung();
		bew.setId(id);
		bew.setPunkte(punkte);
		bew.setKommtentar(kommentar);
		bew.setZeitstempel(zeitstempel);
		bew.setKunde(k);
		bew.setProdukt(p);
		
		em.persist(bew);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public void updateBewertung(long id, int punkte, String kommentar, Timestamp zeitstempel /* erzeugen lassen */, Kunde k, Produkt p) {
		em.getTransaction().begin();
		
		Bewertung bew = em.find(Bewertung.class, id);
		if(bew != null) {
			bew.setPunkte(punkte);
			bew.setKommtentar(kommentar);
			bew.setZeitstempel(zeitstempel);
			bew.setKunde(k);
			bew.setProdukt(p);
		}
		else {
			bew = new Bewertung();
			bew.setId(id);
			bew.setPunkte(punkte);
			bew.setKommtentar(kommentar);
			bew.setZeitstempel(zeitstempel);
			bew.setKunde(k);
			bew.setProdukt(p);
		}
		em.persist(bew);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	public void deleteBewertung(long id) {
		em.getTransaction().begin();
		
		Bewertung bew = em.find(Bewertung.class, id);
		if(bew != null) {
			em.remove(bew);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bewertung> list() {
		Query query = em.createQuery("Select b from Bewertung b");
		Collection<Bewertung> bewCollection = new ArrayList<Bewertung>();
		for(Bewertung bew : (ArrayList<Bewertung>) query.getResultList())
			bewCollection.add(bew);
		return bewCollection;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bewertung> findBewertungById(long id) {
		Query query = em.createQuery("Select b from Bewertung b where b.id = :id");
		query.setParameter("id", id);
		Collection<Bewertung> bewCollection = new ArrayList<Bewertung>();
		for(Bewertung bew : (ArrayList<Bewertung>) query.getResultList())
			bewCollection.add(bew);
		return bewCollection;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bewertung> findBewertungByPunkte(int punkte) {
		Query query = em.createQuery("Select b from Bewertung b where b.punkte = :punkte");
		query.setParameter("punkte", punkte);
		Collection<Bewertung> bewCollection = new ArrayList<Bewertung>();
		for(Bewertung bew : (ArrayList<Bewertung>) query.getResultList())
			bewCollection.add(bew);
		return bewCollection;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Bewertung> getBewertungByProdukt(Produkt p) {
		Query query = em.createQuery("Select b from Bewertung b where b.produkt = :produkt");
		query.setParameter("produkt", p);
		Collection<Bewertung> bewCollection = new ArrayList<Bewertung>();
		for(Bewertung bew : (ArrayList<Bewertung>) query.getResultList())
			bewCollection.add(bew);
		return bewCollection;
	}
}