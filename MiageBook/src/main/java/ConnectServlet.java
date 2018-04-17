
import java.io.IOException;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String name, pwd;
	Cookie cookie;
	
	@Override
	protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		name = reqest.getParameter("inputPseudo");
		pwd = reqest.getParameter("inputPwd");
		
		System.out.println("name: "+name);
		System.out.println("pwd: "+pwd);
		
		try {
    		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora", "PERRIER", "martinien");
    		
	    	Statement stmt = con.createStatement();
	    	
	    	String sql = "SELECT pwd FROM CARINSCR WHERE name = '"+name+"'";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	rs.next();
	    	
	    	System.out.println(rs.getString(1));
	    	
	    	if(!pwd.contentEquals(rs.getString(1))) {
	    		response.sendRedirect("connect.jsp");
	    		this.destroy();
	    	} else {
	    		System.out.println("juilà");
	    		cookie = new Cookie("psd", name);
	    		response.addCookie(cookie);
	    		response.sendRedirect("home.jsp");
	    		this.destroy();
	    	}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		response.sendRedirect("home.jsp");
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
