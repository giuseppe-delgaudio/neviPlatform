package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="metastasi")
public class Metastasi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id_metastasi; 
	private String metastasi;
	private String organi_coinvolti;
	
	@ManyToOne @JoinColumn( name="num_caso_clinico" )
	private CasoClinico casoClinico; 
	
	public Metastasi() {
		
		super(); 
	
	}

	
	
	public CasoClinico getCasoClinico() {
		return casoClinico;
	}





	public void setCasoClinico(CasoClinico casoClinico) {
		this.casoClinico = casoClinico;
	}





	public Integer getId_metastasi() {
		return id_metastasi;
	}

	public void setId_metastasi(Integer id_metastasi) {
		this.id_metastasi = id_metastasi;
	}

	public String getMetastasi() {
		return metastasi;
	}

	public void setMetastasi(String metastasi) {
		this.metastasi = metastasi;
	}

	public String getOrgani_coinvolti() {
		return organi_coinvolti;
	}

	public void setOrgani_coinvolti(String organi_coinvolti) {
		this.organi_coinvolti = organi_coinvolti;
	}
	
	
	
}
