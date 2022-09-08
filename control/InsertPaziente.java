package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PazienteDAO;
import dao.StaffDAO;
import model.Abitudini;
import model.Fenotipo;
import model.Fenotipo.Fototipo;
import model.Paziente;
import model.Staff;

import utility.SHA1Converter;

/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/azioni/insertPaziente")
public class InsertPaziente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPaziente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PazienteDAO dao = new PazienteDAO();
		
		Paziente paz = new Paziente(); 
		
		String message= null; 
		String type = null; 
		StaffDAO dao_1 = new StaffDAO();
		Staff us = (Staff) request.getSession().getAttribute("user");
	
		
		String modNome = request.getParameter("modNome");
		String modCognome = request.getParameter("modCognome");
		String modCF = request.getParameter("modCF");
		String modData = request.getParameter("modDataNascita");
		String modAbFotoprot = request.getParameter("modAbFotopr");
		String modAbLav = request.getParameter("modAbLav");
		String modAbAp = request.getParameter("modAbAp");
		String modfototipo = request.getParameter("modFototipo");
		String modCap = request.getParameter("modCap");
		String modOcc = request.getParameter("modOcc");
		String modCute = request.getParameter("modCute");
		
		
		
		paz.setCf_paziente(SHA1Converter.encryptThisString(modCF.toUpperCase()));
		
		paz.setNome_cognome(SHA1Converter.encryptThisString((modNome+modCognome+modData).toUpperCase()));
		
		System.out.println("----creato"+(modNome+modCognome+modData).toUpperCase());
		
		Abitudini abitudiniMod = new Abitudini();
		abitudiniMod.setAbitudini_fotoprotezione(modAbFotoprot);
		abitudiniMod.setAttivita_creativa_all_aperto(modAbAp);
		abitudiniMod.setAttivita_lavorativa_all_aperto(modAbLav);
		abitudiniMod.setPaziente(paz);
		
		paz.setAbitudini(abitudiniMod);
		
		Fenotipo fenotip = new Fenotipo();
		
		fenotip.setFototipo(Fototipo.valueOf(modfototipo));
		fenotip.setCapelli(modCap);
		fenotip.setCute(modCute);
		fenotip.setOcchi(modOcc);
		fenotip.setPaziente(paz);
		
		paz.setFenotipo(fenotip);
		
		Paziente pazienteNew = dao.insertPaziente(paz);
		String paginaRedirect = "nuovoPaziente.jsp?";
		if( pazienteNew != null )
		{
			
			paginaRedirect="SchedaPaziente.jsp?scelta=ID&id="+pazienteNew.getId();
			message="Paziente creato correttamente";
			type="success";
			
		}else {
			
			message="Paziente <strong>non</strong> creato";
			type="danger";
			
		} 
		
		

		
		
		request.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
		
		response.sendRedirect("/NeviPlatform/staff/"+paginaRedirect+"&message="+message+"&type="+type);
		
	}

	
}
