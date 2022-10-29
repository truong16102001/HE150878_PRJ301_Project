/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.AccountDBContext;
import dal.LecturerDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Lecturer;
import model.Session;
import model.TimeSlot;
import utils.DateTimeHelper;
import dal.SessionDBContext;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ThinkPro
 */
public class scheduleServlet extends BaseRoleController {

    @Override
    protected void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LecturerDBContext ldb = new LecturerDBContext();
        ArrayList<Lecturer> lectures = ldb.list();
        req.setAttribute("lecturers", lectures);

        String lid_raw = req.getParameter("lid");
        String from_raw = req.getParameter("from");
        String to_raw = req.getParameter("to");
        int lid;
        java.sql.Date from = null;
        java.sql.Date to = null;
        try {
            lid = (lid_raw == null) ? 0 : Integer.parseInt(lid_raw);

            if (from_raw == null || from_raw.length() == 0) { // khi user khong nhap date thi mac dinh lay date hien tai
                Date today = new Date();
                int todayOfWeek = DateTimeHelper.getDayofWeek(today);
                if (todayOfWeek == 1) {
                    todayOfWeek = 8;
                }
                Date u_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
                Date u_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
                from = DateTimeHelper.toDateSql(u_from);
                to = DateTimeHelper.toDateSql(u_to);
            } else {
                from = java.sql.Date.valueOf(from_raw);
                to = java.sql.Date.valueOf(to_raw);
            }
            HttpSession session = req.getSession();
            session.setAttribute("from", from);
            session.setAttribute("to", to);

            req.setAttribute("dates", DateTimeHelper.getDateList(from, to));

            TimeSlotDBContext slotDB = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = slotDB.list();
            req.setAttribute("slots", slots);

            SessionDBContext sesDB = new SessionDBContext();
            ArrayList<Session> sessions = sesDB.filter(lid, from, to);

            req.setAttribute("sessions", sessions);

            // LecturerDBContext lecDB = new LecturerDBContext();
            // Lecturer lecturer = lecDB.get(lid);
            //  req.setAttribute("lecturer", lecturer);
            req.setAttribute("lid", lid);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        req.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(req, resp);

    }

    @Override
    protected void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LecturerDBContext ldb = new LecturerDBContext();
        ArrayList<Lecturer> lectures = ldb.list();
        req.setAttribute("lecturers", lectures);
        HttpSession session = req.getSession();
        //String lid_raw = null, from_raw = "", to_raw = "";

