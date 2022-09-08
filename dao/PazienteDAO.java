package dao;






import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.jpa.QueryHints;

import model.CasoClinico;
import model.Paziente;
import model.Staff;
import utility.SHA1Converter;

public class PazienteDAO {

	public PazienteDAO(){
		
		super();
		
	}
	
	
	public boolean updatePaziente( Paziente paziente ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione paziente");
		
		try {
		
		transaction.begin();
		
		em.merge(paziente);

		em.merge(paziente.getFenotipo());
		em.merge(paziente.getAbitudini());
		
		
		transaction.commit();
		
		em.close();
		
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return false;
			
		}
		
		
		return true;
		
		
	}
	
	public Set<CasoClinico> getCasiCliniciByIDpaz( String idPaz ){
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		Set<CasoClinico> casiClinici = new HashSet<CasoClinico>();
		 
		
		Paziente paziente = em.find( Paziente.class , Integer.parseInt(idPaz) );
		
		casiClinici = paziente.getCasiClinici();
		
		System.out.println(casiClinici.size());
		em.close();
		return casiClinici;
		
	}


	public Paziente insertPaziente( Paziente paziente ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione paziente");
		
		try {
		
		transaction.begin();
		
		em.persist(paziente);
		em.persist(paziente.getAbitudini());
		em.persist(paziente.getFenotipo());
		
		
		
		transaction.commit();
		em.close();
		
		
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			
			transaction.rollback();
			em.close();
			return null; 
		}
		
		return paziente; 
		
	}
	
	
	public Paziente getPazienteById( String id) {
	
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		Paziente paz = em.find( Paziente.class , Integer.parseInt(id) );

		em.close();
		
		return paz;

		
	}
	
	public Paziente getPazienteByCf( String codiceFiscale ) {
		

		String enc; 
		
		 
		enc = SHA1Converter.encryptThisString(codiceFiscale.toUpperCase());
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		try {
			
			TypedQuery<Paziente> query = em.createNamedQuery( "CF" , Paziente.class ).setParameter(1 , enc );
			
			List<Paziente> pazienti = query.getResultList();
			
			em.close();
			return pazienti.get(0);
			
			
			}catch( Exception ex ) {
				
				return null; 
				
			}
		
	}
	
	
	public Paziente getPazienteByNomeCognome( String nomeCognomeData ) {
		
		String enc; 
		enc = SHA1Converter.encryptThisString(nomeCognomeData.toUpperCase());
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		try {
			
		TypedQuery<Paziente> query = em.createNamedQuery( "nomeCognome" , Paziente.class ).setParameter(1 , enc );
		
		List<Paziente> pazienti = query.getResultList();
		
		em.close();
		return pazienti.get(0);
		
		
		}catch( Exception ex ) {
			em.close();
			return null; 
			
		}
		
		
	}
	
	public Boolean removePaziente( int id , Staff user ) {
			
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione paziente");
		
		try {
		
		transaction.begin();
		
		Paziente paziente = em.find(Paziente.class, id);
		user.getCasoClinico().removeAll(paziente.getCasiClinici());
		em.remove(paziente);
		
		
		transaction.commit();
		
		em.close();
		
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return false; 
		}
		
		return true; 
			
		}
	
	
	public List<Paziente> getAllPazienti() {
		
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		try {
			
		TypedQuery<Paziente> query = em.createNamedQuery( "getAllPazienti" , Paziente.class );
		List<Paziente> pazienti = query.getResultList();
		
		em.close();
		return pazienti;
		
		
		}catch( Exception ex ) {
			em.close();
			ex.printStackTrace();
			return new ArrayList<Paziente>(); 
			
		}
	
	
	}
	
	public Integer getNumOfVisite( int id ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		Integer n = em.createNamedQuery("getNumVisit").setParameter( 1 , id).getResultList().size();
		em.close();
		return n;
		
		
	}
	
	public List<Paziente> getPazientiByIdMed( String idMed) {
		
		int idMed_1 = Integer.parseInt(idMed);
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		try {
			
		TypedQuery<Paziente> query = em.createNamedQuery( "getByMed" , Paziente.class ).setParameter( 1 , idMed_1);
		query.setHint( QueryHints.HINT_PASS_DISTINCT_THROUGH , false);
		
		List<Paziente> pazienti = query.getResultList();
		
		em.close();
		return pazienti;
		
		}catch( Exception ex ) {
			ex.printStackTrace();
			em.close();
			return new ArrayList<Paziente>(); 
			
		}
	
	}
}
