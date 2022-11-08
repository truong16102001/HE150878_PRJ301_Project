/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author ThinkPro
 */
public class GroupDAO extends DBContext<Group> {

    @Override
    public void insert(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        try {
            String sql = "SELECT gid,gname FROM [Group] WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Group g = new Group();
                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));

                return g;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Group getALL(int stdid, int subid) {
        String sql = "select s.stdid, s.stdname, g.gid, g.gname, g.subid, sub.subname, g.sem, g.year, g.lid as supervisorid, lec.lname as supervisorname, ses.sesid, ses.lid as lecid,  r.rid,r.rname, ses.date, t.tid, \n"
                + "t.description as tdescription, ses.[index], ses.attanded, a.present, a.description, a.record_time\n"
                + "from Student s inner join Student_Group sg on  s.stdid = sg.stdid \n"
                + "inner join [Group] g on g.gid = sg.gid\n"
                + "inner join Subject sub on sub.subid = g.subid\n"
                + "inner join Session ses on ses.gid = g.gid\n"
                + "inner join Lecturer lec on lec.lid = g.lid\n"
                + "inner join TimeSlot t on t.tid = ses.tid\n"
                + "inner join Room r on r.rid = ses.rid\n"
                + "inner join Attandance a on a.stdid = s.stdid and a.sesid = ses.sesid\n"
                + "where s.stdid = ? and sub.subid = ?"
                + " order by ses.sesid";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, stdid);
            ps.setInt(2, subid);
            ResultSet rs = ps.executeQuery();
            Group g = null;
            while (rs.next()) {
                if (g == null) {
                    g = new Group();
                    g.setGid(rs.getInt("gid"));
                    g.setGname(rs.getString("gname"));
                    Subject sub = new Subject();
                    sub.setSubid(rs.getInt("subid"));
                    sub.setSubname(rs.getString("subname"));
                    g.setSubject(sub);
                    Lecturer supervisor = new Lecturer();
                    supervisor.setLid(rs.getInt("supervisorid"));
                    supervisor.setLname(rs.getString("supervisorname"));
                    g.setSupervisor(supervisor);
                    g.setSem(rs.getString("sem"));
                    g.setYear(rs.getInt("year"));
                }
                Session ses = new Session();
                ses.setId(rs.getInt("sesid"));

                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                ses.setRoom(r);

                ses.setDate(rs.getDate("date"));
                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("tid"));
                t.setDescription(rs.getString("tdescription"));
                ses.setTimeslot(t);
                ses.setIndex(rs.getInt("index"));
                LecturerDBContext ldb = new LecturerDBContext();
                Lecturer l = ldb.get(rs.getInt("lecid"));              
                ses.setLecturer(l);
                ses.setAttendated(rs.getBoolean("attanded"));
                g.getSess().add(ses);
            }
            return g;
        } catch (SQLException e) {
        }
        return null;
    }

}
