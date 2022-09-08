package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Familiarita;
import model.Paziente;

public class FamiliaritaDAO {

	public FamiliaritaDAO() {
		
	};
	
	
	public boolean removeFamiliarita( int idFamil ) {
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			EntityTransaction transaction = em.getTransaction();
			
			if ( transaction == null ) System.out.println("Errore creazione transazione familiarita");
			
			try {
			
			transaction.begin();
			
			
			em.remove( em.find( Familiarita.class , idFamil ) );
			
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
	
	public Familiarita insertFamiliarita( String idPaziente , String cf_parenteENC , String genetica ) {
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione familiarita");
		
		try {
			
			
			transaction.begin();
			Paziente paziente = em.find(Paziente.class, Integer.parseInt(idPaziente) );
			
			Familiarita famil = new Familiarita();
			famil.setId_paziente( paziente );
			
			TypedQuery<Paziente> query = em.createNamedQuery( "CF" , Paziente.class ).setParameter(1 , cf_parenteENC );
			Paziente parente = query.getSingleResult();  
			
			famil.setId_parente( parente );
			famil.setGenetica(genetica);
			
			Familiarita famil_1 = new Familiarita();
			
			famil_1.setId_paziente(parente);
			famil_1.setId_parente( paziente  );
			famil_1.setGenetica(genetica);
			em.persist(famil);
			em.persist(famil_1);
			
			transaction.commit();
			em.close();
			return famil;
			
		}catch( Exception ex ) {
			System.out.println("Rollback");
			ex.printStackTrace();
			transaction.rollback();
			em.close();
			return null;
		}
		
		
		
	}
	
}
