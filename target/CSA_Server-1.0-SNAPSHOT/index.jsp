<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="time" class="com.company.CSAServer.controller.TimeServlet" scope="request" />
<jsp:useBean id="data" class="com.company.CSAServer.model.DBFunc" scope="request" />

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="utf-8">
    <!-- Bootstrap: CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JQuery Google-CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    <title>Magala-Shop</title>
</head>
<body>

<div id="navbar"></div>
<script>
    $(function(){
        $("#navbar").load("./modules/nav_ext.html");
    });
</script>

<div class="main_wrapper">

    <br><br>
    <h1>Herzloch willkommen zum <i>Magala</i>-Shop</h1>
    <h2>Springseile zum Spottpreis</h2>
    <br><br>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username">
            <div id="emailHelp" class="form-text">Unser Shop ist nur registrierten Nutzern zugänglich!</div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Passwort</label>
            <input type="password" class="form-control" id="password">
        </div>
        <!-- <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1">
          <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div> -->
        <button type="submit" class="btn btn-primary">Einloggen</button>
        <button type="reset" class="btn btn-warning">Zurücksetzen</button>
    </form>

    <a href="./loggedin/">Login-Mockup</a>

</div>

<div id="footer"></div>
<script>
    $(function(){
        $("#footer").load("./modules/footer.html");
    });
</script>
</body>
</html>