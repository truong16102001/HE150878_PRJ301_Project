<%-- 
    Document   : personalReport
    Created on : Oct 30, 2022, 1:26:55 AM
    Author     : ThinkPro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <b>Student: </b> ${requestScope.st.stdname} - RollNumber: ${requestScope.st.stdid} <br>
        <b>Subject: </b> ${requestScope.sub.subname} <br>
        <b>Semester: </b> ${requestScope.g.sem} <br>
        <b>Year: </b> ${requestScope.g.year} <br>
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
                    <c:forEach begin="1" end="30" var="i" >
                        <tr>
                            <th scope="col" style="text-align: center;">${i}</th>    
                                <c:forEach items="${requestScope.g.sess}" var="ses">
                                    <c:if test="${ses.id eq i }">
                                    <td style="text-align: center;">${ses.date}</td>
                                    <td style="text-align: center;">${ses.timeslot.description}</td>
                                    <td style="text-align: center;">${ses.room.rname}</td>
                                    <td style="text-align: center;">${ses.lecturer.lname}</td>
                                    <td style="text-align: center;">${requestScope.g.gname}</td>
                                    <c:forEach  items="${requestScope.atts}" var="a">
                                        <c:if test="${a.session.id eq ses.id}">
                                            <td style="text-align: center;"> 
                                                <c:if test="${a.present}">
                                                    <span style="color: green; font-weight: bold">Present</span>
                                                </c:if>
                                                <c:if test="${!a.present}">
                                                    <span style="color: red; font-weight: bold">Absent</span>
                                                </c:if>
                                            </td>
                                            <td style="text-align: center;">${a.description}</td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
</html>
