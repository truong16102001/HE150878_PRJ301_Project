<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="../image/fpt-logo.png">
        <link rel="stylesheet" href="../CSS/attendancecheck.css">
        <title>Attendance Checking</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    </head>
    <body>
        <h1>Take Attendance</h1>
        <form action="" method="GET">
            <table class="table table-bordered table-striped">
                <thead style="background-color: #6b90da">
                    <tr>
                        <th></th>
                        <th scope="col">GROUP</th>
                        <th scope="col">ROLL NUMBER</th>
                        <th scope="col">FULL NAME</th>
                        <th scope="col">ABSENT</th>
                        <th scope="col">PRESENT</th>
                        <th scope="col">NOTE</th>                     
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>1</th>
                        <th >SE1645</th>
                        <th >HE1</th>
                        <th >Student A</th>
                        <th ><input  type="radio" name="attend" value="true" /> Attend</th>
                        <th ><input type="radio" name="attend" value="false"/> Absent</th>
                        <th ><input type="text" name="note"></th>                     
                    </tr>
                    <tr>
                        <th>2</th>
                        <th >SE1645</th>
                        <th >HE2</th>
                        <th >Student B</th>
                        <th ><input  type="radio" name="attend" value="true" /> Attend</th>
                        <th ><input type="radio" name="attend" value="false"/> Absent</th>
                        <th ><input type="text" name="note"></th>                     
                    </tr>
                    <tr>
                        <th>3</th>
                        <th >SE1645</th>
                        <th >HE3</th>
                        <th >Student C</th>
                        <th ><input  type="radio" name="attend" value="true" /> Attend</th>
                        <th ><input type="radio" name="attend" value="false"/> Absent</th>
                        <th ><input type="text" name="note"></th>                     
                    </tr>
                    <tr>
                        <th>4</th>
                        <th >SE1645</th>
                        <th >HE4</th>
                        <th >Student D</th>
                        <th ><input  type="radio" name="attend" value="true" /> Attend</th>
                        <th ><input type="radio" name="attend" value="false"/> Absent</th>
                        <th ><input type="text" name="note"></th>                     
                    </tr>
                    <tr>
                        <th>5</th>
                        <th >SE1645</th>
                        <th >HE5</th>
                        <th >Student E</th>
                        <th ><input  type="radio" name="attend" value="true" /> Attend</th>
                        <th ><input type="radio" name="attend" value="false"/> Absent</th>
                        <th ><input type="text" name="note"></th>                     
                    </tr>
                    
                </tbody>
            </table>
            <div class="add"> <button type="submit"> Add </button> </div>
           
        </form>
    </body>
</html>
