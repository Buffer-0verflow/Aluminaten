package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Kategorie
 *
 */
@Entity
@Table(name="WsKategorie")

public class Kategorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private String name;
	
	public Kategorie() {
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
	public String toString() {
		return "A [at1=" + id + ", at2=" + name+"]";
	}
   
}
