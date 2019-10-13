package proj;

import java.io.IOException;
import java.sql.Connection;
import java.io.PrintWriter;

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
 * Servlet implementation class PageRegister
 */
@WebServlet("/PageRegister")
public class PageRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageRegister() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String bday = request.getParameter("birthdate");
		String email = request.getParameter("emailid");
		String username = request.getParameter("us");
		String password = request.getParameter("pass");
		int count = 0;
		
			
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
		String temp = "E00";
		
		java.sql.PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		java.sql.Statement st = null;
		
		
		try {
			con=DriverManager.getConnection(url,user,pas);
			String q = "select max(index_no) from registration";
			st = con.createStatement();
			rs = st.executeQuery(q);
			
			while(rs.next())
			{
				count = rs.getInt(1);
				count++;
				System.out.println(count);
			
			}
			String fin =temp+count;
			System.out.println(fin);
			String query = "insert into registration values(?,?,?,?,?,?,?,?)";
			ps= con.prepareStatement(query);
			ps.setInt(1, count);
			ps.setString(2, fin);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			ps.setString(5, bday);
			ps.setString(6,email);
			ps.setString(7,username);
			ps.setString(8, password);
			
			
			int status = ps.executeUpdate();
			
			
			response.sendRedirect("loginadmin1.html");
		}
		catch(SQLException e)
		{
			System.out.println("Something is wrong");
			e.printStackTrace();
		}


	
		
		
	
	}

}
