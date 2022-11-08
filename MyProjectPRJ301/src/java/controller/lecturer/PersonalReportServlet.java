/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.AttendanceDAO;
import dal.GroupDAO;
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
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;
import model.Subject;

/**
 *
 * @author ThinkPro
 */
public class PersonalReportServlet extends HttpServlet {

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
            out.println("<title>Servlet PersonalReportServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonalReportServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        try {
            int stdid = Integer.parseInt(request.getParameter("stdid"));
            int gid = Integer.parseInt(request.getParameter("gid"));
            GroupDAO gd = new GroupDAO();
            Group g = gd.getALL(stdid, gid);

            request.setAttribute("g", g);
            AttendanceDAO ad = new AttendanceDAO();
            StudentDAO std = new StudentDAO();
            Student st = std.get(stdid);
            request.setAttribute("st", st);

            ArrayList<Attendance> atts = new ArrayList();
            for (int i = 0; i < g.getSess().size(); i++) {
                Session s = g.getSess().get(i);
                atts.add(ad.getAttFromCondition(stdid, s.getId()));
            }

            Map map = std.getNOAbsent(gid, stdid);
            request.setAttribute("map", map);
            request.setAttribute("atts", atts);
            request.getRequestDispatcher("../view/lecturer/personalReport.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        }
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
        processRequest(request, response);
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
