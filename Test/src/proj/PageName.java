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
 * Servlet implementation class PageName
 */
@WebServlet("/PageName")
public class PageName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setContentType("text/html");
		
		String name = request.getParameter("name");
		

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
			String query = "call findname(?)";
			
			ps= con.prepareStatement(query); 	
			
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if (!rs.next()){
			    request.setAttribute("heading", "No data found.");
			    request.getRequestDispatcher("findname.jsp").forward(request, response);
			} 
			else {
				request.setAttribute("sender", rs.getString(1));
				request.setAttribute("sender_phone", rs.getString(2));
				request.setAttribute("receiver", rs.getString(3));
				request.setAttribute("receiver_phone", rs.getString(4));
				request.setAttribute("tracking", rs.getString(5));
				request.setAttribute("origin", rs.getString(6));
				request.setAttribute("dest", rs.getString(7));
				request.setAttribute("add", rs.getString(8));
				request.setAttribute("parc", rs.getString(9));
				request.setAttribute("datedeli", rs.getString(10));
				request.getRequestDispatcher("findname.jsp").forward(request, response);
			}
			
		
			
		}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}


	}

}
