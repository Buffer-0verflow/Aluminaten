package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bewertung
 *
 */
@Entity
@Table(name="WsBewertung")

public class Bewertung implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private int punkte;
	private String kommentar;
	private Timestamp zeitstempel;
	
	@ManyToOne
	@JoinColumn(name="kundeId")
	private Kunde kunde;
	
	@ManyToOne
	@JoinColumn(name="produktId")
	private Produkt produkt;
	
	public Bewertung() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommtentar(String kommtentar) {
		this.kommentar = kommtentar;
	}

	public Timestamp getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
   
}
