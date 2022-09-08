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
import model.Staff.Ruolo;

@WebFilter(filterName="AdminFilter")
public class AdminFilter implements Filter {
	
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	
	Staff user =  (Staff) ( ((HttpServletRequest) request).getSession().getAttribute("user") );
	
	
	
	
	if( user == null ) {
		
		((HttpServletRequest) request).getSession().invalidate();
		((HttpServletResponse)response).sendRedirect("../login.jsp");
		
		
	}else
	
		
	
		if( user.getRuolo().equals(Ruolo.admin)  ) chain.doFilter(request, response);
		
		else 
		{
			((HttpServletRequest) request).getSession().invalidate();
			((HttpServletResponse)response).sendRedirect("../login.jsp");
			
		}

	
	

}


}
