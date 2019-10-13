package proj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageAccount
 */
@WebServlet("/PageAccount")
public class PageAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
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
				request.setAttribute("first", rs.getString(3));
				request.setAttribute("last", rs.getString(4));
				request.setAttribute("dob", rs.getString(5));
				request.setAttribute("email", rs.getString(6));
				request.setAttribute("user", rs.getString(7));
				request.getRequestDispatcher("account.jsp").forward(request, response);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		
	}

}
