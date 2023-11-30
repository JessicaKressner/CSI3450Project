<!-- TestBeanScope.jsp -->
<%@ page import = "chapter35.Count" %>
<jsp:useBean id = "count" scope = "application"
  class = "chapter35.Count">
</jsp:useBean>
<html>
  <head>
    <title>TestBeanScope</title>
  </head>
  <body>
   <center>
    <font color="red"><b><h3>Testing Bean Scope in JSP (Application)</h3></b></font><br>
    <% count.increaseCount(); %>
    You are visitor number <font color="blue"><b><%= count.getCount() %></b></font><br /><br />
    From host: <%= request.getRemoteHost() %>
    and session: <%= session.getId() %>
	
	</center>
  </body>
</html>




