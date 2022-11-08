
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
        <link rel="stylesheet" href="../CSS/report.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <title>Report Attendance</title>
    </head>
    <body>
        <h2><span style="margin-right: 800px">Hello <b>${sessionScope.acc.displayname} </b> ,</span>
            &nbsp;
            <span>
                <a href="../logout" style="text-decoration: none">Logout?</a>
            </span> </h2>
        <h1 style="text-align: center">Report Attendance </h1>
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

                                <form  action="/myprojectprj301/lecturer/att" method="get">    
                                    <c:forEach items="${requestScope.sessions}" var="ses">
                                        <c:if test="${ses.index eq i}">
                                            <input type="hidden" name="id" value="${ses.id}"/>        
                                        </c:if>
                                    </c:forEach>
                                                                 
                                    <input type="hidden" name="flag" value="0"/>
                                    <button class="bt2"type="submit" >
                                        <span style="font-weight: bold">SLOT ${i}</span> 
                                    </button>
                                </form>                              
                            </th>             
                        </c:forEach>
                    </tr>                  
                </thead>
                <tbody>

                    <c:forEach items="${requestScope.stus}" var="stu" >
                        <tr>
                            <th scope="row" >
                                <a href="/myprojectprj301/lecturer/personalreport?stdid=${stu.stdid}&subid=${requestScope.subject.subid}" 
                                   style="text-decoration: none; color:black"> ${stu.stdid} </a> 
                                <input type="hidden" name="stdid" value="${stu.stdid}"/>
                            </th>

                            <td>${stu.stdname} </td>

                            <td>
                                <c:forEach var="entry" items="${requestScope.map}">
                                    <c:if test="${entry.key eq stu.stdid}">
                                        ${entry.value}%
                                    </c:if>
                                </c:forEach>
                            </td>

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

                </tbody>
            </table>
            <c:if test="${(requestScope.flag eq 0)}">
                <div class="fun" style="text-align: center">
                    <button class="bt1" onclick="history.back()" style="font-size:24px"> <b>GO BACK</b> <i class="fa fa-arrow-circle-left"></i></button>
                </div>
            </c:if>
        </div>
    </body>
</html>
