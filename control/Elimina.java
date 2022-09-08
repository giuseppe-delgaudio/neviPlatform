package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.AllegatoDAO;
import dao.AsportazioniDAO;
import dao.DiagnosiDAO;
import dao.LesioniDAO;
import dao.MetastasiDAO;
import dao.PazienteDAO;
import dao.StaffDAO;
import model.Staff.Ruolo;
import model.Staff;


/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/azioni/elimina")
public class Elimina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Elimina() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Boolean check = false;
		String id;
		String paziente = req.getParameter("paziente");
		String idCaso = req.getParameter("id_casoClinico");
		String azione = req.getParameter("azione");
		Boolean sottoposti = false; 
		Boolean pazienti = false;
		StaffDAO dao_1 = new StaffDAO();
		Staff us = (Staff) req.getSession().getAttribute("user");
		System.out.println(azione + "   - -- - -- ");
		switch (azione) {
		
		
		case "metastasi":	{
			
			id = req.getParameter("idMetastasi");
			MetastasiDAO metDao = new MetastasiDAO();
			check = metDao.deleteMetastasi(id);

		}
			break;
		
		case "lesioni":	{
			
							id = req.getParameter("idLesione");
							LesioniDAO lesDao = new LesioniDAO();
							check = lesDao.deleteLesione(id);
			
		}
			
			break;

		case "asportazioni":	{
			
							id = req.getParameter("idAsportazione");
							AsportazioniDAO aspDao = new AsportazioniDAO();
							check = aspDao.deleteAsportazione(id);

		}

			break;
		
		case "allegato":	{
			
							id = req.getParameter("idAllegato");
							AllegatoDAO allDao = new AllegatoDAO();
							check = allDao.deleteAllegato(id);

		}

			break;
			
		case "diagnosi":	{
			
							id = req.getParameter("idDiagnosi");
							DiagnosiDAO diaDao = new DiagnosiDAO();
							check = diaDao.deleteDiagnosi(id);
				
		}

			break;
		
		case "sottoposti":	{
							
							id=req.getParameter("id");
							sottoposti= true;
							check = dao_1.removeStaff(id,us);
			

		}
			break;
		
		case "pazienti":	{
			
			PazienteDAO daoPaz = new PazienteDAO();
			pazienti = true;
			id=req.getParameter("id");
			if( us.getRuolo().equals(Ruolo.admin) ) {
				
				check = daoPaz.removePaziente(Integer.parseInt(id) , us );
			}


		}
			break;

			
		default:
			break;
		}
		
		
		
		
		
		req.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
		
		if( sottoposti == true ) {
			
				if( check == true ) resp.sendRedirect("/NeviPlatform/staff/gestioneStaff.jsp?type=success&message=Eliminato Con Successo");
				else resp.sendRedirect("/ProgettoNevi/staff/gestioneStaff.jsp?type=danger&message=Errore durante la eliminazione");
			
			
			
		}else if( pazienti == true ) {
			
			if( check == true ) resp.sendRedirect("/NeviPlatform/staff/gestionePazienti.jsp?type=success&message=Eliminato Con Successo");
			else resp.sendRedirect("/ProgettoNevi/staff/gestionePazienti.jsp?type=danger&message=Errore durante la eliminazione");
			
			
		}else {
			if( check == true ) resp.sendRedirect("/NeviPlatform/staff/schedaVisita.jsp?id_casoClinico="+idCaso+"&paziente="+paziente+"&type=success"+
														"&message=Eliminato Con Successo");
			else resp.sendRedirect("/NeviPlatform/staff/schedaVisita.jsp?id_casoClinico="+idCaso+"&paziente="+paziente+"&type=danger"+
					"&message=Errore durante la eliminazione");
	
	}}

}
