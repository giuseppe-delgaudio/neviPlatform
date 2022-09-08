package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name="abitudini" )
public class Abitudini implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne @JoinColumn( name="id_paziente" , 
							foreignKey=@ForeignKey( name="abitudini_ibfk_1" ) )
	private Paziente paziente; 
	private String abitudini_fotoprotezione; 
	private String attivita_lavorativa_all_aperto; 
	private String attivita_creativa_all_aperto;
	
	
	
	
	public Abitudini() {
		super();
	}




	public Abitudini(Paziente paziente, String abitudini_fotoprotezione, String attivita_lavorativa_all_aperto,
			String attivita_creativa_all_aperto) {
		super();
		this.paziente = paziente;
		this.abitudini_fotoprotezione = abitudini_fotoprotezione;
		this.attivita_lavorativa_all_aperto = attivita_lavorativa_all_aperto;
		this.attivita_creativa_all_aperto = attivita_creativa_all_aperto;
	}




	public Paziente getPaziente() {
		return paziente;
	}




	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}




	public String getAbitudini_fotoprotezione() {
		return abitudini_fotoprotezione;
	}




	public void setAbitudini_fotoprotezione(String abitudini_fotoprotezione) {
		this.abitudini_fotoprotezione = abitudini_fotoprotezione;
	}




	public String getAttivita_lavorativa_all_aperto() {
		return attivita_lavorativa_all_aperto;
	}




	public void setAttivita_lavorativa_all_aperto(String attivita_lavorativa_all_aperto) {
		this.attivita_lavorativa_all_aperto = attivita_lavorativa_all_aperto;
	}




	public String getAttivita_creativa_all_aperto() {
		return attivita_creativa_all_aperto;
	}




	public void setAttivita_creativa_all_aperto(String attivita_creativa_all_aperto) {
		this.attivita_creativa_all_aperto = attivita_creativa_all_aperto;
	}
	
	
	
}
