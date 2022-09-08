package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StaffDAO;
import model.Staff;


/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/azioni/resetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Staff account = (Staff) request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		String message ="Reset Completato la password è \"default\" ";
		String type = "success";
		
		if( id != null ) {
		
		
		StaffDAO dao = new StaffDAO(); 
		
		if( ! dao.resetPassword(id , account)  ) { message ="Reset non riuscuto"; type="danger"; }
		
		}else {
			
			message ="Reset non riuscuto"; type="danger";
			
		}
		
		response.sendRedirect("/NeviPlatform/staff/gestioneStaff.jsp?message="+message+"&type="+type);
		
	}

	
}
