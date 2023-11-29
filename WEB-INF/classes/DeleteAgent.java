import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class DeleteAgent extends HttpServlet 
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
    String LicenseNum = request.getParameter("LicenseNum");

    try 
	{
      if (LicenseNum.length() == 0) {
        out.println("Please: LicenseNum is required.");
        return; 
    }
    deleteAgent(LicenseNum);
	out.println("<html><head><title>LicenseNum deleted</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>LicenseNum deleted</H2></font>");
    out.println( "</center><br />" );
	
	out.println("</head><body>");
	out.println("<center><table border=\"1\">"); 
	out.println("<tr BGCOLOR=\"#cccccc\">");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">LicenseNum</td>");
    out.println("</tr>");
	
	out.println("</table></center>");
	out.println("<center>");	
    out.println(LicenseNum + " is now deleted from the Agents table.");
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
		String query = "delete  from agents where PersonId  = ?";
		pstmt = conn.prepareStatement(query);
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
  private void deleteAgent(String personId) throws SQLException 
 {
    pstmt.setString(1, personId);
    pstmt.executeUpdate();
 }
}

