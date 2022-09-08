package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="account_staff")
@NamedQuery( name="getAll" , query="SELECT account FROM Staff account"  )
public class Staff implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum Ruolo { responsabile , admin ,analista }
	public static enum Permessi { r , wr };
	
	@Id @GeneratedValue( strategy=GenerationType.IDENTITY ) @Column( name ="id_account" )
	private int id_account; 
	
	@Column( name ="nome_cognome" )
	private String nome_cognome;
	
	
	@Enumerated( EnumType.STRING )
	private Permessi permessi;
	
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	
	@Column( name ="password" )
	private String password; 
	
	@ManyToOne @JoinColumn( name="supervisore" ,
							foreignKey = @ForeignKey( name="account_staff_ibfk_1" ))
	private Staff supervisore;
	
	
	@OneToMany( mappedBy="supervisore" ,  fetch =FetchType.EAGER )
	private Set<Staff> sottoposti;
	
	@OneToMany( mappedBy ="id_staff_medico" , fetch =FetchType.EAGER )
	private Set<CasoClinico> casoClinico;
	
	
	
	public Staff() {
		super();
	}
	
	
	
	
	public Staff(int id_account, String nome_cognome, String cognome, Permessi permessi, Ruolo ruolo, String password,
			Staff supervisore, Set<CasoClinico> casoClinico) {
		super();
		this.id_account = id_account;
		this.nome_cognome = nome_cognome;
		this.permessi = permessi;
		this.ruolo = ruolo;
		this.password = password;
		this.supervisore = supervisore;
		this.casoClinico = casoClinico;
	}




	public Set<CasoClinico> getCasoClinico() {
		
		if( supervisore == null ) return casoClinico; 
		else return supervisore.getCasoClinico();
		
	}



	public void setCasoClinico(Set<CasoClinico> casoClinico) {
		this.casoClinico = casoClinico;
	}



	public int getId_account() {
		return id_account;
	}
	public void setId_account(int id_account) {
		this.id_account = id_account;
	}
	public String getNome_Cognome() {
		return nome_cognome;
	}
	public void setNome_Cognome(String nome_cognome) {
		this.nome_cognome = nome_cognome;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Staff getSupervisore() {
		return supervisore;
	}
	public void setSupervisore(Staff supervisore) {
		this.supervisore = supervisore;
	}




	public Permessi getPermessi() {
		return permessi;
	}




	public void setPermessi(Permessi permessi) {
		this.permessi = permessi;
	}




	public Ruolo getRuolo() {
		return ruolo;
	}




	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	} 
	
	
	
	public CasoClinico getCasoClinico( String id_caso ) {
		
		Iterator<CasoClinico> iter = this.getCasoClinico().iterator();
		CasoClinico caso = null;
		
		while( iter.hasNext() ) {
			
			CasoClinico tmp = iter.next();
			
			if( tmp.getId_caso_clinico() == Integer.parseInt(id_caso) ) 
			{
				
				caso = tmp;
				break;
			}
			
			
		}
		
		
		return caso;
			
		
	}
	
	



	public Set<Staff> getSottoposti() {
		return sottoposti;
	}




	public void setSottoposti(Set<Staff> sottoposti) {
		this.sottoposti = sottoposti;
	}
	
	
	

}
