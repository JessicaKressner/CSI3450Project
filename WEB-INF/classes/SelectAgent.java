import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class SelectAgent extends HttpServlet 			//class
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
		Statement state4 = null;
		ResultSet result = null;
		String query="";        
		Connection con=null; 
        String LicenseNum = request.getParameter("LicenseNum");		//user input                                                   
		try
		{			
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); //web server connects java code to sql
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "CSIPROJECT", "mohammed");
	       	System.out.println("Congratulations! You are connected successfully.");      
     	}
        catch(SQLException e)
		{	
			System.out.println("Error: "+e);	// catch exception errors from user input
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
		
		query = "Select * from agents where LicenseNum = '"+LicenseNum+"'";   //communicates with sql
		
		out.println("<html><head><title>  The Agent Attribute Report</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"white\"><H2>The Agent Attribute Report</H2></font>");
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		out.println("<center><table border=\"1\">"); 			// Chart printout of agent attributes
		out.println("<tr BGCOLOR=\"#cccccc\">");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">PersonId</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">LicenseNum</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">firstName</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">lastName</td>");
		out.println("</tr>");
		try 
		{ 
            while(result.next()) 			//while loop that loops through sql chart line after line
			{ 
		    		out.println("<tr>");
                    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
                    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
                    out.println("</tr>");              		
			} 
	    }
		catch (SQLException e) 
		{
			System.out.println("Resultset is not connected"); 
		}

		out.println("</table></CENTER>");		//centers table output
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
