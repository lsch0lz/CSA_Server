<%--
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
    <link rel="stylesheet" href="./../style.css">
    <title>Magala-Shop</title>
</head>
<body>

<div id="navbar"></div>
<script>
    $(function(){
        $("#navbar").load("./../modules/nav_int.html");
    });
</script>

<div class="main_wrapper">

    <br><br>
    <h1>Herzloch willkommen zum <i>Magala</i>-Shop</h1>
    <h2>Springseile zum Spottpreis</h2>
    <br><br>



</div>

<div id="footer"></div>
<script>
    $(function(){
        $("#footer").load("./../modules/footer.html");
    });
</script>
</body>
</html>
