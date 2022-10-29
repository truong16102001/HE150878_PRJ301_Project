/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.AccountDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Attendance;
import model.Lecturer;
import model.Session;
import model.Student;

/**
 *
 * @author ThinkPro
 */
public class AttendanceCheckingServlet extends BaseRoleController {

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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        Session ses = new Session();
//        try {
//            ses.setId(Integer.parseInt(request.getParameter("sesid")));
//            int flag = Integer.parseInt(request.getParameter("flag"));
//            String[] stdids = request.getParameterValues("stdid");
//            if (stdids != null) {
//                for (String stdid : stdids) {
//                    Attendance a = new Attendance();
//                    Student s = new Student();
//                    s.setStdid(Integer.parseInt(stdid));
//                    a.setDescription(request.getParameter("description" + stdid) == null ? "" : request.getParameter("description" + stdid));
//                    a.setPresent(request.getParameter("present" + stdid).equals("present"));
//                    a.setStudent(s);
//                    ses.getAttendances().add(a);
//                    // response.getWriter().print(request.getParameter("description" + stdid));
//                }
//            }
//            SessionDBContext db = new SessionDBContext();
//            db.update(ses);
//            Session ses2 = db.get(ses.getId());
//            request.setAttribute("ses", ses2);
//            request.setAttribute("flag", flag);
//
//        } catch (NumberFormatException e) {
//        }
//
//        //  response.getWriter().print("abc " + ses.getId());
//        //response.sendRedirect("/myprojectprj301/lecturer/attview");
//        //   request.getRequestDispatcher("/lecturer/attview").forward(request, response);
//        request.getRequestDispatcher("../view/lecturer/attendanceChecking.jsp").forward(request, response);
//    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session ses = new Session();
        int flag = -1;
        try {
            ses.setId(Integer.parseInt(req.getParameter("sesid")));

            flag = Integer.parseInt(req.getParameter("flag"));
            String[] stdids = req.getParameterValues("stdid");
            if (stdids != null) {
                for (String stdid : stdids) {
                    Attendance a = new Attendance();
                    Student s = new Student();
                    s.setStdid(Integer.parseInt(stdid));
                    a.setDescription(req.getParameter("description" + stdid) == null ? "" : req.getParameter("description" + stdid));
                    a.setPresent(req.getParameter("present" + stdid).equals("present"));
                    a.setStudent(s);
                    ses.getAttendances().add(a);
                    // response.getWriter().print(request.getParameter("description" + stdid));
                }
            }
            SessionDBContext db = new SessionDBContext();
            db.update(ses);
            //Session ses2 = db.get(ses.getId());
            // req.setAttribute("ses", ses2);
            //req.setAttribute("flag", flag);
        } catch (NumberFormatException e) {
        }

        //req.getRequestDispatcher("../view/lecturer/attendanceChecking.jsp").forward(req, resp);
        resp.sendRedirect("/myprojectprj301/lecturer/att?id=" + ses.getId() + "&flag=" + flag);

    }

    @Override
    protected void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountDBContext db = new AccountDBContext();
            Account acc = (Account) req.getSession().getAttribute("acc");
            Lecturer lec = db.getAcReturnLecturer(acc.getUsername(), acc.getPassword());
            //   System.out.println("1: " + lec.getLid() + "-" + lec.getLname());

            if (lec != null) {
                String sesid_raw = req.getParameter("id");
                try {
                    int sesid = sesid_raw == null ? -1 : Integer.parseInt(sesid_raw);
                    // System.out.println("sss" + sesid);
                    if (sesid != -1) {
                        int flag = Integer.parseInt(req.getParameter("flag"));
                        SessionDBContext sesDB = new SessionDBContext();
                        Session ses = sesDB.get(sesid);
                        // System.out.println("2: " + ses.getLecturer().getLid() + "-" + ses.getLecturer().getLname());
                        if (ses.getLecturer().getLid() == lec.getLid()) {
                            req.setAttribute("ses", ses);
                            req.setAttribute("flag", flag);
                            req.getRequestDispatcher("../view/lecturer/attendanceChecking.jsp").forward(req, resp);
                        } else {
                            resp.getWriter().println("access denied!2");
                        }
                    } else {
                        //req.setAttribute("alert", "This session does not exist!");
                        resp.getWriter().println("This session does not exist!");
                    }

                } catch (NumberFormatException e) {
                    Logger.getLogger(AttendanceView.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                resp.getWriter().println("access denied!2");
            }
        } catch (NullPointerException e) {
            Logger.getLogger(AttendanceView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
