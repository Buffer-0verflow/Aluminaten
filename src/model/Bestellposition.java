package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bestellposition
 *
 */
@Entity
@Table(name="WsBestellposition")

public class Bestellposition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private int menge;
	private String groesse;
	
	@ManyToOne
	@JoinColumn(name="bestellId")
	private Bestellung bestellung;
	
	@OneToOne
	@JoinColumn(name="produktId")
	private Produkt produkt;
	
	public Bestellposition() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
	
   
}
