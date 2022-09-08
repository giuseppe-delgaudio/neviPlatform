package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllegatoDAO;
import dao.StaffDAO;
import model.Allegato;
import model.Staff;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Permette aggiunta dati usando Ajax
 * @author GiuseppeDelGaudio
 */
@WebServlet( urlPatterns = "/staff/azioni/getPrediction")

public class GetPrediction extends HttpServlet {
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		String caso_clinico = request.getParameter("id_casoClinico");
		String paziente = request.getParameter("paziente");
		String id = request.getParameter("id");
		
		System.out.println(id);
		
		AllegatoDAO dao = new AllegatoDAO();
		
		Allegato al = dao.getAllegato(id);
		
		String base = System.getProperty("catalina.base");

		String basePrediction = base+"\\wtpwebapps\\NeviPlatform\\WEB-INF\\classes\\prediction\\";
		String result = "-1";
		
		// -- Windows 
		processBuilder.command("cmd.exe", "/c", "Rscript.exe "+basePrediction+"convoluzionale.R "+al.getFile());
		processBuilder.directory(new File("D:\\Programmi\\R-3.6.1\\bin"));
		
		/*
		 * LINUX * 
		 * processBuilder.command("bash", "-c", "Rscript "+basePrediction+"convoluzionale.R "+al.getFile() "); richiama l'eseguibile per script R e come parametro passa la posizione sul server del file Convoluzionale.R e il path dell'immagine
		 * processBuilder.directory(new File("D:\\Programmi\\R-3.6.1\\bin")); apro la directory dove è stato installato R e dove è contenuto R script
		 * */
		
		
		try {	
			Process process = processBuilder.start();		
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line;
			if ((line = reader.readLine()) != null ){
				output.append(line);
			}
			int exitVal = process.waitFor();			
			if (exitVal == 0) {			
				result=output.toString();			
			} else {		
				result="-1";
				
			}

		} catch (IOException e) {
			result="-1";
		} catch (InterruptedException e) {
			result="-1";
		}
		
		switch (result) {
		
		case "1": result="nevi";
			
			break;
		case "0": result="melanoma";
		break;
		case "-1": result="errore nella predizione";	
		break;			
		default:
			break;
		} 
		
		al.setPredict(result);
		dao.modifyAllegato(al);
		
		StaffDAO dao_1 = new StaffDAO();
		Staff us = (Staff) request.getSession().getAttribute("user");
		
		request.getSession().setAttribute( "user" , dao_1.getStaff(String.valueOf(us.getId_account())) );
		response.sendRedirect("/NeviPlatform/staff/schedaVisita.jsp?id_casoClinico="+caso_clinico+"&paziente="+paziente+"#allegatiTable");
		
		
	}

}
	

