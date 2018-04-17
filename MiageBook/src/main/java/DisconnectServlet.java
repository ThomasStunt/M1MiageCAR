
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Cookie[] cookies;
	Cookie cook;
	
	@Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
        throws ServletException, IOException {
		
		cookies = reqest.getCookies();
		for(int i = 0; i<cookies.length; i++) {
			cook = cookies[i];
			if(cook.getName().contentEquals("psd")) {
				cook.setMaxAge(0);
			}
		}
		response.sendRedirect("localhost:8080/SimpleServlet/home.jsp");
		
		this.destroy();
	}
    
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }
    
    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }
}
