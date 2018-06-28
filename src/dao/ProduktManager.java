package dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Kategorie;
import model.Produkt;

public class ProduktManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	
	public void insertProdukt(long id, String name, String beschreibung, double preis, String bildpfad, Kategorie k) {
		em.getTransaction().begin();
		
		Produkt prod = new Produkt();
		prod.setId(id);
		prod.setName(name);
		prod.setBeschreibung(beschreibung);
		prod.setPreis(preis);
		prod.setBildpfad(bildpfad);
		prod.setKategorie(k);
	}
	
	
	public void updateProdukt(long id, String name, String beschreibung, double preis, String bildpfad, Kategorie k) {
		em.getTransaction().begin();
		
		Produkt prod = new Produkt();
		prod.setId(id);
		prod.setName(name);
		prod.setBeschreibung(beschreibung);
		prod.setPreis(preis);
		prod.setBildpfad(bildpfad);
		prod.setKategorie(k);
		
		Produkt p = em.find(Produkt.class, prod.getId());
		if(p==null) {
			em.persist(prod);
		} else {
			em.merge(prod);
		}
		em.getTransaction().commit();
	}
	
	
	public void deleteProdukt(long id) {
		em.getTransaction().begin();
		
		Produkt prod = em.find(Produkt.class, id);
		if(prod != null) {
			em.remove(prod);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Produkt> listProdukt() {
		Query query = em.createQuery("Select p from Produkt p");
		Collection<Produkt> prodCollection = new ArrayList<Produkt>();
		for(Produkt prod : (ArrayList<Produkt>) query.getResultList())
			prodCollection.add(prod);
		return prodCollection;
	}
	
	
	public Produkt findProdById(long id) {
		Query query = em.createQuery("Select p from Produkt p where p.id = :id");
		query.setParameter("id", id);
		return (Produkt) query.getSingleResult();
	}
	
	
	public Produkt findProdByName(String name) {
		Query query = em.createQuery("Select p from Produkt p where p.name = :name");
		query.setParameter("name", name);
		return (Produkt) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Produkt> findByKategorie(long id) {
		Query query = em.createQuery("Select p from Produkt p join p.kategorie k where k.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
