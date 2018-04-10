package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String name, pwd;
	HttpSession userSession;
	
	@Override
	protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		name = reqest.getParameter("inputPseudo");
		pwd = reqest.getParameter("inputPwd");
		
		System.out.println("name :"+name);
		System.out.println("pwd :"+pwd);
		
		try {
    		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora", "PERRIER", "martinien");
    		
	    	Statement stmt = con.createStatement();
	    	
	    	String sql = "SELECT name, pwd FROM CARINSCR";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	rs.next();
	    	
	    	if(name != rs.getString(1) || pwd != rs.getString(2))
	    		response.sendRedirect("connect.jsp");
	    	else {
	    		userSession.setAttribute("psd", name);
	    		response.sendRedirect("home.jsp");
	    	}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		response.sendRedirect("register.jsp");
    	} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
