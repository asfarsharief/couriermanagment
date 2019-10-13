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
 * Servlet implementation class PageDelete
 */
@WebServlet("/PageDelete")
public class PageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		
		String tracking = request.getParameter("tracksid");
		

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
			String query = "delete from orders where tracking_no = ?";
			ps= con.prepareStatement(query); 
			ps.setString(1, tracking);
			int status = ps.executeUpdate();
			System.out.println(status);
			String  query1 = "delete from track_details where tracking_number = ?";
			ps= con.prepareStatement(query1); 
			ps.setString(1, tracking);
			int status1 = ps.executeUpdate();
			System.out.println(status1);
			if(status==0 && status1==0)
			{
				 request.setAttribute("heading", "Invalid Tracking Number. Try again.");
				  request.getRequestDispatcher("cancel.jsp").forward(request, response);
			}
			else {
				 request.setAttribute("heading", "Order Cancelled Successfully.");
				    request.getRequestDispatcher("cancel.jsp").forward(request, response);
			}
			
			}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}

		
	}

}
