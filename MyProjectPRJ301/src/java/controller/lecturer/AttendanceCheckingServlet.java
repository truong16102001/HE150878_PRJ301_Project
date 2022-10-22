/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author ThinkPro
 */
public class AttendanceCheckingServlet extends HttpServlet {

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
            out.println("<title>Servlet AttendanceCheckingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceCheckingServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int sesid = Integer.parseInt(request.getParameter("id"));
            SessionDBContext sesDB = new SessionDBContext();
            Session ses = sesDB.get(sesid);
            request.setAttribute("ses", ses);
        } catch (NumberFormatException e) {
        }
        request.getRequestDispatcher("../view/lecturer/attendanceChecking.jsp").forward(request, response);

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
        Session ses = new Session();
        try {
            ses.setId(Integer.parseInt(request.getParameter("sesid")));
            String[] stdids = request.getParameterValues("stdid");
            if (stdids != null) {
                for (String stdid : stdids) {
                    Attendance a = new Attendance();
                    Student s = new Student();
                    s.setStdid(Integer.parseInt(stdid));
                    a.setDescription(request.getParameter("description" + stdid) == null ? "" : request.getParameter("description" + stdid));
                    a.setPresent(request.getParameter("present" + stdid).equals("present"));
                    a.setStudent(s);
                    ses.getAttendances().add(a);
                    // response.getWriter().print(request.getParameter("description" + stdid));
                }
            }
            SessionDBContext db = new SessionDBContext();
            db.update(ses);
        } catch (NumberFormatException e) {
        }

        //  response.getWriter().print("abc " + ses.getId());
        response.sendRedirect("att?id=" + ses.getId());

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
