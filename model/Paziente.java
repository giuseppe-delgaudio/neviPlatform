package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import dao.PazienteDAO;



@Entity
@Table(name="pazienti")
@NamedQueries({
				@NamedQuery( name="nomeCognome" , query="SELECT paz FROM Paziente paz WHERE paz.nome_cognome = ?1" ),
				@NamedQuery( name="CF" , query="SELECT paz FROM Paziente paz WHERE paz.cf_paziente = ?1" ),
				@NamedQuery( name="getAllPazienti" , query="SELECT paz FROM Paziente paz" ),
				@NamedQuery( name="getByMed" , query="SELECT DISTINCT paziente FROM CasoClinico paz WHERE paz.id_staff_medico.id_account = ?1" ),
				@NamedQuery( name="getNumVisit" , query="SELECT paz from CasoClinico paz WHERE paz.paziente.id =?1" )
				 
})
public class Paziente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String cf_paziente;
	
	private String password;
	
	@OneToMany( mappedBy = "paziente" , fetch =FetchType.EAGER )
	private Set<CasoClinico> casiClinici;
	
	private String nome_cognome;
	
	@OneToOne(mappedBy="paziente")
	private Abitudini abitudini; 
	
	@OneToOne(mappedBy="paziente")
	private Fenotipo fenotipo;
	
	@OneToMany( mappedBy ="id_paziente" , fetch =FetchType.EAGER )
	private Set<Familiarita> parenti;
	
	@Transient
	private Integer numOfVisit;
	
	public Paziente() {}

	public Paziente(String cf_paziente, String password , String nome_cognome) {
		super();
		this.cf_paziente = cf_paziente;
		this.password = password;
		this.nome_cognome = nome_cognome;
	}

	public String getCf_paziente() {
		return cf_paziente;
	}

	public void setCf_paziente(String cf_paziente) {
		this.cf_paziente = cf_paziente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome_cognome() {
		return nome_cognome;
	}

	public void setNome_cognome(String nome_cognome) {
		this.nome_cognome = nome_cognome;
	}

	public Abitudini getAbitudini() {
		return abitudini;
	}

	public void setAbitudini(Abitudini abitudini) {
		this.abitudini = abitudini;
	}

	public Fenotipo getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(Fenotipo fenotipo) {
		this.fenotipo = fenotipo;
	}

	public Set<Familiarita> getParenti() {
		return parenti;
	}

	public void setParenti(Set<Familiarita> parenti) {
		this.parenti = parenti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public Integer getNumVisit() {
		
			PazienteDAO dao = new PazienteDAO();
			
			
			numOfVisit = dao.getNumOfVisite(this.id);
			return this.numOfVisit;
	}

	public Set<CasoClinico> getCasiClinici() {
		return casiClinici;
	}

	public void setCasiClinici(Set<CasoClinico> casiClinici) {
		this.casiClinici = casiClinici;
	}
	
	
	
	
	
	
}
