/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

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

/**
 *
 * @author ThinkPro
 */
public class scheduleSetvlet extends HttpServlet {

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
        int lid = Integer.parseInt(request.getParameter("lid"));
        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");

        java.sql.Date from = null;
        java.sql.Date to = null;

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

        request.setAttribute("from", from);
        request.setAttribute("to", to);

        request.setAttribute("dates", DateTimeHelper.getDateList(from, to));

        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();
        request.setAttribute("slots", slots);

        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.filter(lid, from, to);

        request.setAttribute("sessions", sessions);

        LecturerDBContext lecDB = new LecturerDBContext();
        Lecturer lecturer = lecDB.get(lid);
        request.setAttribute("lecturer", lecturer);

        request.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(request, response);

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
        LecturerDBContext ldb = new LecturerDBContext();
        ArrayList<Lecturer> lectures = ldb.list();
        request.setAttribute("lecturers", lectures);
        //  request.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(request, response);
        // processRequest(request, response);
        String lid_raw = request.getParameter("lid");
        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");
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

            request.setAttribute("from", from);
            request.setAttribute("to", to);

            request.setAttribute("dates", DateTimeHelper.getDateList(from, to));

            TimeSlotDBContext slotDB = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = slotDB.list();
            request.setAttribute("slots", slots);

            SessionDBContext sesDB = new SessionDBContext();
            ArrayList<Session> sessions = sesDB.filter(lid, from, to);

            request.setAttribute("sessions", sessions);

            LecturerDBContext lecDB = new LecturerDBContext();
            Lecturer lecturer = lecDB.get(lid);
            request.setAttribute("lecturer", lecturer);
             request.setAttribute("lid", lid);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        request.getRequestDispatcher("../view/lecturer/schedule.jsp").forward(request, response);
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
        // processRequest(request, response);
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
