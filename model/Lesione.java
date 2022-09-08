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
@Table( name = "lesioni" )
public class Lesione implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id_lesioni;
	private String diametro_lesione;
	private String lesioni_satellite;
	private String sede_anatomica;
	private String tipo_lesione;
	private String margini; 
	private String staging;
	@ManyToOne @JoinColumn(name="num_caso_clinico")
	private CasoClinico casoClinico;
	
	
	
	
	public Lesione() {
		super();
		
	}
	
	
	
	
	public Lesione(int id_lesioni, String diametro_lesione, String lesioni_satellite, String sede_anatomica,
			String tipo_lesione, String margini, String staging, CasoClinico casoClinico) {
		super();
		this.id_lesioni = id_lesioni;
		this.diametro_lesione = diametro_lesione;
		this.lesioni_satellite = lesioni_satellite;
		this.sede_anatomica = sede_anatomica;
		this.tipo_lesione = tipo_lesione;
		this.margini = margini;
		this.staging = staging;
		this.casoClinico = casoClinico;
	}


	


	public String getDiametro_lesione() {
		return diametro_lesione;
	}




	public void setDiametro_lesione(String diametro_lesione) {
		this.diametro_lesione = diametro_lesione;
	}




	public String getSede_anatomica() {
		return sede_anatomica;
	}




	public void setSede_anatomica(String sede_anatomica) {
		this.sede_anatomica = sede_anatomica;
	}




	public String getTipo_lesione() {
		return tipo_lesione;
	}




	public void setTipo_lesione(String tipo_lesione) {
		this.tipo_lesione = tipo_lesione;
	}




	public CasoClinico getCasoClinico() {
		return casoClinico;
	}


	public void setCasoClinico(CasoClinico casoClinico) {
		this.casoClinico = casoClinico;
	}


	public Integer getId_lesioni() {
		return id_lesioni;
	}

	public void setId_lesioni(Integer id_lesioni) {
		this.id_lesioni = id_lesioni;
	}
	public String getLesioni_satellite() {
		return lesioni_satellite;
	}
	public void setLesioni_satellite(String lesioni_satellite) {
		this.lesioni_satellite = lesioni_satellite;
	}
	public String getMargini() {
		return margini;
	}
	public void setMargini(String margini) {
		this.margini = margini;
	}
	public String getStaging() {
		return staging;
	}
	public void setStaging(String staging) {
		this.staging = staging;
	}
	
	
	
	

}
