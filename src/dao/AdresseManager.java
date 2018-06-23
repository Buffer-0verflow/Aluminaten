package dao;

import model.Adresse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AdresseManager {
	
	//update, insert, delete
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	public Long insertAdresse(String strasse, String plz, String wohnort) {
		em.getTransaction().begin();
		
		Adresse adr = new Adresse();
		adr.setPlz(plz);
		adr.setStrasse(strasse);
		adr.setWohnort(wohnort);
		em.persist(adr);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		Long id = adr.getId();
		return id;
	}
	
	
	/*public void updateAdresse(int id, String strasse, String plz, String wohnort) {
		em.getTransaction().begin();
		
		Adresse adr = em.find(Adresse.class, id);
		if(adr!=null) {
			adr.setPlz(plz);
			adr.setStrasse(strasse);
			adr.setWohnort(wohnort);
		}
		else {
			em.persist(new Adresse (id, strasse, plz, wohnort));
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}*/
	
	public Long updateAdresse(long id, String strasse, String plz, String wohnort) {
		em.getTransaction().begin();
		
		Adresse adresse = new Adresse();
		adresse.setId(id);
		adresse.setStrasse(strasse);
		adresse.setPlz(plz);
		adresse.setWohnort(wohnort);
		
		Adresse adr = em.find(Adresse.class, adresse.getId());
		
		if(adr == null) {
			em.persist(adresse);
		} else {
			em.merge(adresse);
		}
		
		em.getTransaction().commit();
		Long idR = adresse.getId();
		em.close();
		emf.close();
		return idR;
		
	}
	
	public Adresse findById(long id) {
		Query query = em.createQuery("Select k from Adresse k where k.id = :id");
		query.setParameter("id", id);
		return (Adresse) query.getSingleResult();
	}
	
	
	public void deleteAdresse(long id) {
		em.getTransaction().begin();
		
		Adresse adr = em.find(Adresse.class, id);
		if(adr != null) {
			em.remove(adr);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
