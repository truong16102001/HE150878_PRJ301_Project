<%-- 
    Document   : schedule
    Created on : Oct 13, 2022, 2:36:54 PM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="helper" class="utils.DateTimeHelper"/>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>      
        <link rel="icon" type="image/x-icon" href="../image/fpt-logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Of Lecturer</title>
        <link rel="stylesheet" href="../CSS/schedule.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <div id="container">

            <form id="form" action="schedule" method="get">

                <b>Lecturer:</b>  <select name="lid" >
                    <option value="${0}">  CHOOSE  </option>
                    <c:forEach items="${requestScope.lecturers}" var="ls">
                        <option ${(ls.lid == lid)? 'selected':''} value="${ls.lid}">${ls.lname} </option>
                    </c:forEach>
                </select> <br>
                <b>From:</b> <input type="date" name="from" value="${sessionScope.from}"/>               
                <b> To:</b> <input type="date" name="to" value="${sessionScope.to}"/>
                <input type="submit" value="View"/> 
            </form>

            <table class="table table-bordered table-striped">
                <thead style="background-color: #6b90da">
                    <tr>
                        <th scope="col" rowspan="2" style="text-align: center">Slot</th>
                            <c:forEach items="${requestScope.dates}" var="d">
                            <th scope="col" style="text-align: center"> ${helper.getDayNameofWeek(d)} </th>                                                                                         
                            </c:forEach> 
                    </tr>    
                    <tr>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <th scope="col" style="text-align: center">  ${helper.dateFormat(d,"dd/MM/yyyy")}  </th>                                                                                     
                            </c:forEach>   
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.slots}" var="slot">
                        <tr style="text-align: center">
                            <th style="text-align: center">${slot.tid}</th>
                                <c:forEach items="${requestScope.dates}" var="d">
                                <th scope="col">
                                    <c:forEach items="${requestScope.sessions}" var="ses">
                                        <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.timeslot.tid eq slot.tid)}">
                                            <a  href="att?id=${ses.id}">${ses.group.gname} <br>Subject: ${ses.group.subject.subname} at ${ses.room.rname}</a> <br/>
                                            <c:if test="${ses.attendated}">
                                                (<font  color="Green" style="text-align: center">Attended</font>)
                                            </c:if>
                                            <c:if test="${!ses.attendated}">
                                                (<font  color="Red" style="text-align: center">Not yet</font>)
                                            </c:if>

                                            <a class="label label-primary" 
                                               href="https://fu.edunext.vn"> -EduNext </a><br>
                                            <span class="label label-success">${slot.description}</span></p></th>
                                        </c:if>
                                    </c:forEach>
                                </th>
                            </c:forEach>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

        <script>
            function change() {
                document.getElementById("form").submit();
            }
        </script>
    </body>
</html>
