package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.sun.istack.internal.Nullable;

@Entity
@Table( name="caso_clinico" )
public class CasoClinico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Tipo_melanoma { diffusionesuperficiale ,
									   lentigomaligna ,
									   lentigginosoacrale,
									   nodulare }
	public static enum Spessore_breslow { I , II , III , IV , V }
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="id_caso_clinico" )
	private int id_caso_clinico;
	
	@ManyToOne( cascade=CascadeType.REMOVE ) @JoinColumn( name = "id_paziente"  )
	private Paziente paziente;
	
	@Column( name="ulcerazione" )
	private String ulcerazione;
	
	@Column( name="lesione_pigmentata" )
	private String lesione_pigmentata;
	
	@Column( name ="tipo_melanoma")
	@Enumerated( EnumType.STRING )
	private Tipo_melanoma tipo_melanoma; 
	
	@Column( name="indice_mitotico" )
	private String indice_mitotico;
	
	@Column( name="spessore_di_breslow" )
	@Enumerated( EnumType.STRING )
	private Spessore_breslow spessore_di_breslow;
	
	@Column( name="regressione" )
	private String regressione; 
	
	@Column( name="invasione_perivascolare" )
	private String invasione_perivascolare; 
	
	@Column( name="invasione_perineurale" )
	private String invasione_perineurale;
	
	@Column( name="linfociti_infiltranti" )
	private String linfociti_infiltranti;
	
	@Column( name="nevo_melanocitico_associato" )
	private String nevo_melanocitico_associato;
	
	@Column( name="bcc_associato" )
	private String bcc_associato;
	
	@Nullable
	@Column( name="numero_linfonodi_esaminati" )
	private Integer numero_linfonodi_esaminati;
	
	@Nullable
	@Column( name="numero_linfonodi_metastatici" )
	private Integer numero_linfonodi_metastatici;
	
	@Nullable
	@Column( name="numero_linfonodi_sentinella" )
	private Integer numero_linfonodi_sentinella;
	
	@Nullable
	@Column( name="numero_linfonodi_sentinella_piu")
	private Integer numero_linfonodi_sentinella_piu;
	
	@Column( name="ABCDE" )
	private String ABCDE;
	
	@Column( name="SEVEN_POINT_CHECK_LIST" )
	private String SEVEN_POINT_CHECK_LIST;
	
	@Nullable
	@Column( name="numero_nevi" )
	private Integer numero_nevi;
	
	@Column( name="data_caso" )
	private Date data_caso;
	
	@ManyToOne( cascade=CascadeType.ALL ) @JoinColumn( name="id_staff_medico",
							foreignKey = @ForeignKey( name="caso_clinico_ibfk_1" ))
	private Staff id_staff_medico;
	
	@OneToMany( mappedBy="casoClinico" , cascade=CascadeType.ALL , fetch =FetchType.EAGER )
	private Set<Metastasi> metastasi;
	
	@OneToMany( mappedBy="casoClinico" , cascade=CascadeType.ALL , fetch =FetchType.EAGER)
	private Set<Lesione> lesione;
	
	@OneToMany ( mappedBy="casoClinico" , cascade=CascadeType.ALL , fetch =FetchType.EAGER)
	private Set<Diagnosi> diagnosi;
	
	@OneToMany( mappedBy="casoClinico" , cascade=CascadeType.ALL , fetch =FetchType.EAGER)
	private Set<Asportazioni> asportazioni;

	@OneToMany( mappedBy="casoClinico" , cascade=CascadeType.ALL , fetch =FetchType.EAGER)
	private Set<Allegato> allegati;
	
	public CasoClinico() {
		super();
	}


	public CasoClinico(int id_caso_clinico, Paziente paziente, String ulcerazione, String lesione_pigmentata,
			Tipo_melanoma tipo_melanoma, String indice_mitotico, Spessore_breslow spessore_di_breslow,
			String regressione, String invasione_perivascolare, String invasione_perineurale,
			String linfociti_infiltranti, String nevo_melanocitico_associato, String bcc_associato,
			Integer numero_linfonodi_esaminati, Integer numero_linfonodi_metastatici, Integer numero_linfonodi_sentinella,
			Integer numero_linfonodi_sentinella_piu, String aBCDE, String sEVEN_POINT_CHECK_LIST, Integer numero_nevi,
			Date data_caso, Staff id_staff_medico, Set<Metastasi> metastasi, Set<Lesione> lesione,
			Set<Diagnosi> diagnosi, Set<Asportazioni> asportazioni , Set<Allegato> allegati ) {
		super();
		this.id_caso_clinico = id_caso_clinico;
		this.paziente = paziente;
		this.ulcerazione = ulcerazione;
		this.lesione_pigmentata = lesione_pigmentata;
		this.tipo_melanoma = tipo_melanoma;
		this.indice_mitotico = indice_mitotico;
		this.spessore_di_breslow = spessore_di_breslow;
		this.regressione = regressione;
		this.invasione_perivascolare = invasione_perivascolare;
		this.invasione_perineurale = invasione_perineurale;
		this.linfociti_infiltranti = linfociti_infiltranti;
		this.nevo_melanocitico_associato = nevo_melanocitico_associato;
		this.bcc_associato = bcc_associato;
		this.numero_linfonodi_esaminati = numero_linfonodi_esaminati;
		this.numero_linfonodi_metastatici = numero_linfonodi_metastatici;
		this.numero_linfonodi_sentinella = numero_linfonodi_sentinella;
		this.numero_linfonodi_sentinella_piu = numero_linfonodi_sentinella_piu;
		ABCDE = aBCDE;
		SEVEN_POINT_CHECK_LIST = sEVEN_POINT_CHECK_LIST;
		this.numero_nevi = numero_nevi;
		this.data_caso = data_caso;
		this.id_staff_medico = id_staff_medico;
		this.metastasi = metastasi;
		this.lesione = lesione;
		this.diagnosi = diagnosi;
		this.asportazioni = asportazioni;
		this.allegati = allegati;
	}



	



	public Set<Allegato> getAllegati() {
		return allegati;
	}


	public void setAllegati(Set<Allegato> allegati) {
		this.allegati = allegati;
	}


	public String getABCDE() {
		return ABCDE;
	}



	



	public Set<Lesione> getLesione() {
		return lesione;
	}


	public void setLesione(Set<Lesione> lesione) {
		this.lesione = lesione;
	}


	public void setId_caso_clinico(int id_caso_clinico) {
		this.id_caso_clinico = id_caso_clinico;
	}


	public void setABCDE(String aBCDE) {
		ABCDE = aBCDE;
	}







	public String getSEVEN_POINT_CHECK_LIST() {
		return SEVEN_POINT_CHECK_LIST;
	}







	public void setSEVEN_POINT_CHECK_LIST(String sEVEN_POINT_CHECK_LIST) {
		SEVEN_POINT_CHECK_LIST = sEVEN_POINT_CHECK_LIST;
	}







	public int getId_caso_clinico() {
		return id_caso_clinico;
	}

	public void setId_caso_clinico(Integer id_caso_clinico) {
		this.id_caso_clinico = id_caso_clinico;
	}

	public Paziente getPaziente() {
		return paziente;
	}

	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}

	

	public String getUlcerazione() {
		return ulcerazione;
	}

	public void setUlcerazione(String ulcerazione) {
		this.ulcerazione = ulcerazione;
	}


	public String getLesione_pigmentata() {
		return lesione_pigmentata;
	}

	public void setLesione_pigmentata(String lesione_pigmentata) {
		this.lesione_pigmentata = lesione_pigmentata;
	}


	public Tipo_melanoma getTipo_melanoma() {
		return tipo_melanoma;
	}

	public void setTipo_melanoma(Tipo_melanoma tipo_melanoma) {
		this.tipo_melanoma = tipo_melanoma;
	}

	public String getIndice_mitotico() {
		return indice_mitotico;
	}

	public void setIndice_mitotico(String indice_mitotico) {
		this.indice_mitotico = indice_mitotico;
	}

	public Spessore_breslow getSpessore_di_breslow() {
		return spessore_di_breslow;
	}

	public void setSpessore_di_breslow(Spessore_breslow spessore_di_breslow) {
		this.spessore_di_breslow = spessore_di_breslow;
	}

	public String getRegressione() {
		return regressione;
	}

	public void setRegressione(String regressione) {
		this.regressione = regressione;
	}

	public String getInvasione_perivascolare() {
		return invasione_perivascolare;
	}

	public void setInvasione_perivascolare(String invasione_perivascolare) {
		this.invasione_perivascolare = invasione_perivascolare;
	}

	public String getInvasione_perineurale() {
		return invasione_perineurale;
	}

	public void setInvasione_perineurale(String invasione_perineurale) {
		this.invasione_perineurale = invasione_perineurale;
	}

	public String getLinfociti_infiltranti() {
		return linfociti_infiltranti;
	}

	public void setLinfociti_infiltranti(String linfociti_infiltranti) {
		this.linfociti_infiltranti = linfociti_infiltranti;
	}

	public String getNevo_melanocitico_associato() {
		return nevo_melanocitico_associato;
	}

	public void setNevo_melanocitico_associato(String nevo_melanocitico_associato) {
		this.nevo_melanocitico_associato = nevo_melanocitico_associato;
	}

	public String getBcc_associato() {
		return bcc_associato;
	}

	public void setBcc_associato(String bcc_associato) {
		this.bcc_associato = bcc_associato;
	}

	public Integer getNumero_linfonodi_esaminati() {
		return numero_linfonodi_esaminati;
	}

	public void setNumero_linfonodi_esaminati(Integer numero_linfonodi_esaminati) {
		this.numero_linfonodi_esaminati = numero_linfonodi_esaminati;
	}

	public Integer getNumero_linfonodi_metastatici() {
		return numero_linfonodi_metastatici;
	}

	public void setNumero_linfonodi_metastatici(Integer numero_linfonodi_metastatici) {
		this.numero_linfonodi_metastatici = numero_linfonodi_metastatici;
	}

	public Integer getNumero_linfonodi_sentinella() {
		return numero_linfonodi_sentinella;
	}

	public void setNumero_linfonodi_sentinella(Integer numero_linfonodi_sentinella) {
		this.numero_linfonodi_sentinella = numero_linfonodi_sentinella;
	}

	public Integer getNumero_linfonodi_sentinella_piu() {
		return numero_linfonodi_sentinella_piu;
	}

	public void setNumero_linfonodi_sentinella_piu(Integer numero_linfonodi_sentinella_piu) {
		this.numero_linfonodi_sentinella_piu = numero_linfonodi_sentinella_piu;
	}

	public Integer getNumero_nevi() {
		return numero_nevi;
	}

	public void setNumero_nevi(Integer numero_nevi) {
		this.numero_nevi = numero_nevi;
	}

	public Date getData_caso() {
		return data_caso;
	}

	public void setData_caso(Date data_caso) {
		this.data_caso = data_caso;
	}

	public Staff getId_staff_medico() {
		return id_staff_medico;
	}

	public void setId_staff_medico(Staff id_staff_medico) {
		this.id_staff_medico = id_staff_medico;
	}

	public Set<Metastasi> getMetastasi() {
		return metastasi;
	}

	public void setMetastasi(Set<Metastasi> metastasi) {
		this.metastasi = metastasi;
	}

	public Set<Lesione> getLesioni() {
		return lesione;
	}

	public void setLesioni(Set<Lesione> lesione) {
		this.lesione = lesione;
	}

	public Set<Diagnosi> getDiagnosi() {
		return diagnosi;
	}

	public void setDiagnosi(Set<Diagnosi> diagnosi) {
		this.diagnosi = diagnosi;
	}

	public Set<Asportazioni> getAsportazioni() {
		return asportazioni;
	}

	public void setAsportazioni(Set<Asportazioni> asportazioni) {
		this.asportazioni = asportazioni;
	}
	
	
	
	
	
}
