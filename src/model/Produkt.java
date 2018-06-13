package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Produkt
 *
 */
@Entity
@Table(name="WsProdukt")

public class Produkt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private String name;
	private String beschreibung;
	@Column(columnDefinition = "number")
	private double preis;
	private String bildpfad;
	
	@ManyToOne
	@JoinColumn(name="kategorieId")
	private Kategorie kategorie;
	
	public Produkt() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public String getBildpfad() {
		return bildpfad;
	}

	public void setBildpfad(String bildpfad) {
		this.bildpfad = bildpfad;
	}

	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	public String toString() {
		return "A [at1=" + id + ", at2=" + name + ", at3=" + beschreibung + ", at4=" + preis + "\nbs=" + bildpfad + "]";
	}
	
	
}
