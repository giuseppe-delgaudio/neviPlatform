package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Asportazioni;
import model.CasoClinico;


public class AsportazioniDAO {
	
	public AsportazioniDAO() {
		
		super();
		
	}
	
	
	public boolean deleteAsportazione( String idAsportazione ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione staff");
		
		try {
		
		transaction.begin();
		Asportazioni asp = em.find( Asportazioni.class , Integer.parseInt(idAsportazione) );
		CasoClinico caso = asp.getcasoClinico();
        caso.getAsportazioni().remove(asp);
		em.merge(caso);
		em.remove(asp);
		
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
	
	
	public String insertAsportazione( String idCasoClinico , Asportazioni asportazione   ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione asportazione");
		
		try {
		
		transaction.begin();

		asportazione.setNum_casoClinico(em.find(CasoClinico.class, Integer.parseInt(idCasoClinico)));

		em.persist(asportazione);
		
		transaction.commit();
		em.close();
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return null;
		}
		
		return asportazione.getIdasportazioni().toString(); 
		
	}

}
