/*

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DeleteHome extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 
            

                String HOMEID = request.getParameter("HOMEID");
             
					
           



		try
		{			
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "CSIPROJECT", "mohammed");
	       	System.out.println("Congratulations! You are connected successfully.");      
      
			
     	}
        catch(SQLException e)
		{	
			System.out.println("Error: "+e);
			
			
			
		}
		catch(Exception e) 
		{
			System.err.println("Exception while loading  driver");		
		}
	    try 
		{
        	state4 = con.createStatement();
		} 
		catch (SQLException e) 	
		{
			System.err.println("SQLException while creating statement");			
		}
		
		response.setContentType("text/html");
		PrintWriter out = null ;
		try
		{
			out =  response.getWriter();
		}
		catch (IOException e) 
		{
  			e.printStackTrace();
		}
		
		query = "delete  from  homes where HOMEID  = '"+HOMEID+"'";											
      
		
		out.println("<html><head><title>  Record has deleted</title>");	 
		out.println("</head><body>");
		
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>The following record has been deleted from the database:</H2></font>");
		
        out.print( HOMEID );
		
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		
		try 
		{ 
   			result.close(); 
			state4.close(); 	
			con.close();
    		System.out.println("Connection is closed successfully.");
 	   	}
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}

  		out.println("</body></html>");
    } 
}
*/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class DeleteHome extends HttpServlet 
{
  private PreparedStatement pstmt;
  
  public void init() throws ServletException {
    initializeJdbc();
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  doGet(request, response);
  }
  
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String HomeID = request.getParameter("HomeID");

    try 
	{
      if (HomeID.length() == 0) {
        out.println("Please: Home ID is required.");
        return; 
    }
    deleteHome(HomeID);
	out.println("<html><head><title>HomeID Deleted</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>HomeID Deleted</H2></font>");
    out.println( "</center><br />" );
	
	out.println("</head><body>");
	out.println("<center><table border=\"1\">"); 
	out.println("<tr BGCOLOR=\"#cccccc\">");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">HomeID</td>");
    out.println("</tr>");
	
	out.println("</table></center>");
	out.println("<center>");	
    out.println(HomeID + " is now deleted from the Homes table.");
	out.println("</center>");
	out.println("</body></html>");
    }
    catch(Exception ex) 
	{
      out.println("\n Error: " + ex.getMessage());
    }
    finally 
	{
      out.close(); 
    }
 } 
  private void initializeJdbc() 
  {
    try 
	{
        String driver = "oracle.jdbc.driver.OracleDriver";  
        Class.forName(driver);
		// database URL is the unique identifier of the database on the Internet
		// thin is the oracle server
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "CSIPROJECT";
		String password = "mohammed";  
		Connection conn = DriverManager.getConnection(url,user, password);  
		String query = "delete  from  homes where HomeID  = ?";
		pstmt = conn.prepareStatement(query);
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
  private void deleteHome(String HomeID) throws SQLException 
 {
    pstmt.setString(1, HomeID);
    pstmt.executeUpdate();
 }
}
