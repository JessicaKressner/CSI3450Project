<html>
  <head>
    <title>
      Factorial
    </title>
  </head>
  <body>
   <center>
   <!-- scriptlet  -->
  <% for (int i = 0; i <= 10; i++) { %>
  
                    <!-- expression   -->
       Factorial of <%= i %> is
	   
	     <!-- expression   -->
         <%= computeFactorial(i) %> <br />
  <!-- scriptlet  -->		 
  <% } %>

  <!-- declaration    -->
  <%! private long computeFactorial(int n) {    
        if (n == 0)
          return 1;
        else
          return n * computeFactorial(n - 1);
      }
  %>
  </center>
  </body>
</html>
