package model;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bestellung
 *
 */
@Entity
@Table(name="WsBestellung")

public class Bestellung implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private Date bestellzeitpunkt;
	private int status; 
	
	@ManyToOne
	@JoinColumn(name="kundeId")
	private Kunde kunde;
	
	public Bestellung() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBestellzeitpunkt() {
		return bestellzeitpunkt;
	}

	public void setBestellzeitpunkt(Date bestellzeitpunkt) {
		this.bestellzeitpunkt = bestellzeitpunkt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	
   
}
