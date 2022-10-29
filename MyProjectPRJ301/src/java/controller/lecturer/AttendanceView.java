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
import model.Lecturer;
import model.Session;

/**
 *
 * @author ThinkPro
 */
public class AttendanceView extends BaseRoleController {

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
            out.println("<title>Servlet AttendanceView</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceView at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
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
//        try {
//            int sesid = Integer.parseInt(request.getParameter("id"));
//            int flag = Integer.parseInt(request.getParameter("flag"));
//            SessionDBContext sesDB = new SessionDBContext();
//            Session ses = sesDB.get(sesid);
//            request.setAttribute("ses", ses);
//            request.setAttribute("flag", flag);
//        } catch (NumberFormatException e) {
//        }
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
//        try {
//            AccountDBContext db = new AccountDBContext();
//            Account acc = (Account) req.getSession().getAttribute("acc");
//            Lecturer lec = db.getAcReturnLecturer(acc.getUsername(), acc.getPassword());
//         //   System.out.println("1: " + lec.getLid() + "-" + lec.getLname());
//
//            if (lec != null) {
//                String sesid_raw = req.getParameter("id");
//                try {
//                    int sesid = sesid_raw == null ? -1 : Integer.parseInt(sesid_raw);
//                   // System.out.println("sss" + sesid);
//                    if (sesid != -1) {
//                        int flag = Integer.parseInt(req.getParameter("flag"));
//                        SessionDBContext sesDB = new SessionDBContext();
//                        Session ses = sesDB.get(sesid);
//                       // System.out.println("2: " + ses.getLecturer().getLid() + "-" + ses.getLecturer().getLname());
//                        if (ses.getLecturer().getLid() == lec.getLid()) {
//                            req.setAttribute("ses", ses);
//                            req.setAttribute("flag", flag);
//                            req.getRequestDispatcher("../view/lecturer/attendanceChecking.jsp").forward(req, resp);
//                        } else {
//                            resp.getWriter().println("access denied!2");
//                        }
//                    } else {
//                        //req.setAttribute("alert", "This session does not exist!");
//                        resp.getWriter().println("This session does not exist!");
//                    }
//
//                } catch (NumberFormatException e) {
//                    Logger.getLogger(AttendanceView.class.getName()).log(Level.SEVERE, null, e);
//                }
//            } else {
//                resp.getWriter().println("access denied!");
//            }
//        } catch (NullPointerException e) {
//            Logger.getLogger(AttendanceView.class.getName()).log(Level.SEVERE, null, e);
//        }
    }

    @Override
    protected void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
