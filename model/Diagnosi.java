package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="diagnosi" )
public class Diagnosi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id_diagnosi; 
	private String diagnosi;  
	private Date anno_diagnosi;
	
	@ManyToOne @JoinColumn( name="num_caso_clinico" )
	private CasoClinico casoClinico;
	
	
	
	public Diagnosi() {
		super();
	}
	public Diagnosi(Integer id_diagnosi, String diagnosi, Date anno_diagnosi, CasoClinico num_caso_clinico) {
		super();
		this.id_diagnosi = id_diagnosi;
		this.diagnosi = diagnosi;
		this.anno_diagnosi = anno_diagnosi;
		this.casoClinico = num_caso_clinico;
	}
	public Integer getId_diagnosi() {
		return id_diagnosi;
	}
	public void setId_diagnosi(Integer id_diagnosi) {
		this.id_diagnosi = id_diagnosi;
	}
	public String getDiagnosi() {
		return diagnosi;
	}
	public void setDiagnosi(String diagnosi) {
		this.diagnosi = diagnosi;
	}
	public Date getAnno_diagnosi() {
		return anno_diagnosi;
	}
	public void setAnno_diagnosi(Date anno_diagnosi) {
		this.anno_diagnosi = anno_diagnosi;
	}
	public CasoClinico getNum_caso_clinico() {
		return casoClinico;
	}
	public void setNum_caso_clinico(CasoClinico caso_clinico) {
		this.casoClinico = caso_clinico;
	} 
	
	
	
	
}
