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
@Table( name="asportazioni" )
public class Asportazioni implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue( strategy= GenerationType.IDENTITY)
	private Integer idasportazioni; 
	private String pregresse_asportazioni;
	
	@ManyToOne @JoinColumn( name="num_caso_clinico" )
	private CasoClinico casoClinico; 
	private Date data_asportazione; 
	private String spf;
	
	
	
	public Asportazioni() {
		super();
	}
	public Asportazioni(Integer idasportazioni, String pregresse_asportazioni, CasoClinico caso_clinico, Date data_asportazione,
			String spf) {
		super();
		this.idasportazioni = idasportazioni;
		this.pregresse_asportazioni = pregresse_asportazioni;
		this.casoClinico = caso_clinico;
		this.data_asportazione = data_asportazione;
		this.spf = spf;
	}
	public Integer getIdasportazioni() {
		return idasportazioni;
	}
	public void setIdasportazioni(Integer idasportazioni) {
		this.idasportazioni = idasportazioni;
	}
	public String getPregresse_asportazioni() {
		return pregresse_asportazioni;
	}
	public void setPregresse_asportazioni(String pregresse_asportazioni) {
		this.pregresse_asportazioni = pregresse_asportazioni;
	}
	public CasoClinico getcasoClinico() {
		return casoClinico;
	}
	public void setNum_casoClinico(CasoClinico casoClinico) {
		this.casoClinico = casoClinico;
	}
	public Date getData_asportazione() {
		return data_asportazione;
	}
	public void setData_asportazione(Date data_asportazione) {
		this.data_asportazione = data_asportazione;
	}
	public String getSpf() {
		return spf;
	}
	public void setSpf(String spf) {
		this.spf = spf;
	}
	
	
	
}
