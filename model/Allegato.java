package model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="allegati" )
public class Allegato implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static enum Tipo_File { PNG,JPEG,PDF,GENERICO }
	public static enum Tipo_Tag{ NEVI , TAC , RISONANZA , REFERTO }
	
	@Id @GeneratedValue( strategy= GenerationType.IDENTITY )
	@Column(name="id_allegato")
	private Integer id_allegato;
	
	@Enumerated( EnumType.STRING )
	private Tipo_File tipo_file;  

	@Enumerated( EnumType.STRING )
	private Tipo_Tag tag;  
	

	private String predict;
	
	private String file;
	
	@ManyToOne @JoinColumn( name="caso_clinico" ,
							foreignKey = @ForeignKey( name="allegati_ibfk_1" ))
	private CasoClinico casoClinico;

	public Allegato(Integer id_allegato, Tipo_File tipo_file, String file, CasoClinico casoClinico) {
		super();
		this.id_allegato = id_allegato;
		this.tipo_file = tipo_file;
		this.file = file;
		this.casoClinico = casoClinico;
	}
	
	
	
	public Tipo_Tag getTag() {
		return tag;
	}



	public void setTag(Tipo_Tag tag) {
		this.tag = tag;
	}



	public String getPredict() {
		return predict;
	}



	public void setPredict(String predict) {
		this.predict = predict;
	}



	public Allegato() {
		
		super();
		
	}


	public Integer getId_allegato() {
		return id_allegato;
	}


	public void setId_allegato(Integer id_allegato) {
		this.id_allegato = id_allegato;
	}


	public Tipo_File getTipo_file() {
		return tipo_file;
	}


	public void setTipo_file(Tipo_File tipo_file) {
		this.tipo_file = tipo_file;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public CasoClinico getCasoClinico() {
		return casoClinico;
	}


	public void setCasoClinico(CasoClinico casoClinico) {
		this.casoClinico = casoClinico;
	}
	
	
	

}
