package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StaffDAO;
import model.Staff;
import model.Staff.Permessi;
import model.Staff.Ruolo;
import utility.SHA1Converter;

/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/azioni/generaAccount")
public class GeneraAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// potremmo farlo passare dal filtro Admin per alcune fuzioni 
		
		String message; 
		String type; 
		
		StaffDAO dao = new StaffDAO(); 
		Integer id;
		String ruolo = request.getParameter("ruolo"); 
		Staff account = (Staff) request.getSession().getAttribute("user");
		
		if( account.getRuolo().equals(Ruolo.responsabile) ) {
			
			Staff staff = new Staff();
			staff.setNome_Cognome("");
			staff.setPassword(SHA1Converter.encryptThisString("default"));
			staff.setPermessi(Permessi.r);
			staff.setRuolo(Ruolo.analista);
			staff.setSupervisore(account);
			id = dao.insertStaff(staff);
			
			if( id != null ) {
				
				message="Sottoposto creato il suo ID è  ANA-<stong>"+id+"</strong> la password è 'default' da cambiare al primo accesso";
				type="success";
				
			}else {
				
				message="Sottoposto non creato si e' verificato un problema";
				type="danger"; 
				
			}
			
		}else if( account.getRuolo().equals(Ruolo.admin) ) {
			
					
					boolean check = true; 
					String role="ADM-";
					Staff staff = new Staff();
					staff.setNome_Cognome("");
					staff.setPassword(SHA1Converter.encryptThisString("default"));
					staff.setRuolo(Ruolo.valueOf(ruolo));
					String supervisoreString = request.getParameter("supervisore");
					if( staff.getRuolo().equals(Ruolo.analista) ) { 
																		Staff supervisore;
																		System.out.println("--------------- + 0  "+ supervisoreString);
																		
																						if( supervisoreString == null ) supervisoreString = String.valueOf(account.getId_account());
																						supervisore = dao.getStaff(supervisoreString);
																						
																						if( supervisore == null ) check=false; 
																						else if( ! supervisore.getRuolo().equals(Ruolo.analista) )staff.setSupervisore(supervisore);
																								else check=false;
																						staff.setPermessi(Permessi.r);
																						
																		
																			}
					else staff.setPermessi(Permessi.wr);
					
					
					
					if( check == false ) {
						
						message="Supervisore non trovato immettere un ID corretto";
						type="danger";
						
					}else {
						
						
						id = dao.insertStaff(staff);
						
						if( id != null ) {
							
							if( staff.getRuolo().equals(Ruolo.responsabile) ) role="MED-";
							else if( staff.getRuolo().equals(Ruolo.analista) ) role="ANA-";
							
							
							message="Account "+ staff.getRuolo().toString() +" creato il suo ID è "+role+"<stong>"+id+"</strong> la password è 'default' da cambiare al primo accesso";
							type="success";
							
						}else {
							
							message="Account non creato si e' verificato un problema";
							type="danger"; 
							
						}
				
						
						
						
						
					}
					
		}else {
			
			message="Non hai l'autorizzazione per creare sottoposti";
			type="danger";
			
		}
		
		request.getSession().setAttribute( "user" , dao.getStaff(String.valueOf(account.getId_account())) );
		response.sendRedirect("/NeviPlatform/staff/gestioneStaff.jsp?message="+message+"&type="+type);
		
	}

	
}
