<%-- 
    Document   : personalReport
    Created on : Oct 30, 2022, 1:26:55 AM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="../../CSS/report.css">
        <link rel="icon" type="image/x-icon" href="../../image/fpt-logo.png">
        <link href="../../CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../../CSS/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Report</title>
    </head>
    <body>

        <h2><span style="margin-right: 800px">Hello <b>${sessionScope.acc.displayname} </b> ,</span>
            &nbsp;
            <span>
                <a href="../logout" style="text-decoration: none">Logout?</a>
            </span> </h2>
        <h1 style="text-align: center">Personal Report </h1>
        <b>Student: </b> ${requestScope.group.gname} <br>
        <b>Subject: </b> ${requestScope.subject.subname} <br>
        <b>Semester: </b> ${requestScope.subject.subname} <br>
        <b>Year: </b> ${requestScope.subject.subname} <br>
        <div id="container">   
            <table class="table table-bordered table-striped">
                <thead class="table table-primary">
                    <tr>
                        <th scope="col" style="text-align: center;">No.</th>                      
                        <th scope="col" style="text-align: center" >DATE</th>
                        <th scope="col" style="text-align: center">SLOT</th>
                        <th scope="col" style="text-align: center">ROOM</th>
                        <th scope="col" style="text-align: center">LECTURER</th>
                        <th scope="col" style="text-align: center">GROUP NAME</th>  
                        <th scope="col" style="text-align: center">ATTENDANCE STATUS</th>
                        <th scope="col" style="text-align: center">LECTURER'S COMMENT</th>
                    </tr>                  
                </thead>
                <tbody>
                    <tr>
                        <th scope="col" style="text-align: center;">1</th>                      
                        <td> <span class="label label-primary"> Wednesday 14/09/2022 </span> </td> 
                        <td> <span class="label label-danger"> 6_(16:10-17:40) </span> </td>
                        <th scope="col" style="text-align: center">DE-C203</th>
                        <th scope="col" style="text-align: center">E</th>
                        <th scope="col" style="text-align: center">F </th>  
                        <th scope="col" style="text-align: center">G </th>
                        <th scope="col" style="text-align: center">H</th>
                    </tr>
                    <tr>
                        <th scope="col" style="text-align: center;">2</th>                      
                        <th scope="col" style="text-align: center" >B</th>
                        <th scope="col" style="text-align: center">C</th>
                        <th scope="col" style="text-align: center">DE-C203</th>
                        <th scope="col" style="text-align: center">E</th>
                        <th scope="col" style="text-align: center">F </th>  
                        <th scope="col" style="text-align: center">G </th>
                        <th scope="col" style="text-align: center">H</th>
                    </tr>
                    <tr>
                        <th scope="col" style="text-align: center;">3</th>                      
                        <th scope="col" style="text-align: center" >B</th>
                        <th scope="col" style="text-align: center">C</th>
                        <th scope="col" style="text-align: center">D</th>
                        <th scope="col" style="text-align: center">E</th>
                        <th scope="col" style="text-align: center">F </th>  
                        <th scope="col" style="text-align: center">G </th>
                        <th scope="col" style="text-align: center">H</th>
                    </tr>
                    <tr>
                        <th scope="col" style="text-align: center;">4</th>                      
                        <th scope="col" style="text-align: center" >B</th>
                        <th scope="col" style="text-align: center">C</th>
                        <th scope="col" style="text-align: center">D</th>
                        <th scope="col" style="text-align: center">E</th>
                        <th scope="col" style="text-align: center">F </th>  
                        <th scope="col" style="text-align: center">G </th>
                        <th scope="col" style="text-align: center">H</th>
                    </tr>
                </tbody>
            </table>

        </div>
    </body>
</html>
