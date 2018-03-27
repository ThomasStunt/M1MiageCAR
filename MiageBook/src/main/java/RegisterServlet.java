import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    String lName, fName, name, mail, pwd;
    HttpSession userSession;
    
    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) 
        throws ServletException, IOException {

    	userSession = reqest.getSession(true);
    	
    	lName = reqest.getParameter("inputName");
    	fName = reqest.getParameter("inputFirstname");
    	name = reqest.getParameter("inputPseudo");
    	mail = reqest.getParameter("inputMail");
    	pwd = reqest.getParameter("inputPwd");
    	
    	String pwd2 = reqest.getParameter("confPwd");
    	if(!pwd.contentEquals(pwd2)) {
    		response.sendRedirect("register.jsp");
    	}
    	
    	userSession.setAttribute("name", name);
    	userSession.setMaxInactiveInterval(0);
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