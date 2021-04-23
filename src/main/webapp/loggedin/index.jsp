<%@ page import="com.company.CSAServer.model.Kunde" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.company.CSAServer.model.Artikel" %><%--
  Created by IntelliJ IDEA.
  User: giuseppe
  Date: 20.04.21
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="utf-8">
    <!-- Bootstrap: CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JQuery Google-CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Magala-Shop</title>
</head>
<body>

<div id="navbar"></div>
<script>
    $(function(){
        $("#navbar").load("${pageContext.request.contextPath}/modules/nav_int.html");
    });
</script>

<div class="main_wrapper">

    <%
    Kunde k = (Kunde) session.getAttribute("kunde");
    ArrayList<Artikel> artikel = (ArrayList<Artikel>) session.getAttribute("artikel");
    %>

    <br><br>
    <h1>Hallo <%=k.getVorname()%> <%=k.getName()%></h1>
    <h2>Hast du Lust auf Springseile?</h2>
    <br><br>


    <table class="table table-hover">
        <thead>
            <tr>
                <td>#</td>
                <td>Bild</td>
                <td>Bezeichnung</td>
                <td>Preis</td>
            </tr>
        </thead>
        <tbody>
            <%  for(int i = 0; i < artikel.size(); i++) { %>
                <tr>
                    <td><%= artikel.get(i).getaID() %></td>
                    <td><img src="<%= artikel.get(i).getBildURL() %>" alt="" style="max-width: 100px;"></td>
                    <td><%= artikel.get(i).getBezeichnung() %></td>
                    <td><%= artikel.get(i).getPreis() %> €</td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <br><br>

</div>

<div id="footer"></div>
<script>
    $(function(){
        $("#footer").load("${pageContext.request.contextPath}/modules/footer.html");
    });
</script>
</body>
</html>