        AccountDBContext db = new AccountDBContext();
        Account acc = (Account) req.getSession().getAttribute("acc");

//        Lecturer lec = db.getAcReturnLecturer(acc.getUsername(), acc.getPassword());
//        if (lec != null) {
//            //  response.getWriter().print("ok" + lec.getLid() + " " + lec.getLname());
//            lid_raw = String.valueOf(lec.getLid());
//        }
        String lid_raw = req.getParameter("lid");
        String from_raw = req.getParameter("from");
        String to_raw = req.getParameter("to");
        int lid;
        java.sql.Date from = null;
        java.sql.Date to = null;
        try {
            lid = (lid_raw == null) ? 0 : Integer.parseInt(lid_raw);

            if (from_raw == null || from_raw.length() == 0) { // khi user khong nhap date thi mac dinh lay date hien tai
                Date today = new Date();
                int todayOfWeek = DateTimeHelper.getDayofWeek(today);
                if (todayOfWeek == 1) {
                    todayOfWeek = 8;
                }
                Date u_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
                Date u_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
                from = DateTimeHelper.toDateSql(u_from);
                to = DateTimeHelper.toDateSql(u_to);
            } else {
                from = java.sql.Date.valueOf(from_raw);
                to = java.sql.Date.valueOf(to_raw);
            }

            session.setAttribute("from", from);
            session.setAttribute("to", to);

            req.setAttribute("dates", DateTimeHelper.getDateList(from, to));

            TimeSlotDBContext slotDB = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = slotDB.list();
            req.setAttribute("slots", slots);

            SessionDBContext sesDB = new SessionDBContext();
            ArrayList<Session> sessions = sesDB.filter(lid, from, to);

            req.setAttribute("sessions", sessions);

            LecturerDBContext lecDB = new LecturerDBContext();
            Lecturer lecturer = lecDB.get(lid);
            req.setAttribute("lecturer", lecturer);
            req.setAttribute("lid", lid);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        req.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(req, resp);

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
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
//        LecturerDBContext ldb = new LecturerDBContext();
//        ArrayList<Lecturer> lectures = ldb.list();
//        request.setAttribute("lecturers", lectures);
//        HttpSession session = request.getSession();
//        String lid_raw = null, from_raw = "", to_raw = "";
//
//        AccountDBContext db = new AccountDBContext();
//        Account acc = (Account) request.getSession().getAttribute("acc");
//
//        Lecturer lec = db.getAcReturnLecturer(acc.getUsername(), acc.getPassword());
//        if (lec != null) {
//            //  response.getWriter().print("ok" + lec.getLid() + " " + lec.getLname());
//            lid_raw = String.valueOf(lec.getLid());
//        }
//
//        int lid;
//        java.sql.Date from = null;
//        java.sql.Date to = null;
//        try {
//            lid = (lid_raw == null) ? 0 : Integer.parseInt(lid_raw);
//
//            if (from_raw == null || from_raw.length() == 0) { // khi user khong nhap date thi mac dinh lay date hien tai
//                Date today = new Date();
//                int todayOfWeek = DateTimeHelper.getDayofWeek(today);
//                if (todayOfWeek == 1) {
//                    todayOfWeek = 8;
//                }
//                Date u_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
//                Date u_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
//                from = DateTimeHelper.toDateSql(u_from);
//                to = DateTimeHelper.toDateSql(u_to);
//            } else {
//                from = java.sql.Date.valueOf(from_raw);
//                to = java.sql.Date.valueOf(to_raw);
//            }
//
//            session.setAttribute("from", from);
//            session.setAttribute("to", to);
//
//            request.setAttribute("dates", DateTimeHelper.getDateList(from, to));
//
//            TimeSlotDBContext slotDB = new TimeSlotDBContext();
//            ArrayList<TimeSlot> slots = slotDB.list();
//            request.setAttribute("slots", slots);
//
//            SessionDBContext sesDB = new SessionDBContext();
//            ArrayList<Session> sessions = sesDB.filter(lid, from, to);
//
//            request.setAttribute("sessions", sessions);
//
//            LecturerDBContext lecDB = new LecturerDBContext();
//            Lecturer lecturer = lecDB.get(lid);
//            request.setAttribute("lecturer", lecturer);
//            request.setAttribute("lid", lid);
//        } catch (NumberFormatException e) {
//            System.out.println(e);
//        }
//
//        request.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(request, response);
}

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
//        LecturerDBContext ldb = new LecturerDBContext();
//        ArrayList<Lecturer> lectures = ldb.list();
//        request.setAttribute("lecturers", lectures);
//
//        String lid_raw = request.getParameter("lid");
//        String from_raw = request.getParameter("from");
//        String to_raw = request.getParameter("to");
//        int lid;
//        java.sql.Date from = null;
//        java.sql.Date to = null;
//        try {
//            lid = (lid_raw == null) ? 0 : Integer.parseInt(lid_raw);
//
//            if (from_raw == null || from_raw.length() == 0) { // khi user khong nhap date thi mac dinh lay date hien tai
//                Date today = new Date();
//                int todayOfWeek = DateTimeHelper.getDayofWeek(today);
//                if (todayOfWeek == 1) {
//                    todayOfWeek = 8;
//                }
//                Date u_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
//                Date u_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
//                from = DateTimeHelper.toDateSql(u_from);
//                to = DateTimeHelper.toDateSql(u_to);
//            } else {
//                from = java.sql.Date.valueOf(from_raw);
//                to = java.sql.Date.valueOf(to_raw);
//            }
//            HttpSession session = request.getSession();
//            session.setAttribute("from", from);
//            session.setAttribute("to", to);
//
//            request.setAttribute("dates", DateTimeHelper.getDateList(from, to));
//
//            TimeSlotDBContext slotDB = new TimeSlotDBContext();
//            ArrayList<TimeSlot> slots = slotDB.list();
//            request.setAttribute("slots", slots);
//
//            SessionDBContext sesDB = new SessionDBContext();
//            ArrayList<Session> sessions = sesDB.filter(lid, from, to);
//
//            request.setAttribute("sessions", sessions);
//
//            LecturerDBContext lecDB = new LecturerDBContext();
//            Lecturer lecturer = lecDB.get(lid);
//            request.setAttribute("lecturer", lecturer);
//            request.setAttribute("lid", lid);
//        } catch (NumberFormatException e) {
//            System.out.println(e);
//        }
//        request.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(request, response);
//}
/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

