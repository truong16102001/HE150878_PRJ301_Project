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
        <link rel="stylesheet" href="../CSS/attendancecheck1.css">
        <title>Attendance Checking</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    </head>
    <body>
        <h3 style="background-color: #f5f5f5; border-radius: 4px">
            <span style="margin-right: 790px; background:  #5cb85c ;padding: 0 10px;border-radius: 8px;">Hello <b>${sessionScope.acc.displayname} </b> ,</span>
            &nbsp;
            <span class="lo">
                <a href="../logout" style="text-decoration: none; background:  #5cb85c; border-radius: 8px; padding: 0 10px;" > Logout?</a>
            </span> </h3>
        <c:if test="${requestScope.ses eq null}"> null </c:if>

            <h1 style="text-align: center">Take Attendance</h1>          
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


        <form action="/myprojectprj301/lecturer/att" method="POST">
            <input type="hidden" name="sesid" value="${param.id}"/>

            <table class="table table-bordered table-striped">
                <thead style="background-color: #6b90da">
                    <tr>
                        <th scope="col" style="text-align: center">No.</th>                      
                        <th scope="col" style="text-align: center" >ROLL NUMBER</th>
                        <th scope="col" style="text-align: center">FULL NAME</th>
                        <th scope="col"style="text-align: center">ABSENT</th>
                        <th scope="col"style="text-align: center">PRESENT</th>
                        <th scope="col"style="text-align: center">NOTE</th>  
                        <th scope="col"style="text-align: center">Record Time</th>
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
                                <th scope="col" style="text-align: center">${loop.index + 1} </th>
                                <th scope="col" style="text-align: center">
                                    ${att.student.stdid} <input type="hidden" name="stdid" value="${att.student.stdid}"/>
                                </th>
                                <td style="text-align: center">${att.student.stdname} </td>
                                <th scope="col" style="text-align: center">
                                    <input type="radio" 
                                           <c:if test="${att.present}">  checked="checked"  </c:if> 
                                           name="present${att.student.stdid}" value="present" />  
                                </th>

                                <th scope="col" style="text-align: center">
                                    <input type="radio"
                                           <c:if test="${!att.present}"> checked="checked" </c:if>
                                           name="present${att.student.stdid}" value="absent" /> 
                                </th>
                                <th scope="col" style="text-align: center">
                                    <input type="text" name="description${att.student.stdid}" value="${att.description}" /> 
                                </th>
                                <td style="text-align: center">
                                    ${att.record_time}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <input type="hidden" name="flag" value="1" />
            <c:if test="${requestScope.flag eq 1}">
                <div class="add"> <button type="submit"> <b>SAVE </b><i class="fa fa-save"></i> </button> </div> <br>
            </c:if>

        </form>
        <c:if test="${(requestScope.flag eq 1) or (requestScope.flag eq 0)}">
            <div class="fun" style="text-align: center">
                <button class="bt1" onclick="history.back()" style="font-size:24px"> <b>GO BACK</b> <i class="fa fa-arrow-circle-left"></i></button>
                <br/> <br/>
                <c:if test="${(requestScope.flag eq 1)}">                                              
                    <form action="/myprojectprj301/lecturer/reportattendance" method="get">
                        <input type="hidden" name="gid" value="${requestScope.ses.group.gid}" />

                        <button  class="btt" type="submit" style="border-radius: 10px" > 
                            VIEW ATTENDANCE REPORT <i class="fa fa-arrow-circle-right"></i>                           
                        </button>     
                    </form>
                </c:if> 
            </div>       
        </c:if>
    </body>
</html>
