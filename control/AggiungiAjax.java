package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import dao.AllegatoDAO;
import dao.AsportazioniDAO;
import dao.DiagnosiDAO;
import dao.FamiliaritaDAO;
import dao.LesioniDAO;
import dao.MetastasiDAO;
import dao.StaffDAO;
import model.Allegato;
import model.Lesione;
import model.Staff;
import utility.SHA1Converter;
import model.Allegato.Tipo_File;
import model.Asportazioni;
import model.Diagnosi;
import model.Familiarita;
import model.Metastasi;;



/**
 * Permette aggiunta dati usando Ajax
 * @author GiuseppeDelGaudio
 */
@WebServlet( urlPatterns = "/staff/azioni/aggiungiAjax")
@MultipartConfig ( fileSizeThreshold = 1024 * 1024 * 2 , // Threshold  1mb
						
					maxFileSize = 1024*1024*50, //  50mb file max size 
					maxRequestSize = 1024*1024*50 ) // 50 mb max server
		
public class AggiungiAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
        /**
     * Gestione metodo HTTP <code>POST</code>
     * 
     * 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     * 
     */


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
		
            
		response.setContentType("application/json");
		
		String idCasoClinico = request.getParameter("idCasoClinico");
		String azione = request.getParameter("azione");
		JSONObject json = new JSONObject();
		String tipoMsg=null; 
		String msg=null;
		
		
		switch (azione) {
		
		
		case "allegati":{
			
			String idPaziente = request.getParameter("idPaziente");
			
			String browserType = request.getHeader("User-Agent");
			
			String pathServer = System.getProperty("catalina.base")+"\\AllegatiPazienti"+"\\"+idPaziente; 
			 
			
			System.out.println(pathServer);
			
			File fileSave = new File(pathServer);
			
			String nameFile="vuoto"; 

			

			
			try{
				
				if( ! fileSave.exists() ) { fileSave.mkdir(); }
				
				
			    
				
				if( request.getParts() != null && request.getParts().size() > 0 ) {
					
					AllegatoDAO dao = new AllegatoDAO();
					
					Part part = request.getPart("file"); 
								
					nameFile = this.extractFileName(part , browserType );

					
					String tipoFile = nameFile.substring(nameFile.lastIndexOf(".")+1).toUpperCase();
					
		            part.write(pathServer+File.separator+nameFile );
		           
		            Allegato all = new Allegato();
		            
		            
		            
		            String relPath = "AllegatiPazienti"+File.separator+idPaziente+File.separator+nameFile;
		            
		            all.setFile(pathServer+File.separator+nameFile);
		            
		            System.out.println(tipoFile);
		            
		            Tipo_File tFile = Tipo_File.GENERICO;
		            
		            if( tipoFile.equals("JPG") || tipoFile.equals("JPEG") ) tFile = Tipo_File.JPEG;
		            if( tipoFile.equals("PNG") ) tFile = Tipo_File.PNG;
		            if( tipoFile.equals("PDF") ) tFile = Tipo_File.PDF;
		            
		            all.setTipo_file(tFile);
		            all.setTag(Allegato.Tipo_Tag.valueOf(request.getParameter("tag")));
		            
		            String id = dao.insertAllegato(idCasoClinico, all );
		            if( id != null ) {
		            
		            	json.put("tag", all.getTag() );
		            	json.put("nomeFile",nameFile);
						json.put("tipo", tFile.toString());
						json.put("id", id);
						msg="Inserito correttamente";
						tipoMsg="success";
		            }else {
		            	
		            	File remove = new File(pathServer+File.separator+nameFile );
		            	remove.delete();
		            	msg="Si è verificato qualche problema riprova | PersistenceUnit";
						tipoMsg="danger";
		            }
		            
					
				}else {
					
					msg="Upload null";
					tipoMsg="danger";					
				}
				
				}catch(IOException e){
					File remove = new File(pathServer+File.separator+nameFile );
					remove.delete();
					msg="Si è verificato qualche problema riprova | Exception";
					tipoMsg="danger";
					e.printStackTrace();
					
				}
			
			
		}
			
			break;
			
		case "metastasi" : {
			
			String metastasi = request.getParameter("metastasi");
			String organiCoinvolti = request.getParameter("organiCoinvolti");
			
			Metastasi met = new Metastasi();
			
			met.setOrgani_coinvolti(organiCoinvolti);
			met.setMetastasi(metastasi);
			
			MetastasiDAO dao = new MetastasiDAO();
			
			String id = dao.insertMetastasi(idCasoClinico, met);
	        
			if( id != null ) {
	            
	            	json.put("metastasi", met.getMetastasi());
	            	json.put("organiCoinvolti", met.getOrgani_coinvolti());
					json.put("id", id);
					msg="Inserito correttamente";
					tipoMsg="success";
	            }else {
	            	
	            	
	            	msg="Si è verificato qualche problema riprova | PersistenceUnit";
					tipoMsg="danger";
	            }
			
			
		}
		
			break;
		case "lesioni" : {
			
			String diamLesione  = request.getParameter("diamLesione");
			String lesioneSat  = request.getParameter("lesioneSat");
			String sedeAnatomica  = request.getParameter("sedeAnatomica");
			String tipoLesione  = request.getParameter("tipoLesione");
			String margini  = request.getParameter("margini");
			String staging  = request.getParameter("staging");
			
			Lesione les = new Lesione();
			les.setDiametro_lesione(diamLesione);
			les.setLesioni_satellite(lesioneSat);
			les.setSede_anatomica(sedeAnatomica);
			les.setTipo_lesione(tipoLesione);
			les.setMargini(margini);
			les.setStaging(staging);
			
			LesioniDAO dao = new LesioniDAO();
			
			String id = dao.insertLesione(idCasoClinico, les);
	        
			if( id != null ) {
	            
	            	json.put("dimLes", les.getDiametro_lesione());
	            	json.put("lesSat", les.getLesioni_satellite());
	            	json.put("sedAna", les.getSede_anatomica());
	            	json.put("tipLes", les.getTipo_lesione());
	            	json.put("marg", les.getMargini());
	            	json.put("stag", les.getStaging());
	            	json.put("id", id);
					msg="Inserito correttamente";
					tipoMsg="success";
	            }else {
	            	
	            	
	            	msg="Si è verificato qualche problema riprova | PersistenceUnit";
					tipoMsg="danger";
	            }
			
			
		}
		
			break;
			
		case "asportazioni" : {
			
			String dataAspo = request.getParameter("dataAsportazioni");
			String spf = request.getParameter("spf");
			String pregrAsportazioni = request.getParameter("pregAsportazioni");
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Asportazioni asp = new Asportazioni();
			
			try {
			
			
			asp.setData_asportazione(simpleDateFormat.parse(dataAspo));
			asp.setPregresse_asportazioni(pregrAsportazioni);
			asp.setSpf(spf);
			AsportazioniDAO asp_dao = new AsportazioniDAO();
			
			String id = asp_dao.insertAsportazione(idCasoClinico, asp);
	        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
			
			if( id != null ) {
	            
	            	json.put("dataAspo", smp.format(asp.getData_asportazione())) ;
	            	json.put("spf", spf);
					json.put("prgAspo", pregrAsportazioni);
	            	json.put("id", id);
					msg="Inserito correttamente";
					tipoMsg="success";
	            }else {
	            	
	            	
	            	msg="Si è verificato qualche problema riprova | PersistenceUnit";
					tipoMsg="danger";
	            }
			
			}catch (Exception e) {
				
				msg="Si è verificato qualche problema riprova | Data Parse Exception";
				tipoMsg="danger";
				
			}
			
			
		}
		
			break;
			
		case "diagnosi" : {
			
			String dataDiagnosi = request.getParameter("dataDiagnosi");
			String diagnosi = request.getParameter("diagnosi");
			
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Diagnosi dia = new Diagnosi();
			
			try {
			
			
			dia.setDiagnosi(diagnosi);
			dia.setAnno_diagnosi(simpleDateFormat.parse(dataDiagnosi));
			DiagnosiDAO diaDao = new DiagnosiDAO();
			
			String id = diaDao.insertDiagnosi(idCasoClinico, dia);
	        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
			
			if( id != null ) {
	            
	            	json.put("dataDiagnosi", smp.format(dia.getAnno_diagnosi())) ;
	            	json.put("diagnosi", dia.getDiagnosi());
	            	json.put("id", id);
					msg="Inserito correttamente";
					tipoMsg="success";
	            }else {
	            	
	            	
	            	msg="Si è verificato qualche problema riprova | PersistenceUnit";
					tipoMsg="danger";
	            }
			
			}catch (Exception e) {
				
				msg="Si è verificato qualche problema riprova | Data Parse Exception";
				tipoMsg="danger";
				
			}
			
			
		}
		
			break;
		case "familiarita" : {
			
			String grado = request.getParameter("grado");
			String codFiscale_par = request.getParameter("cf_parente");
			String idPaziente = request.getParameter("idPaziente");
			
			FamiliaritaDAO dao = new FamiliaritaDAO();
			
			Familiarita famil = dao.insertFamiliarita( idPaziente , SHA1Converter.encryptThisString(codFiscale_par.toUpperCase()) , grado);
			
			if( famil != null ) {
				
				json.put("grado", grado );
				json.put("idFamil", famil.getId_account());
				json.put( "idParente" ,famil.getId_parente().getId());
				msg="Inserito correttamente";
				tipoMsg="success";
			}else {
				
				msg="Inserimento non riuscito , ricontrolla codice fiscale";
				tipoMsg="danger";
				
			}
			
			
		}
		
			break;
		default:
			break;
		}
		
		
		StaffDAO dao_1 = new StaffDAO();
		Staff us = (Staff) request.getSession().getAttribute("user");
		
		
		request.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
		json.put("tipoMsg" , tipoMsg );            
		json.put("messaggio", msg);
		response.getWriter().print(json.toString());

}
	
	
private String extractFileName(Part part , String broswer) {
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
                    
			if (s.trim().startsWith("filename")) {
                                if( broswer.contains("Edge") || broswer.contains("Trident") ){ return s.substring(s.lastIndexOf(File.separator)+1 , s.length()-1 ); }
                                else return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
	
