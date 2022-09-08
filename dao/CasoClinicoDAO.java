package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.CasoClinico;
import model.Paziente;
import model.Staff;

public class CasoClinicoDAO {

	public CasoClinicoDAO() {
		
		super();
	}
	
	public String insertCasoClinico( CasoClinico caso , String paz , int idMedico ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		
		if ( transaction == null ) System.out.println("Errore creazione transazione caso");
		
		try {
		
		transaction.begin();
		
		Paziente paziente = em.find(Paziente.class,  Integer.parseInt(paz) );
		caso.setId_staff_medico(em.find(Staff.class , idMedico));
		caso.setPaziente(paziente);
		em.persist(caso);
		System.out.println("inserisco caso");
		transaction.commit();
		
	
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			return null;
		}
				
		return String.valueOf(caso.getId_caso_clinico());	
		
	}

	public boolean updateCasoClinico( CasoClinico caso ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		
		if ( transaction == null ) System.out.println("Errore creazione transazione caso");
		
		try {
		
		transaction.begin();
		
		em.merge(caso);
		System.out.println("mergo caso");
		
		transaction.commit();
		
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			return false;
		}
		
		
		return true;
		
		
	}
	
	
}
