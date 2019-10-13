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
 * Servlet implementation class PageUpdate
 */
@WebServlet("/PageUpdate")
public class PageUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String tracking = request.getParameter("trackid");
		String status = request.getParameter("status");
		String date = request.getParameter("date");
		

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
			String query = "UPDATE track_details SET status_order = ?, date_delivery = ? WHERE tracking_number = ?";
			
			ps= con.prepareStatement(query); 	
			
			
			ps.setString(1, status);
			ps.setString(2, date);
			ps.setString(3, tracking);
			
			int count = ps.executeUpdate();
			
			if(count ==0)
			{
				 request.setAttribute("heading", "No order found. Check tracking number.");
				 request.getRequestDispatcher("status.jsp").forward(request, response);
			}
			else
			{
				 request.setAttribute("heading", "Status changed successfully.");
				 request.getRequestDispatcher("status.jsp").forward(request, response);
			}		
		}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}


	}

}
