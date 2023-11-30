<!-- StoreStudent.jsp -->
   <%@ page import = "chapter35.Student" %>
   <jsp:useBean id = "studentId" class = "chapter35.Student"
     scope = "session"></jsp:useBean>
   <jsp:useBean id = "storeDataId" class = "chapter35.StoreData"
     scope = "application" ></jsp:useBean>
 
   <html>
     <body>
      <%
        storeDataId.storeStudent(studentId);
		out.println(studentId.getFirstName() + " " +
                studentId.getLastName() +
          " is now registered in the database");
        out.close(); // Close stream
      %>
    </body>
  </html>

