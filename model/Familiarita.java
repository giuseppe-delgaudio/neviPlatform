package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="familiarita" )
public class Familiarita implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id_familiarita;
	@ManyToOne @JoinColumn( name ="id_paziente" ,
							foreignKey = @ForeignKey( name="familiarita_ibfk_1" ))
	private Paziente id_paziente;
	@ManyToOne @JoinColumn( name ="id_parente" ,
			foreignKey = @ForeignKey( name="familiarita_ibfk_2"))
	private Paziente id_parente; 
	private String genetica;

	
	
	public Familiarita() {
		super();
	}



	public Familiarita(int id, Paziente id_paziente, Paziente id_parente, String genetica) {
		super();
		this.id_familiarita = id;
		this.id_paziente = id_paziente;
		this.id_parente = id_parente;
		this.genetica = genetica;
	}



	public int getId_account() {
		return id_familiarita;
	}



	public void setId_account(int id_account) {
		this.id_familiarita = id_account;
	}



	public Paziente getId_paziente() {
		return id_paziente;
	}



	public void setId_paziente(Paziente id_paziente) {
		this.id_paziente = id_paziente;
	}



	public Paziente getId_parente() {
		return id_parente;
	}



	public void setId_parente(Paziente id_parente) {
		this.id_parente = id_parente;
	}



	public String getGenetica() {
		return genetica;
	}



	public void setGenetica(String genetica) {
		this.genetica = genetica;
	}

	

	
	
	
	
}
