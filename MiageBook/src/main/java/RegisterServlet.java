
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    String lName, fName, name, mail, pwd;
    
    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) 
        throws ServletException, IOException {

    	lName = reqest.getParameter("inputName");
    	fName = reqest.getParameter("inputFirstname");
    	name = reqest.getParameter("inputPseudo");
    	mail = reqest.getParameter("inputMail");
    	pwd = reqest.getParameter("inputPwd");
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora", "PERRIER", "martinien");
    		
	    	Statement stmt = con.createStatement();
	    	
	    	String sql = "INSERT into CARINSCR VALUES('"+lName+"', '"+fName+"', '"+name+"', '"+mail+"', '"+pwd+"')";
	    	stmt.executeUpdate(sql);
	    	
	    	RequestDispatcher rd = reqest.getRequestDispatcher("connect");
	    	rd.forward(reqest, response);
	    	this.destroy();
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