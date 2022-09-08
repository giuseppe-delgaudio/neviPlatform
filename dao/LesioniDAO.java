package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Asportazioni;
import model.CasoClinico;
import model.Lesione;

public class LesioniDAO {
	
	public LesioniDAO() {
		
		super();
		
	}


	public boolean deleteLesione( String idLesione ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione staff");
		
		try {
		
		transaction.begin();
		
		Lesione les = em.find( Lesione.class , Integer.parseInt(idLesione) );
		CasoClinico caso = les.getCasoClinico();
		caso.getLesione().remove(les);
		em.merge(caso);
		em.remove(les);
		
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
	
	
public String insertLesione( String idCasoClinico , Lesione lesione   ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione lesione");
		
		try {
		
		transaction.begin();

		lesione.setCasoClinico(em.find(CasoClinico.class, Integer.parseInt(idCasoClinico)));

		em.persist(lesione);
		
		transaction.commit();
		em.close();
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return null;
		}
		
		return lesione.getId_lesioni().toString(); 
		
	}
	

}
