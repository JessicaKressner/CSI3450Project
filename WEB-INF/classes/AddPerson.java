//
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddPerson extends HttpServlet 
{
  private PreparedStatement pstmt;
  public void init() throws ServletException {
    initializeJdbc();
  }
  public void doPost(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException  
 {
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String PERSONID = request.getParameter("PERSONID");
    String NAME = request.getParameter("NAME");
	String SSN = request.getParameter("SSN");
	String driversLicenseNr = request.getParameter("driversLicenseNr");
	String BirthDate = request.getParameter("BirthDate");
	String BirthPlace = request.getParameter("BirthPlace");
    

    try 
	{
      if (PERSONID.length() == 0 || NAME.length() == 0) {
        out.println("Please: PERSON ID and NAME are required");
        return; 
    }
    storePerson(PERSONID, NAME, SSN, driversLicenseNr, BirthDate, BirthPlace);
	out.println("<html><head><title>Person Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Person Report</H2></font>");
    out.println( "</center><br />" );
	/*
	out.println("</head><body>");
	out.println("<center><table border=\"1\">"); 
	out.println("<tr BGCOLOR=\"#cccccc\">");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">HOME ID</td>");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">ADDRESS</td>");
    out.println("</tr>");
	*/
	out.println("</table></center>");
		
    out.println(PERSONID + " " + NAME +
        " is now added to the Person table");
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
		pstmt = conn.prepareStatement("insert into person " +
        "(PERSONID, NAME, SSN, driversLicenseNr, BirthDate, BirthPlace) values (?, ?, ?, ?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
  private void storePerson(String PERSONID, String NAME, String SSN, String driversLicenseNr, String BirthDate, String BirthPlace) throws SQLException 
 {
    pstmt.setString(1, PERSONID);
    pstmt.setString(2,NAME);
    pstmt.setString(3, SSN);
    pstmt.setString(4, driversLicenseNr);
    pstmt.setString(5, BirthDate);
    pstmt.setString(6, BirthPlace);
    pstmt.executeUpdate();
 }
}
