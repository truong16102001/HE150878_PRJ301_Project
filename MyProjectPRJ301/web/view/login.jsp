<%-- 
    Document   : login
    Created on : Oct 27, 2022, 1:32:25 PM
    Author     : ThinkPro
--%>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="image/fpt-logo.png">
        <title>FAP Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">       
        <link href="CSS/login.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>     
        <img class="bgLogin" src="image/fpt.jpg" alt="">
        <div class="container">
            <div class="box"> 
                <div style="text-align: center"> <img alt="Avatar" class="avatar" src="image/coc2.png"> </div>
                <h2 style="text-align: center"><b>Login </b></h2>
                <form action="login" method="post">
                    <c:if test="${sessionScope.error ne null}">                      
                        <div class="alert alert-danger" style="font-size: 10px">
                            <strong>${sessionScope.error}</strong> 
                        </div>         
                        <c:remove var="error" scope="session" />
                    </c:if>

                    <div class="inp">
                        <span for="username" style="font-size: 20px"><b>Username:</b></span> 
                        <input type="text" placeholder="enter your email" name="username" required> 
                    </div>

                    <div class="inp"> 
                        <span for="password" style="font-size: 20px"><b>Password:</b> </span> 
                        <input type="password" placeholder="enter your password" name="password" required> 
                    </div>
                    <input type="hidden" name="error" value=""/>
                    <div class="inp">
                        <input type="submit" value="Login"></input>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>

