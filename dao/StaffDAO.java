package dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Staff;
import model.Staff.Ruolo;
import utility.SHA1Converter;

public class StaffDAO {
	
		public StaffDAO(){
			
			super();
			
		}
				
		public boolean modifyStaff( Staff staff ) {
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			EntityTransaction transaction = em.getTransaction();
			
			if ( transaction == null ) System.out.println("Errore creazione transazione staff");
			
			try {
			
			transaction.begin();
			
			em.merge(staff);
			
			transaction.commit();
			em.close();
			return true;
			
			}catch( Exception ex ) {
				System.out.println("Rollback");
				ex.printStackTrace();
				transaction.rollback();
				em.close();
				return false;
				
			}
						
		}
		
		
		public boolean removeStaff( String id , Staff user ) {
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			EntityTransaction transaction = em.getTransaction();
			
			if ( transaction == null ) System.out.println("Errore creazione transazione staff");
			
			try {
			
			transaction.begin();
			
			Staff account = em.find(Staff.class , Integer.parseInt(id) ); 
			
			if( user.getRuolo().equals(Ruolo.admin) ) {
				
				em.remove(account);
				
			}else if(user.getRuolo().equals(Ruolo.responsabile)) {
				
				if( account.getRuolo().equals(Ruolo.analista) ) em.remove(account);
				else throw new Exception();
			}
			
			transaction.commit();
			account = em.find(Staff.class , Integer.parseInt(id) ); 
			em.close();
			if( account == null ) return true;
			else return false;
			
			}catch( Exception ex ) {
				System.out.println("Rollback");
				ex.printStackTrace();
				transaction.rollback();
				em.close();
				return false;
				
			}
			
			
			
		}

		public Integer insertStaff( Staff staff ) {
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			EntityTransaction transaction = em.getTransaction();
			
			if ( transaction == null ) System.out.println("Errore creazione transazione staff");
			
			try {
			
			transaction.begin();
			
			em.persist(staff);
			
			transaction.commit();
			em.close();
			}catch( Exception ex ) {
				System.out.println("Rollback");
				ex.printStackTrace();
				transaction.rollback();
				em.close();
				return null;
			}
			
			return staff.getId_account();
			
		}
		
		public Staff getStaff( String id ) {
			
			EntityManager em = LocalEntityManager.getEntityManger();
		
			int n = Integer.parseInt(id);
			System.out.println("sono nel dao il numero è |"+n+"|");
			Staff staff = em.find( Staff.class , n );
			em.close();
			return staff;
			
		}
		
		
		public List<Staff> getAllStaff() {
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			
			List<Staff> resultList = ( List<Staff> ) em.createNamedQuery("getAll").getResultList();
			
			em.close();
			
			return resultList;
			
		}
		
		
		public boolean resetPassword(String id ,  Staff account ) {
			
			boolean check = false;
			
			EntityManager em = LocalEntityManager.getEntityManger();
			
			EntityTransaction transaction = em.getTransaction();
			
			if ( transaction == null ) System.out.println("Errore creazione transazione staff");
			
			try {
			
			
				
			
			
			Staff staff = em.find(Staff.class, Integer.parseInt(id) );
			if( staff != null ) {
				
				transaction.begin();
				
				if( account.getRuolo().equals(Ruolo.admin) || ( account.getRuolo().equals(Ruolo.responsabile ) && staff.getRuolo().equals(Ruolo.analista)  ) ) {
				
					staff.setPassword(SHA1Converter.encryptThisString("default"));
				
					em.merge(staff);
				
					check = true;
					
					}else check= false;
				
					}else check = false;
					
					transaction.commit();
					
					em.close();
					return check ;
				
		
			
			
			}catch( Exception ex ) {
				System.out.println("Rollback");
				ex.printStackTrace();
				transaction.rollback();
				em.close();
				return false;
				
			}
			
			
			
		}
		
		
		
	

}
