<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="helper" class="utils.DateTimeHelper"/>
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

        <c:if test="${requestScope.ses eq null}"> null </c:if>

            <h1>Take Attendance</h1>          
            <b>Group:</b> ${requestScope.ses.group.gname} <br>
        <b>Subject: </b> ${requestScope.ses.group.subject.subname} <br>
        <b> Room: </b>  ${requestScope.ses.room.rname} <br/>
        <b>Date: </b> ${requestScope.ses.date} <br>
        <b>Time:</b> ${requestScope.ses.timeslot.description}<br/>
        <b>Attended: </b>    
        <c:if test="${requestScope.ses.attendated}">
            <span style="color:green"><b>YES</b></span>
        </c:if>
        <c:if test="${!requestScope.ses.attendated}">
            <span style="color:red"><b>NO</b></span>
        </c:if>


        <form action="att" method="POST">
            <input type="hidden" name="sesid" value="${param.id}"/>

            <table class="table table-bordered table-striped">
                <thead style="background-color: #6b90da">
                    <tr>
                        <th scope="col">No.</th>                      
                        <th scope="col">ROLL NUMBER</th>
                        <th scope="col">FULL NAME</th>
                        <th scope="col">ABSENT</th>
                        <th scope="col">PRESENT</th>
                        <th scope="col">NOTE</th>  
                        <th scope="col">Record Time</th>
                    </tr>
                </thead>
                <tbody> 

                    <c:if test="${(requestScope.ses.attendances eq null) or (requestScope.ses.attendances.size() == 0)}">
                        <tr>
                            <th scope="col" colspan="6">
                                <h5 style="color:chocolate">This session does not have students!</h5>
                            </th>
                        </tr>                  
                    </c:if>
                    <c:if test="${(requestScope.ses.attendances ne null) and (requestScope.ses.attendances.size() > 0)}">
                        <c:forEach items="${requestScope.ses.attendances}" var="att" varStatus="loop">
                            <tr>
                                <th scope="col">${loop.index + 1} </th>
                                <th scope="col" >
                                    ${att.student.stdid} <input type="hidden" name="stdid" value="${att.student.stdid}"/>
                                </th>
                                <th scope="col">${att.student.stdname} </th>
                                <th scope="col">
                                    <input type="radio" 
                                           <c:if test="${att.present}">  checked="checked"  </c:if> 
                                           name="present${att.student.stdid}" value="present" />  
                                </th>

                                <th scope="col">
                                    <input type="radio"
                                           <c:if test="${!att.present}"> checked="checked" </c:if>
                                           name="present${att.student.stdid}" value="absent" /> 
                                </th>
                                <th scope="col">
                                    <input type="text" name="description${att.student.stdid}" value="${att.description}" /> 
                                </th>
                                <th scope="col">
                                    ${att.record_time}
                                </th>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <div class="add"> <button type="submit"> <b>Save</b> </button> </div> <br>

        </form>
        <div  style="text-align: center">
            <button type="button" style="border-radius: 10px" > <a  href="schedule?lid=${requestScope.ses.lecturer.lid}&from=${sessionScope.from}&to=${sessionScope.to}" style="text-decoration: none; font-weight: bold"> BACK </a> </button>      
            <button type="button" style="border-radius: 10px" > <a  href="#" style="text-decoration: none; font-weight: bold"> VIEW ATTENDANCE REPORT </a> </button>  
        </div>
    </body>
</html>
