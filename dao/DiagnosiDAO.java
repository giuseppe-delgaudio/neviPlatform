package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Asportazioni;
import model.CasoClinico;
import model.Diagnosi;

public class DiagnosiDAO {
	
	public DiagnosiDAO() {
		
		super();
		
	}
	
public String insertDiagnosi( String idCasoClinico , Diagnosi diagnosi   ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione asportazione");
		
		try {
		
		transaction.begin();

		diagnosi.setNum_caso_clinico(em.find(CasoClinico.class, Integer.parseInt(idCasoClinico)));
		em.persist(diagnosi);
		
		transaction.commit();
		em.close();
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return null;
		}
		
		return diagnosi.getId_diagnosi().toString(); 
		
	}
	
	
public boolean deleteDiagnosi( String idDiagnosi ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione staff");
		
		try {
		
		transaction.begin();
		
		Diagnosi di = em.find( Diagnosi.class , Integer.parseInt(idDiagnosi) );
		
		CasoClinico caso = di.getNum_caso_clinico();
        caso.getDiagnosi().remove(di);
		em.merge(caso);
		em.remove(di);
		
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

}
