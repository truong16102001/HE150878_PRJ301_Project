
<%-- 
    Document   : reportAttendanceOfStudent
    Created on : Oct 14, 2022, 1:50:45 AM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="helper" class="utils.DateTimeHelper"/>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="../image/fpt-logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../CSS/report1.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <title>Report Attendance</title>
    </head>
    <body>
        <h2><span style="margin-right: 800px">Hello <b>${sessionScope.acc.displayname} </b> ,</span>
            &nbsp;
            <span>
                <a href="../logout" style="text-decoration: none">Logout?</a>
            </span> </h2>
        <h1>Report Attendance </h1>
        <b>Group:</b> ${requestScope.group.gname} <br>
        <b>Subject: </b> ${requestScope.subject.subname} <br>

        <div id="container">   


            <table class="table table-bordered table-striped">
                <thead >
                    <tr>
                        <th scope="col" >ROLL NUMBER</th>                        
                        <th scope="col">FULL NAME</th>
                        <th scope="col">ABSENT(%)</th>
                            <c:forEach var="i" begin="1" end="30">
                            <th  scope="col">                             
                                    <form  action="/myprojectprj301/lecturer/attview" method="post">
                                        <input type="hidden" name="id" value="${i}"/>
                                        <input type="hidden" name="flag" value="0"/>
                                        <button class="bt2" type="submit" >
                                            <span style="font-weight: bold">SLOT ${i}</span> 
                                        </button>
                                    </form>                              
                            </th>             
                        </c:forEach>
                    </tr>                  
                </thead>
                <tbody>
                    <c:if test="${(requestScope.sessions ne null) and (requestScope.sessions.size() > 0)}">  
                        <c:forEach items="${requestScope.stus}" var="stu" >
                            <tr>
                                <th scope="col" >
                                    <a href="#" style="text-decoration: none; color:black"> ${stu.stdid} </a> 
                                    <input type="hidden" name="stdid" value="${stu.stdid}"/>
                                </th>

                                <th scope="col">${stu.stdname} </th>

                                <th scope="col">
                                    <c:forEach var="entry" items="${requestScope.map}">
                                        <c:if test="${entry.key eq stu.stdid}">
                                            ${entry.value}%
                                        </c:if>
                                    </c:forEach>
                                </th>

                                <c:forEach var="i" begin="1" end="30"> 
                                    <th scope="col"> 
                                        <c:forEach items="${requestScope.sessions}" var="ses"> 
                                            <c:if test="${ses.index eq i}">
                                                <c:forEach items="${requestScope.atts}" var="att">
                                                    <c:if test="${(stu.stdid eq att.student.stdid) and (ses.id eq att.session.id)}">
                                                        <c:if test="${!att.present}">
                                                            <font color="Green"> <b>P</b></font>
                                                        </c:if>
                                                        <c:if test="${att.present}">
                                                            <font color="Red"> <b>A</b></font>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach> 
                                            </c:if>
                                        </c:forEach>
                                    </th>
                                </c:forEach>

                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>

            </table>

        </div>
    </body>
</html>
