package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.FamiliaritaDAO;
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
@WebServlet("/staff/azioni/updatePaziente")
public class UpdatePaziente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePaziente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PazienteDAO dao = new PazienteDAO();
		String  action = request.getParameter("choice");
		String idPaziente = request.getParameter("idPaziente");
		Paziente paz = dao.getPazienteById(idPaziente);
		String message= null; 
		String type = null; 
		FamiliaritaDAO daoFamil = new FamiliaritaDAO();
		
		if( action.equals("normal") ) {
		
		String modNome = request.getParameter("modNome");
		String modCognome = request.getParameter("modCognome");
		String modCF = request.getParameter("modCF");
		
		String modAbFotoprot = request.getParameter("modAbFotopr");
		String modAbLav = request.getParameter("modAbLav");
		String modAbAp = request.getParameter("modAbAp");
		String modfototipo = request.getParameter("modFototipo");
		String modCap = request.getParameter("modCap");
		String modOcc = request.getParameter("modOcc");
		String modCute = request.getParameter("modCute");
		
		System.out.println(modNome+modCognome+modCF+modAbFotoprot+modAbAp+modfototipo+modCap+modOcc+modCute);
		
		
		
		
		
		if(  !modCF.equals("null") ) paz.setCf_paziente(SHA1Converter.encryptThisString(modCF));
		
		if(  !modNome.equals("null") && !modCognome.equals("null") ) paz.setNome_cognome
											(SHA1Converter.encryptThisString((modNome+modCognome).toUpperCase()));
		
		Abitudini abitudiniMod = paz.getAbitudini();
		abitudiniMod.setAbitudini_fotoprotezione(modAbFotoprot);
		abitudiniMod.setAttivita_creativa_all_aperto(modAbAp);
		abitudiniMod.setAttivita_lavorativa_all_aperto(modAbLav);
		abitudiniMod.setPaziente(paz);
		
		paz.setAbitudini(abitudiniMod);
		
		Fenotipo fenotip = paz.getFenotipo();
		
		fenotip.setFototipo(Fototipo.valueOf(modfototipo));
		fenotip.setCapelli(modCap);
		fenotip.setCute(modCute);
		fenotip.setOcchi(modOcc);
		fenotip.setPaziente(paz);
		
		paz.setFenotipo(fenotip);
		
		
		
		if( dao.updatePaziente(paz) )
		{
			
			message="Modifiche salvate correttamente";
			type="success";
			
		}else {
			
			message="Modifiche <strong>non</strong> salvate";
			type="danger";
			
		} 
		
		
		}else if( action.equals("removefamil") ) {
			
			Integer idFamil = Integer.parseInt(request.getParameter("idFamil"));
			
			if( daoFamil.removeFamiliarita(idFamil) )
			{
				
				message="Modifiche salvate correttamente";
				type="success";
				
			}else {
				
				message="Modifiche <strong>non</strong> salvate";
				type="danger";
				
			} 
			
			
			
		}
		
		
		StaffDAO dao_1 = new StaffDAO();
		Staff us = (Staff) request.getSession().getAttribute("user");
		
		request.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
		
		response.sendRedirect("/NeviPlatform/staff/SchedaPaziente.jsp?scelta=ID&id="+paz.getId()+"&message="+message+"&type="+type);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost( req, resp);
	}

}
