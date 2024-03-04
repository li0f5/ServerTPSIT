<%--
  Created by IntelliJ IDEA.
  User: leonardo.fani.stud_t
  Date: 04/03/2024
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="/WEB-INF/tag1.tld" prefix="tq" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>accesso</title>

</head>
<body>
    <tq:query
            sql="SELECT nome,cognome FROM studente"
            dsn="localhost:8080/its_buzzi"
            user="root"
            password=""
            fields="nome,cognome"
    />
    <br><br>
  <form action="pagina%20principale.jsp" method="get">
      <label for="fname">First name:</label>
      <input type="text" id="fname" name="fname"><br><br>
      <label for="lname">Last name:</label>
      <input type="text" id="lname" name="lname"><br><br>
      <input type="submit" value="Submit">
  </form>


</body>
</html>
