/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author ThinkPro
 */
public class AttendanceDAO extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Attendance getAttFromCondition(int stdid, int sesid) {
        String sql = "select sesid, stdid, present, description, record_time\n"
                + "from Attandance where stdid = ?  and sesid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, stdid);
            ps.setInt(2, sesid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Attendance a = new Attendance();
                
                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));
                a.setSession(ses);
                Student st = new Student();
                st.setStdid(rs.getInt("stdid"));
                a.setStudent(st);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                a.setRecord_time(rs.getString("record_time"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Attendance filterAttendance(int sesid, int stdid) {
        try {
            String sql = "select a.sesid, a.stdid, a.present, a.description, a.record_time from "
                    + "Attandance a where sesid = ? and stdid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sesid);
            ps.setInt(2, stdid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Attendance a = new Attendance();

                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));

                Student s = new Student();
                s.setStdid(rs.getInt("stdid"));

                a.setSession(ses);
                a.setStudent(s);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                a.setRecord_time(rs.getString("record_time"));
                return a;
            }
        } catch (SQLException e) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Map<Integer, Double> getNOAbsent(int gid, int subid) {
        Map<Integer, Double> list = new HashMap<>();

        try {
            String sql = "select s.stdid, sum(case present when 1 then 1 else 0 end) as [NumberOfAbsent]\n"
                    + "from Session ses inner join [Group] g on ses.gid = g.gid\n"
                    + "							inner join Subject sub on sub.subid = g.subid\n"
                    + "						inner join Student_Group sg on sg.gid = g.gid\n"
                    + "						inner JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "                       LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "					   where g.gid = ? and sub.subid = ? \n"
                    + "					   group by s.stdid";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gid);
            ps.setInt(2, subid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("stdid"), Math.round((rs.getInt("NumberOfAbsent") * 100.0 / 30) * 10) / 10.0);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
