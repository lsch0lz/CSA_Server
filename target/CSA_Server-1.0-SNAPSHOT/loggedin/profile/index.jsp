<%@ page import="com.company.CSAServer.model.Kunde" %>
<%-- Created by IntelliJ IDEA.
  User: giuseppe
  Date: 22.04.21
  Time: 11:12
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
    %>

    <br><br>
    <h1>Profil von <%=k.getVorname()%> <%=k.getName()%></h1>
    <h2>Willst du deine Angaben ändern?</h2>
    <br><br>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="alert">
                ${successMessage}
        </div>
    </c:if>

    <form class="row g-3 needs-validation" action="${pageContext.request.contextPath}/loggedin/updateProfile" method="post">
        <div class="col-md-3">
            <label for="kID" class="form-label">Kunden-ID</label>
            <input type="text" class="form-control" id="kID" name="kID" value="<%=k.getkID()%>" readonly required>
        </div>
        <div class="col-md-9">
            <label for="username" class="form-label">Username</label>
            <div class="input-group">
                <span class="input-group-text" id="inputGroupPrepend">@</span>
                <input type="text" class="form-control" id="username" name="username" value="<%=k.getUsername()%>" aria-describedby="inputGroupPrepend" required>
            </div>
        </div>

        <div class="col-md-6">
            <label for="prename" class="form-label">Vorname</label>
            <input type="text" class="form-control" id="prename" name="prename" value="<%=k.getVorname()%>" required>
        </div>
        <div class="col-md-6">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%=k.getName()%>" required>
        </div>

        <div class="col-md-12">
            <label for="password" class="form-label">Passwort</label><br>
            <input type="text" class="form-control" id="password" name="password" value="<%=k.getPassword()%>" required>
        </div>

        <div class="col-md-6">
            <label for="street" class="form-label">Strassse & Hnr.</label>
            <input type="text" class="form-control" id="street" name="street" value="<%=k.getStrasseHnr()%>" required>
        </div>
        <div class="col-md-3">
            <label for="city" class="form-label">Ort</label>
            <input type="text" class="form-control" id="city" name="city" value="<%=k.getOrt()%>" required>
        </div>
        <div class="col-md-3">
            <label for="postalcode" class="form-label">Postleitzahl</label>
            <input type="text" class="form-control" id="postalcode" name="postalcode" value="<%=k.getPlz()%>" required>
        </div>

        <div class="col-md-12">
            <label for="state" class="form-label">Land</label>
            <input type="text" class="form-control" id="state" name="state" value="<%=k.getLand()%>" required>
        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit">Speichern</button>
            <button class="btn btn-warning" type="reset">Zurücksetzen</button>
        </div>
    </form>

</div>

<div id="footer"></div>
<script>
    $(function(){
        $("#footer").load("${pageContext.request.contextPath}/modules/footer.html");
    });
</script>
</body>
</html>