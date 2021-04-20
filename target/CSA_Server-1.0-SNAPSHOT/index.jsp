<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="time" class="com.example.CSA_Server.TimeServlet" scope="request" />
<jsp:useBean id="data" class="com.example.CSA_Server.DBFunc" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<p>Das ist ein Probetext</p>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<LI>Hallo du da</LI>
<LI><jsp:getProperty name="time" property="time"/></LI>
<LI>Du bist: <%request.getParameter("name");%></LI>

<!--<jsp:getProperty name="data" property="bestellung"/>-->
<!--<p><%request.getParameter("data");%></p>-->
</body>
</html>