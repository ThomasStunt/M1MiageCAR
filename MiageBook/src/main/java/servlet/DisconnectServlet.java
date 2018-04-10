package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    HttpSession userSession;
	
	@Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
        throws ServletException, IOException {
		
		userSession = reqest.getSession(true);
		userSession.invalidate();
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
