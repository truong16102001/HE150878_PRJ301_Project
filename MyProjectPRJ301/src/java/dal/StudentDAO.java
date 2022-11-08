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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author ThinkPro
 */
public class StudentDAO extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        String sql = "select stdid, stdname from Student where stdid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student st = new Student();
                st.setStdid(rs.getInt("stdid"));
                st.setStdname(rs.getString("stdname"));
                return st;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Student> filterStudent(int gid) {
        ArrayList<Student> list = new ArrayList();
        try {
            String sql = " select st.stdid, st.stdname from Student st inner join "
                    + "Student_Group sg on sg.stdid = st.stdid where sg.gid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStdid(rs.getInt("stdid"));
                st.setStdname(rs.getString("stdname"));
                list.add(st);
            }
        } catch (SQLException e) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public Map<Integer, Double> getNOAbsent(int gid, int stdid) {
        Map<Integer, Double> map = new HashMap<>();
        try {
            String sql = "select s.stdid, sum(case present when 1 then 1 else 0 end) as [NumberOfAbsent]\n"
                    + "from Session ses inner join [Group] g on ses.gid = g.gid\n"
                    + "							inner join Subject sub on sub.subid = g.subid\n"
                    + "						inner join Student_Group sg on sg.gid = g.gid\n"
                    + "						inner JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "                       LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "					   where g.gid = ? and s.stdid = ? \n"
                    + "					   group by s.stdid";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gid);
            ps.setInt(2, stdid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                map.put(rs.getInt("NumberOfAbsent"), Math.round((rs.getInt("NumberOfAbsent") * 100.0 / 30) * 10) / 10.0);
            }
            return map;
        } catch (SQLException e) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
