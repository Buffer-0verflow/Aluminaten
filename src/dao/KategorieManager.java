package dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Kategorie;

public class KategorieManager {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	public void insertKategorie(long id, String name) {
		em.getTransaction().begin();
		
		Kategorie kat = new Kategorie();
		kat.setId(id);
		kat.setName(name);
		
		em.persist(kat);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	public void updateKategorie(long id, String name) {
		em.getTransaction().begin();
		
		Kategorie kat = new Kategorie();
		
		kat.setId(id);
		kat.setName(name);
		
		Kategorie k = em.find(Kategorie.class, kat.getId());
		if(k == null) {
			em.persist(kat);
		} else {
			em.merge(kat);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	public void deleteKategorie(long id) {
		em.getTransaction().begin();
		
		Kategorie kat = em.find(Kategorie.class, id);
		if(kat != null) {
			em.remove(kat);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Kategorie> listKategorie() {
		Query query = em.createQuery("Select k from Kategorie k");
		Collection<Kategorie> katCollection = new ArrayList<Kategorie>();
		for(Kategorie kat : (ArrayList<Kategorie>) query.getResultList())
			katCollection.add(kat);
		return katCollection;
	}
	
	
	public Kategorie findKategorieById(long id) {
		Query query = em.createQuery("Select k from Kategorie k where k.id = :id");
		query.setParameter("id", id);
		return (Kategorie) query.getSingleResult();
	}
	
	
	public Kategorie findKategorieByName(String name) {
		Query query = em.createQuery("Select k from Kategorie k where k.name = :name");
		query.setParameter("name", name);
		return (Kategorie) query.getSingleResult();
	}
}