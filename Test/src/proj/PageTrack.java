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
 * Servlet implementation class PageTrack
 */
@WebServlet("/PageTrack")
public class PageTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String tracking = request.getParameter("trackid");
		

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
			String query = "call tracking(?)";
			
			ps= con.prepareStatement(query); 	
			
			ps.setString(1, tracking);
			rs = ps.executeQuery();
			
			if (!rs.next()){
			    request.setAttribute("heading", "No data found.");
			    request.getRequestDispatcher("tracking.jsp").forward(request, response);
			} 
			else {
				request.setAttribute("id", rs.getString(2));
				request.setAttribute("datein", rs.getString(3));
				request.setAttribute("datedisp", rs.getString(4));
				request.setAttribute("status", rs.getString(5));
				request.setAttribute("datedeli", rs.getString(6));
				request.getRequestDispatcher("tracking.jsp").forward(request, response);
			}
			
		
			
		}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}

		
	}

}
