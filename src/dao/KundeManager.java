package dao;

import model.Adresse;
import model.Kunde;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class KundeManager {
	//insert, update, delete, find, list
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("XX");
	EntityManager em = emf.createEntityManager();
	
	public void insertKunde(String vorname, String nachname, String email, Date geburtstag, String benutzername, String passwort, Long adrID) {
		em.getTransaction().begin();
		
		Kunde kunde = new Kunde();
		kunde.setVorname(vorname);
		kunde.setNachname(nachname);
		kunde.setEmail(email);
		kunde.setGeburtstag(geburtstag);
		kunde.setBenutzername(benutzername);
		kunde.setPasswort(passwort);
		kunde.setAdresse(em.find(Adresse.class, adrID));
		
		em.persist(kunde);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	/*public void updateKunde(long id, String vorname, String nachname, String email, Date geburtstag, String benutzername, String passwort, Adresse adr) {
		em.getTransaction().begin();
		
		Kunde kunde = em.find(Kunde.class, id);
		
		if(kunde != null) {
			kunde.setVorname(vorname);
			kunde.setNachname(nachname);
			kunde.setEmail(email);
			kunde.setGeburtstag(geburtstag);
			kunde.setBenutzername(benutzername);
			kunde.setPasswort(passwort);
			kunde.setAdresse(adr);
		}
		else {
			em.persist(new Kunde(id, vorname, nachname, email, geburtstag, benutzername, passwort));
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}*/
	
	public void updateKunde(long id, String vorname, String nachname, String email, Date geburtstag, String benutzername, String passwort, Long adrID) {
		em.getTransaction().begin();
		
		Kunde kunde = new Kunde();
		kunde.setId(id);
		kunde.setVorname(vorname);
		kunde.setNachname(nachname);
		kunde.setGeburtstag(geburtstag);
		kunde.setBenutzername(benutzername);
		kunde.setPasswort(passwort);
		kunde.setEmail(email);
		kunde.setAdresse(em.find(Adresse.class, adrID));
		
		Kunde k = em.find(Kunde.class, kunde.getId());
		if (k == null) {
			em.persist(kunde);
		} else {
			em.merge(kunde);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	public void deleteKunde(long id) {
		em.getTransaction().begin();
		
		Kunde kunde = em.find(Kunde.class, id);
		if(kunde != null) {
			em.remove(kunde);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Kunde> listKunde() {
		Query query = em.createQuery("Select k from Kunde k");
		Collection<Kunde> kundeCollection = new ArrayList<Kunde>();
		for (Kunde kunde : (ArrayList<Kunde>) query.getResultList())
			kundeCollection.add(kunde);
		return kundeCollection;
	}
	
	
	public Kunde findById(long id) {
		Query query = em.createQuery("Select k from Kunde k where k.id = :id");
		query.setParameter("id", id);
		Kunde k = (Kunde) query.getSingleResult();
		em.refresh(k);
		return k;
	}
		
	public Kunde findByUsername(String username) {
		Query query = em.createQuery("Select k from Kunde k where k.benutzername = :username");
		query.setParameter("username", username);
		return (Kunde) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Kunde> findByNachname(String nachname) {
		Query query = em.createQuery("Select k from Kunde k where k.nachname = :nachname");
		query.setParameter("nachname", nachname);
		Collection<Kunde> kundeCollection = new ArrayList<Kunde>();
		for(Kunde kunde : (ArrayList<Kunde>) query.getResultList())
			kundeCollection.add(kunde);
		return kundeCollection;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Kunde> findByVorname(String vorname) {
		Query query = em.createQuery("Select k from Kunde k where k.vorname = :vorname");
		query.setParameter("vorname", vorname);
		Collection<Kunde> kundeCollection = new ArrayList<Kunde>();
		for(Kunde kunde : (ArrayList<Kunde>) query.getResultList())
			kundeCollection.add(kunde);
		return kundeCollection;
	}
	
	
	public Kunde findByEmail(String email) {
		Query query = em.createQuery("Select k from Kunde k where k.email = :email");
		query.setParameter("email", email);
		return (Kunde) query.getSingleResult();
	}
}
	