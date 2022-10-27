/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.AttendanceDAO;
import dal.GroupDAO;
import dal.SessionDBContext;
import dal.StudentDAO;
import dal.SubjectDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;
import model.Subject;

/**
 *
 * @author ThinkPro
 */
public class ReportAttendanceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReportAttendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportAttendanceServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int gid, subid;
        try {

            gid = Integer.parseInt(request.getParameter("gid"));
            subid = Integer.parseInt(request.getParameter("subid"));

            SessionDBContext ssdb = new SessionDBContext();
            ArrayList<Session> sessions = ssdb.getReportAttendance(gid, subid);
            request.setAttribute("sessions", sessions);

//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).getId());
//            }
            GroupDAO gd = new GroupDAO();
            Group g = gd.get(gid);
            request.setAttribute("group", g);
            SubjectDAO sd = new SubjectDAO();
            Subject sub = sd.get(subid);
            request.setAttribute("subject", sub);
            StudentDAO std = new StudentDAO();
            ArrayList<Student> stus = std.filterStudent(gid);
            request.setAttribute("stus", stus);

            ArrayList<Attendance> atts = new ArrayList();
            AttendanceDAO atd = new AttendanceDAO();
            for (Student stu : stus) {
                for (Session ses : sessions) {
                    atts.add(atd.filterAttendance(ses.getId(), stu.getStdid()));
                }
            }
            request.setAttribute("atts", atts);

            Map<Integer, Double> map = atd.getNOAbsent(gid, subid);
//            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
//                response.getWriter().println(entry.getKey() + "--- " + entry.getValue());
//            }
            request.setAttribute("flag", "0");
            request.setAttribute("map", map);
        } catch (NumberFormatException e) {
        }
        request.getRequestDispatcher("../view/lecturer/reportAttendanceOfStudent.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
