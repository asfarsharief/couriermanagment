package proj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageLogin
 */
@WebServlet("/PageLogin")
public class PageLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =  request.getParameter("username");
		String password = request.getParameter("password");
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Driver Found");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver not found");
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost/faa_db";
		String user = "root";
		String pas = "";
		
		java.sql.PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con=DriverManager.getConnection(url,user,pas);
			String query = "select * from registration where us=(?) and pass=(?)";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (!rs.next()){
			    response.sendRedirect("loginerror.html");
			} 
			else {
				if(rs.getString(2).equals("A001"))
				{
					response.sendRedirect("loginadmin.html");
				}
				else {
				response.sendRedirect("loginpage.html");
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	
	}
   
	

}
