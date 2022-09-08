package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Staff;
import model.Staff.Permessi;

/**
 * Servlet Filter implementation class AzioniFilter
 */
@WebFilter(filterName="StaffAzioniFilter" )
public class AzioniFilter implements Filter {

   
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Staff user =  (Staff) ( ((HttpServletRequest) request).getSession().getAttribute("user") );
		
		
		
		
		
		if( user.getPermessi().equals(Permessi.wr) ) chain.doFilter(request, response);
		
		else {
		
			
			((HttpServletResponse)response).sendRedirect("../login.jsp");
		}
		
	}

	

}
