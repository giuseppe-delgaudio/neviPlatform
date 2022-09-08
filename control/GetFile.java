package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllegatoDAO;
import model.Allegato;
import model.Allegato.Tipo_File;


/**
 * Servlet implementation class updatePaziente
 */
@WebServlet("/staff/getFile")
public class GetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
	    AllegatoDAO dao = new AllegatoDAO();   
		Allegato al =dao.getAllegato(id);
		File file = new File(al.getFile());
	      String filename = file.getAbsolutePath();
	      System.out.println(filename+" ---- ");
	      
	      String mime = null; 
	      
	      if( al.getTipo_file().equals(Tipo_File.PNG)  ) mime="images/png";
	      else if( al.getTipo_file().equals(Tipo_File.JPEG)) mime="image/jpeg"; 
	      else if( al.getTipo_file().equals(Tipo_File.PDF)  ) mime="application/pdf";
	      

	      System.out.println("----- " +mime+"");
	      
	      resp.setContentType(mime);
	      
	      resp.setContentLength((int)file.length());

	      FileInputStream in = new FileInputStream(file);
	      OutputStream out = resp.getOutputStream();

	      // Copy the contents of the file to the output stream
	       byte[] buf = new byte[1024];
	       int count = 0;
	       while ((count = in.read(buf)) >= 0) {
	         out.write(buf, 0, count);
	      }
	    out.close();
	    in.close();

	}
		
		
		}

	

