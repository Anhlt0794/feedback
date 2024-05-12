package feedback.web.filter;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import feedback.web.bean.admin.LoginBean;
 
@WebFilter(urlPatterns = {"/admin/*"}, description = "Session Checker Filter")
public class AuthFilterAdmin implements Filter {
     
	 private FilterConfig config = null;
	 
	    public void init(FilterConfig config) throws ServletException {
	        this.config = config;
	        config.getServletContext().log("Initializing SessionCheckerFilter");
	    }
	 
	    public void doFilter(ServletRequest req, ServletResponse res,
	                         FilterChain chain)
	            throws ServletException, IOException {
	    	 req.setCharacterEncoding("UTF-8");
	    	    res.setContentType("text/html; charset=UTF-8"); 
	       boolean managelogin = false;
	    	    HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        LoginBean login = (LoginBean) request.getSession().getAttribute("loginBean");
	        if(login!=null){
	    	   managelogin = login.isMangerIslogin();
	       }
	        if (request.getRequestURI().endsWith("login.html") || managelogin) {
	        	chain.doFilter(req, res);
	        }else{
	        	  response.sendRedirect(request.getContextPath() + "/admin/login.html");
	        }
	        
	        
	    }
	 
	    public void destroy() {
	        config.getServletContext().log("Destroying SessionCheckerFilter");
	    }
}