package control;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PazienteDAO;
import dao.StaffDAO;
import model.Paziente;
import model.Staff;
import utility.SHA1Converter;

/**
 * Servlet implementation class testPazienti
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PazienteDAO dao_paz;
	private StaffDAO dao_staff;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encrypted;
		String user = request.getParameter("user"); 
		String password = request.getParameter("password"); 
		String url = "login.jsp"; 
		String errorMessage = null;
		HttpSession session = request.getSession(); 
		Boolean checkOk = false;
		//session.setMaxInactiveInterval(120);
		
		
			
			encrypted = SHA1Converter.encryptThisString(password);
			dao_staff = new StaffDAO();
			System.out.println(user);
			Staff staff = dao_staff.getStaff(user);
			
			
			if( staff == null ) errorMessage = "Non è stato trovato nessun account staff ricontrolla il campo utente";
			else if( staff.getPassword().equals(encrypted) ) {
				
				checkOk = true; 
				session.setAttribute("user", staff);
				session.setAttribute("role", "staff");
				request.removeAttribute("errorMessage");
				url="staff/Home.jsp";
			}else {
				
				
				url="login.jsp";
				errorMessage = "La password non è corretta , se è il tuo primo accesso insetisci la password di default";
				
			}
					
		if( checkOk == false ) { 
			
			// Qualcosa è andato storto Dispacher 
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispacher = getServletContext().getRequestDispatcher("/"+url);
			dispacher.forward(request, response);
			
			
		}else { // Ok Redirect
			
			if( staff.getPassword().equals(SHA1Converter.encryptThisString("default")) ) {
				
				url="staff/gestioneAccount.jsp";
				request.removeAttribute("user");
				response.sendRedirect(url);
				
			}else {
			
			request.removeAttribute("user");
			response.sendRedirect(url);
			
			}}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
