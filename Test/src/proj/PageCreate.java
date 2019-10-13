package proj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageCreate
 */
@WebServlet("/PageCreate")
public class PageCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		
		String sender = request.getParameter("sender");
		String sender_phone = request.getParameter("senpho");
		String receiver = request.getParameter("receive");
		String receiver_phone = request.getParameter("recpho");
		String origin = request.getParameter("origin");
		String dest = request.getParameter("dest");
		String add = request.getParameter("add");
		String pin = request.getParameter("pin");
		String parctype = request.getParameter("parctype");
		String weight = request.getParameter("weight");
		
		
	
		
		
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
			String query = "INSERT INTO orders (sender_name, sender_phone, receiver_name, receiver_phone, origin, destination, address, pincode, parcel_type) VALUES (?,?,?,?,?,?,?,?,?)";
			ps= con.prepareStatement(query); 	
			
			ps.setString(1, sender);
			ps.setString(2, sender_phone);
			ps.setString(3, receiver);
			ps.setString(4, receiver_phone);
			ps.setString(5, origin);
			ps.setString(6, dest);
			ps.setString(7, add);
			ps.setString(8, pin);
			ps.setString(9, parctype);
			
			
			int status = ps.executeUpdate();
			
			String q2 = "call adddate(?,?)";
			ps = con.prepareStatement(q2);
			ps.setString(1,sender);
			ps.setString(2, receiver);
			int stati = ps.executeUpdate();
			
			String query1 = "select * from orders where sender_name = ? and receiver_name = ?";
			ps = con.prepareStatement(query1);
			ps.setString(1, sender);
			ps.setString(2, receiver);
			rs = ps.executeQuery();
			String track_id = null;			
			
			while(rs.next()) {
					track_id = rs.getString(1);
					
					request.setAttribute("track", track_id);
					
				if(rs.getString(10).equals("Standard"))
				{	
					String q = "call updet(?)";
					ps= con.prepareStatement(q); 					
					ps.setString(1, track_id);
					int stat = ps.executeUpdate();
					request.getRequestDispatcher("payment.jsp").forward(request, response);
				}
				else if(rs.getString(10).equals("Ship Same"))
				{
					System.out.println("Success");
					String q5 = "call updetsame(?)";
					ps= con.prepareStatement(q5); 					
					ps.setString(1, track_id);
					int stat = ps.executeUpdate();
					String query2 = "select cost from cost where index_no = 8";
					ps = con.prepareStatement(query2);
					rs = ps.executeQuery();
					
					while(rs.next()) {
					request.setAttribute("price", rs.getString(1));
					request.getRequestDispatcher("paymentfinal.jsp").forward(request, response);
					}
				}
					else if(rs.getString(10).equals("Overnight"))
					{
						String q6 = "call updetnight(?)";
						ps= con.prepareStatement(q6); 					
						ps.setString(1, track_id);
						int stat = ps.executeUpdate();
						String query3 = "select cost from cost where index_no = 7";
						ps = con.prepareStatement(query3);
						rs = ps.executeQuery();
						
						while(rs.next()) {
						request.setAttribute("price", rs.getString(1));
						request.getRequestDispatcher("paymentfinal.jsp").forward(request, response);
					}
					}
			}
			
			
		}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}

	}

}
