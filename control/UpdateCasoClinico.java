package control;

import java.io.IOException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CasoClinicoDAO;
import dao.StaffDAO;
import model.CasoClinico;
import model.CasoClinico.Spessore_breslow;
import model.CasoClinico.Tipo_melanoma;
import model.Staff;

/**
 * Servlet implementation class updateCasoClinico
 */
@WebServlet("/staff/azioni/updateCasoClinico")
public class UpdateCasoClinico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCasoClinico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// pagina return per errore
		String page = request.getParameter("page");
		// identificativo in chiaro paziente
		String idPaz = request.getParameter("idPaz");
		String idCaso = request.getParameter("idCaso");
		
		CasoClinico caso = ((Staff) (request.getSession().getAttribute("user"))).getCasoClinico(idCaso);
		
		String errortype;
		String errorMessage;
		
		if( caso != null ) {
			
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			
			
			String modDataVisita = request.getParameter("dataVisita");
			
			String modUlcerazione = request.getParameter("ulcerazione");
			String modLesionPigmentata = request.getParameter("lesPig");
			String modTipo_melanoma = request.getParameter("tipo_melanoma");
			String modIndice_Mitotico = request.getParameter("indiceMitotico");
			String modSpessoreBreslow = request.getParameter("spessore_breslow");
			String modRegressione = request.getParameter("regressione");
			String modInvasione_perivascolare = request.getParameter("invasione_perivascolare");
			String modInvasione_perineurale = request.getParameter("invasione_perineurale");
			String modNevo_melanocitico_a = request.getParameter("nevo_melanocitico_associato");
			String modLinfociti_infiltranti = request.getParameter("linfociti_infiltranti");
			String modNum_linf_esaminati = request.getParameter("num_linf_esaminati");
			String modNum_nevi = request.getParameter("num_nevi");
			String modNum_linf_sentinella = request.getParameter("num_linf_sentinella");
			String modNum_lnf_meta = request.getParameter("num_lnf_meta");
			String modNum_linf_sentinella_piu = request.getParameter("num_linf_sentinella+");
			String modBcc_associato = request.getParameter("bcc_associato");
			String modAbcde = request.getParameter("abcde");
			String modSevenPoint = request.getParameter("sevenPoint");
			
			caso.setId_staff_medico(caso.getId_staff_medico());
			
			Tipo_melanoma melanoma; 
			Spessore_breslow breslow ;
			
			if( modTipo_melanoma.equals("")  ) melanoma = null;
			else melanoma = Tipo_melanoma.valueOf(modTipo_melanoma);
			
			if( modSpessoreBreslow.equals("") ) breslow = null;
			else breslow = Spessore_breslow.valueOf(modSpessoreBreslow);
			
			try {
				
				System.out.println(modDataVisita);
				caso.setData_caso(simpleDateFormat.parse(modDataVisita));
				caso.setUlcerazione(modUlcerazione);
				caso.setLesione_pigmentata(modLesionPigmentata);
				caso.setTipo_melanoma(melanoma);
				caso.setIndice_mitotico(modIndice_Mitotico);
				caso.setSpessore_di_breslow(breslow);
				caso.setRegressione(modRegressione);
				caso.setInvasione_perineurale(modInvasione_perineurale);
				caso.setInvasione_perivascolare(modInvasione_perivascolare);
				caso.setNevo_melanocitico_associato(modNevo_melanocitico_a);
				caso.setLinfociti_infiltranti(modLinfociti_infiltranti);
				Integer tmp;
				
				if(  modNum_linf_esaminati.equals("")  ) tmp= null;
				else tmp = Integer.parseInt(modNum_linf_esaminati);
				caso.setNumero_linfonodi_esaminati(tmp);
				 
				if(  modNum_nevi.equals("")  ) tmp= null;
				else tmp = Integer.parseInt(modNum_nevi);
				caso.setNumero_nevi(tmp);
				
				if( modNum_linf_sentinella.equals("") )  tmp= null;
				else tmp = Integer.parseInt(modNum_linf_sentinella);
 				caso.setNumero_linfonodi_sentinella(tmp);
 				
 				if( modNum_lnf_meta.equals("") )  tmp= null;
				else tmp = Integer.parseInt(modNum_lnf_meta);
				caso.setNumero_linfonodi_metastatici(tmp);
				
				if( modNum_linf_sentinella_piu.equals("") )  tmp= null;
				else tmp = Integer.parseInt(modNum_linf_sentinella_piu);
				caso.setNumero_linfonodi_sentinella_piu(tmp);
				caso.setBcc_associato(modBcc_associato);
				caso.setABCDE(modAbcde);
				caso.setSEVEN_POINT_CHECK_LIST(modSevenPoint);
				
				
				CasoClinicoDAO dao = new CasoClinicoDAO();
				
				if( ! dao.updateCasoClinico(caso)) throw new Exception();
				
				StaffDAO dao_1 = new StaffDAO();
				Staff us = (Staff) request.getSession().getAttribute("user");
				
				errortype="success";
				errorMessage="Valore modificato corettamente";
				
				request.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
				response.sendRedirect("/NeviPlatform/staff/"+page+"?id_casoClinico="+idCaso+"&paziente="+idPaz+"&type="+errortype+
						"&message="+errorMessage);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				errortype="danger";
				errorMessage="Errore generico modifica non apportata";
				
				response.sendRedirect("/NeviPlatform/staff/"+page+"?id_casoClinico="+idCaso+"&paziente="+idPaz+"&type="+errortype+
										"&message="+errorMessage);
				
			}
			
			
			
			
			
		}else {
			
			errortype="danger";
			errorMessage="Visita non trovata. Prova rieffettuare il <a href='login.html'>login</a>";
			
			response.sendRedirect("/NeviPlatform/staff/"+page+"?id_casoClinico="+idCaso+"&paziente="+idPaz+"&type="+errortype+
									"&message="+errorMessage);
			
		}
		
	}

}
