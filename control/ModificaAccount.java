package control;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StaffDAO;
import model.Staff;

import utility.SHA1Converter;

/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/modificaAccount")
public class ModificaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message; 
		String type; 
		
		StaffDAO dao = new StaffDAO(); 
	
		Staff account = (Staff) request.getSession().getAttribute("user");
		
		String password_1 = request.getParameter("password1");
		String password_2 = request.getParameter("password2");
		
		message="Modifica non effettuata riprova";
		type="danger"; 
		
		if( password_1 != null && password_2 != null ) { 
		
		if( password_1.equals(password_2)  && ! password_1.equals("default")  ) {
		
			
			account.setPassword(SHA1Converter.encryptThisString(password_1));
			
			if( dao.modifyStaff(account) ) {
				
				message="Modifica avvenuta con successo";
				type="success"; 
				
			
			}else {
				
				message="Le password non corrispondono riprova";
				type="danger"; 
				
			}
			
		}
		
		
		
		
	}

		response.sendRedirect("/NeviPlatform/staff/gestioneAccount.jsp?message="+message+"&type="+type);
	
	}
	
}

