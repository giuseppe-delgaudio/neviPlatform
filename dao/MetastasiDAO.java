package dao;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Allegato;
import model.CasoClinico;
import model.Metastasi;

public class MetastasiDAO {
	
public MetastasiDAO() {
		
		super();
		
	}
	
	
public boolean deleteMetastasi( String idMetastasi   ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		
		EntityTransaction transaction = em.getTransaction();
		
		if ( transaction == null ) System.out.println("Errore creazione transazione metastasi");
		
		try {
		
		transaction.begin();
		
		Metastasi met = em.find( Metastasi.class , Integer.parseInt(idMetastasi) );
		CasoClinico caso = met.getCasoClinico();
		caso.getMetastasi().remove(met);
		em.merge(caso);
        em.remove(met); 
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


public String insertMetastasi( String idCasoClinico , Metastasi metastasi   ) {
	
	
	EntityManager em = LocalEntityManager.getEntityManger();
	
	EntityTransaction transaction = em.getTransaction();
	
	if ( transaction == null ) System.out.println("Errore creazione transazione metastasi");
	
	try {
	
	transaction.begin();

	metastasi.setCasoClinico(em.find(CasoClinico.class, Integer.parseInt(idCasoClinico)));

	em.persist(metastasi);
	
	transaction.commit();
	em.close();
	}catch( Exception ex ) {
		System.out.println("Rollback");
		ex.printStackTrace();
		transaction.rollback();
		em.close();
		return null;
	}
	
	return metastasi.getId_metastasi().toString(); 
	
}

}
