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
        <link rel="stylesheet" href="../CSS/report1.css">
        <link rel="icon" type="image/x-icon" href="../image/fpt-logo.png">
        <link href="../../CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../../CSS/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Report</title>
    </head>
    <body>

         <h3 style="background-color: #f5f5f5; border-radius: 4px">
            <span style="margin-right: 790px; background:  #5cb85c ;padding: 0 10px;border-radius: 8px;">Hello <b>${sessionScope.acc.displayname} </b> ,</span>
            &nbsp;
            <span class="lo">
                <a href="../logout" style="text-decoration: none; background:  #5cb85c; border-radius: 8px; padding: 0 10px;" > Logout?</a>
            </span> </h3>
        <h1 style="text-align: center">Personal Report </h1>
        <b>Student: </b> ${requestScope.st.stdname} - RollNumber: ${requestScope.st.stdid} <br>
        <b>Subject: </b> ${requestScope.g.subject.subname} <br>
        <b>Semester: </b> ${requestScope.g.sem} <br>
        <b>Year: </b> ${requestScope.g.year} <br>
        <b>Supervisor: </b> ${requestScope.g.supervisor.lname} <br>
        <div id="container"> 
            <table class="table table-bordered table-striped">
                <thead class="table table-primary sticky-top">
                    <tr class="head">
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
                                    <c:if test="${ses.index eq i }">
                                    <td style="text-align: center; "><span style="width: 70%; height: 30px; border-radius: 5px; background-color: #337ab7; color: white; padding: 5px ">${ses.date} </span></td>
                                    <td style="text-align: center; "><span style="width: 70%; height: 30px; border-radius: 5px; background-color: #d9534f;; color: white; padding:5px">${ses.timeslot.description}</span></td>
                                    <td style="text-align: center;">${ses.room.rname}</td>
                                    <td style="text-align: center;">${ses.lecturer.lname}</td>
                                    <td style="text-align: center;">${requestScope.g.gname}</td>
                                    <c:forEach  items="${requestScope.atts}" var="a">
                                        <c:if test="${a.session.id eq ses.id}">
                                            <td style="text-align: center;"> 
                                                <c:if test="${!a.present}">
                                                    <span style="color: green; font-weight: bold">Present</span>
                                                </c:if>
                                                <c:if test="${a.present}">
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
                <tfoot>
                    <tr><td colspan="8" style="text-align: center"><b>
                                <c:forEach var="entry" items="${requestScope.map}">
                                    <b>   ABSENT: ${entry.value}% ABSENT SO FAR (${entry.key} ABSENT ON 30 TOTAL). </b>
                                </c:forEach>
                        </td></tr>
                </tfoot>
            </table>
        </div>
    </body>
</html>
