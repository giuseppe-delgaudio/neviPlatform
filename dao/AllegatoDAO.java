package dao;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;

import model.Allegato;
import model.CasoClinico;
import model.Paziente;


public class AllegatoDAO {
	
	public AllegatoDAO() {
		
		super();
		
	}
	
public Allegato getAllegato( String id ) {
	
	EntityManager em = LocalEntityManager.getEntityManger();
	Allegato al = em.find( Allegato.class , Integer.parseInt(id) );
	em.close();
	return al;
}


public void modifyAllegato( Allegato al ) {
	
	EntityManager em = LocalEntityManager.getEntityManger();
	
	EntityTransaction tx = em.getTransaction();
	
 
	
	tx.begin();
		 em.merge(al);
	tx.commit();
	em.close();
	
}

public boolean deleteAllegato( String idAllegato   ) {
		
		
		EntityManager em = LocalEntityManager.getEntityManger();
		EntityTransaction transaction = em.getTransaction();
		
		try {
		
		transaction.begin();
		
		Allegato al = em.find( Allegato.class , Integer.parseInt(idAllegato) );

        File oldFile = new File(al.getFile()); 
         
        if(oldFile.exists()) oldFile.delete(); 
		
        CasoClinico caso = al.getCasoClinico();
        caso.getAllegati().remove(al);
        em.merge(caso);
        em.remove(al);
        
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


public String insertAllegato( String idCasoClinico , Allegato allegato   ) {
	
	
	EntityManager em = LocalEntityManager.getEntityManger();
	
	EntityTransaction transaction = em.getTransaction();
	
	if ( transaction == null ) System.out.println("Errore creazione transazione allegato");
	
	try {
	
	transaction.begin();

	allegato.setCasoClinico(em.find(CasoClinico.class, Integer.parseInt(idCasoClinico)));

	em.persist(allegato);

	transaction.commit();
	em.close();
	
	
	}catch( Exception ex ) {
		System.out.println("Rollback");
		ex.printStackTrace();
		transaction.rollback();
		em.close();
		return null;
	}
	
	return allegato.getId_allegato().toString(); 
	
}

}
