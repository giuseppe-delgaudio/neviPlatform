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

@WebFilter(filterName="StaffFilter")
public class StaffFilter implements Filter {
	
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	
	String role =  (String) ( ((HttpServletRequest) request).getSession().getAttribute("role") );
	
	
	
	if( role == null ) {
		
		((HttpServletRequest) request).getSession().invalidate();
		((HttpServletResponse)response).sendRedirect("../login.jsp");
		
		
	}else
	
		
	
		if(role.equals("staff")  ) chain.doFilter(request, response);
		
		else 
		{
			((HttpServletRequest) request).getSession().invalidate();
			((HttpServletResponse)response).sendRedirect("../login.jsp");
			
		}

	
	

}


}
