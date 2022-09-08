package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name ="fenotipo" )
public class Fenotipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static enum Fototipo{I,II,III,IV,V,VI};
	
	@Id
	@OneToOne @JoinColumn( name="id_paziente" , 
							foreignKey=@ForeignKey(name="fenotipo_ibfk_1"))
	private Paziente paziente; 
	
	@Column( name="fototipo" )
	@Enumerated( EnumType.STRING )
	private Fototipo fototipo;
	private String capelli; 
	private String occhi; 
	private String cute;
	
	
	
	public Fenotipo() {
		super();
	}



	public Fenotipo(Paziente paziente, Fototipo fototipo, String capelli, String occhi, String cute) {
		super();
		this.paziente = paziente;
		this.fototipo = fototipo;
		this.capelli = capelli;
		this.occhi = occhi;
		this.cute = cute;
	}



	public Paziente getPaziente() {
		return paziente;
	}



	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}



	public Fototipo getFototipo() {
		return fototipo;
	}



	public void setFototipo(Fototipo fototipo) {
		this.fototipo = fototipo;
	}



	public String getCapelli() {
		return capelli;
	}



	public void setCapelli(String capelli) {
		this.capelli = capelli;
	}



	public String getOcchi() {
		return occhi;
	}



	public void setOcchi(String occhi) {
		this.occhi = occhi;
	}



	public String getCute() {
		return cute;
	}



	public void setCute(String cute) {
		this.cute = cute;
	}
	
	
	
	
	
}
