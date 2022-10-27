/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author ThinkPro
 */
public class SessionDBContext extends dal.DBContext<Session> {

    public ArrayList<Session> filter(int lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	ses.sesid,ses.[date],ses.[index],ses.attanded\n"
                    + "	,l.lid,l.lname\n"
                    + "	,g.gid,g.gname\n"
                    + "	,sub.subid,sub.subname\n"
                    + "	,r.rid,r.rname\n"
                    + "	,t.tid,t.[description]\n"
                    + "FROM [Session] ses \n"
                    + "			INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "			INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "			INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "			INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "			INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "WHERE\n"
                    + "l.lid = ?\n"
                    + "AND ses.[date] >= ?\n"
                    + "AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Subject sub = new Subject();

                session.setId(rs.getInt("sesid"));
                session.setDate(rs.getDate("date"));
                session.setIndex(rs.getInt("index"));
                session.setAttendated(rs.getBoolean("attanded"));

                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                session.setLecturer(l);

                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                session.setGroup(g);

                sub.setSubid(rs.getInt("subid"));
                sub.setSubname(rs.getString("subname"));
                g.setSubject(sub);

                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                session.setRoom(r);

                t.setTid(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                session.setTimeslot(t);

                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    public ArrayList<Session> getReportAttendance(int gid, int subid) {
        ArrayList<Session> list = new ArrayList();
        try {
            String sql = "SELECT ses.sesid,ses.[index],ses.date,ses.attanded,g.gid,g.gname ,l.lid,l.lname,sub.subid,sub.subname  \n"
                    + "                   , r.rid, r.rname, t.tid,t.[description] tdescription   \n"
                    + "                 FROM [Session] ses	"
                    + "INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "                  INNER JOIN [Group] g ON g.gid = ses.gid \n"
                    + "                  INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "                  INNER JOIN TimeSlot t ON t.tid = ses.tid  \n"
                    + "                  INNER JOIN [Subject] sub ON sub.subid = g.subid  \n"
                    + "                 WHERE g.gid = ? and sub.subid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gid);
            ps.setInt(2, subid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Session ses;

                ses = new Session();
                ses.setId(rs.getInt("sesid"));
                ses.setDate(rs.getDate("date"));
                ses.setIndex(rs.getInt("index"));

                Lecturer l = new Lecturer();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                ses.setLecturer(l);

                Group g = new Group();
                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                ses.setGroup(g);

                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("tid"));
                t.setDescription(rs.getString("tdescription"));
                ses.setTimeslot(t);

                Subject sub = new Subject();
                sub.setSubid(rs.getInt("subid"));
                sub.setSubname(rs.getString("subname"));
                g.setSubject(sub);

                list.add(ses);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public Session get(int id) {
        try {
            String sql = "SELECT ses.sesid,ses.[index],ses.date,ses.attanded\n"
                    + "	,g.gid,g.gname\n"
                    + "	,r.rid,r.rname\n"
                    + "	,t.tid,t.[description] tdescription\n"
                    + "	,l.lid,l.lname\n"
                    + "	,sub.subid,sub.subname\n"
                    + "	,s.stdid,s.stdname\n"
                    + "	,ISNULL(a.present,1) present, ISNULL(a.[description],'') [description], a.record_time record_time\n"
                    + "		FROM [Session] ses\n"
                    + "		INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "		INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "		INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "		INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "		INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "		left JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "		left JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "		LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Session ses = null;
            while (rs.next()) {
                if (ses == null) {
                    ses = new Session();
                    ses.setId(rs.getInt("sesid"));
                    Room r = new Room();
                    r.setRid(rs.getInt("rid"));
                    r.setRname(rs.getString("rname"));
                    ses.setRoom(r);

                    TimeSlot t = new TimeSlot();
                    t.setTid(rs.getInt("tid"));
                    t.setDescription(rs.getString("tdescription"));
                    ses.setTimeslot(t);

                    Lecturer l = new Lecturer();
                    l.setLid(rs.getInt("lid"));
                    l.setLname(rs.getString("lname"));
                    ses.setLecturer(l);

                    Group g = new Group();
                    g.setGid(rs.getInt("gid"));
                    g.setGname(rs.getString("gname"));
                    ses.setGroup(g);

                    Subject sub = new Subject();
                    sub.setSubid(rs.getInt("subid"));
                    sub.setSubname(rs.getString("subname"));
                    g.setSubject(sub);

                    ses.setDate(rs.getDate("date"));
                    ses.setIndex(rs.getInt("index"));
                    ses.setAttendated(rs.getBoolean("attanded"));
                }
                //read student
                if (rs.getInt("stdid") != 0) {
                    Student s = new Student();
                    s.setStdid(rs.getInt("stdid"));
                    s.setStdname(rs.getString("stdname"));
                    //read attandance
                    Attendance a = new Attendance();
                    a.setStudent(s);
                    a.setSession(ses);
                    a.setPresent(rs.getBoolean("present"));
                    a.setDescription(rs.getString("description"));
                    a.setRecord_time(rs.getString("record_time"));
                    ses.getAttendances().add(a);
                }

            }
            return ses;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public ArrayList<Session> getSessionByCond(int gid, int subid) {
//        try {
//            String sql = " \n"
//                    + "	SELECT ses.sesid,ses.[index],ses.date,ses.attanded\n"
//                    + "                                       	,g.gid,g.gname\n"
//                    + "                                     	,r.rid,r.rname\n"
//                    + "                                     	,t.tid,t.[description] tdescription\n"
//                    + "                                    	,l.lid,l.lname\n"
//                    + "                                      	,sub.subid,sub.subname\n"
//                    + "                                      	,s.stdid,s.stdname\n"
//                    + "                                      	,ISNULL(a.present,1) present, ISNULL(a.[description],'') [description], isnull(a.record_time,'') record_time\n"
//                    + "                                      		FROM [Session] ses\n"
//                    + "                                        		INNER JOIN Room r ON r.rid = ses.rid\n"
//                    + "                                       		INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
//                    + "                                     		INNER JOIN Lecturer l ON l.lid = ses.lid\n"
//                    + "                                      		INNER JOIN [Group] g ON g.gid = ses.gid\n"
//                    + "                                      		INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
//                    + "                                       		left JOIN [Student_Group] sg ON sg.gid = g.gid\n"
//                    + "                                      		left JOIN [Student] s ON s.stdid = sg.stdid\n"
//                    + "                                       		LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
//                    + "                                   WHERE g.gid = ? and sub.subid = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, gid);
//            ps.setInt(2, subid);
//            ResultSet rs = ps.executeQuery();
//            ArrayList<Session> list = new ArrayList();
//            while (rs.next()) {
//                Session ses;
//
//                ses = new Session();
//                ses.setId(rs.getInt("sesid"));
//                ses.setDate(rs.getDate("date"));
//                ses.setIndex(rs.getInt("index"));
//
//                Lecturer l = new Lecturer();
//                l.setLid(rs.getInt("lid"));
//                l.setLname(rs.getString("lname"));
//                ses.setLecturer(l);
//
//                Group g = new Group();
//                g.setGid(rs.getInt("gid"));
//                g.setGname(rs.getString("gname"));
//                ses.setGroup(g);
//
//                Room r = new Room();
//                r.setRid(rs.getInt("rid"));
//                r.setRname(rs.getString("rname"));
//                ses.setRoom(r);
//
//                TimeSlot t = new TimeSlot();
//                t.setTid(rs.getInt("tid"));
//                t.setDescription(rs.getString("tdescription"));
//                ses.setTimeslot(t);
//
//                Subject sub = new Subject();
//                sub.setSubid(rs.getInt("subid"));
//                sub.setSubname(rs.getString("subname"));
//                g.setSubject(sub);
//                //read student
//                if (rs.getInt("stdid") != 0) {
//                    Student s = new Student();
//                    s.setStdid(rs.getInt("stdid"));
//                    s.setStdname(rs.getString("stdname"));
//                    //read attandance
//                    Attendance a = new Attendance();
//                    a.setStudent(s);
//                    a.setSession(ses);
//                    a.setPresent(rs.getBoolean("present"));
//                    a.setDescription(rs.getString("description"));
//                    a.setRecord_time(rs.getString("record_time"));
//                    ses.getAttendances().add(a);
//                }
//                list.add(ses);
//            }
//            return list;
//        } catch (SQLException e) {
//        }
//        return null;
//    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {

        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET attanded = 1 WHERE sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();

            //remove old attandances
            sql = "DELETE Attandance WHERE sesid = ? ";
            PreparedStatement stm_delete = connection.prepareStatement(sql);
            stm_delete.setInt(1, model.getId());
           
            stm_delete.executeUpdate();

            //insert new attandances
            for (Attendance att : model.getAttendances()) {
                sql = "INSERT INTO [Attandance]\n"
                        + "           ([sesid]\n"
                        + "           ,[stdid]\n"
                        + "           ,[present]\n"
                        + "           ,[description]\n"
                        + "           ,[record_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert = connection.prepareStatement(sql);
                stm_insert.setInt(1, model.getId());
                stm_insert.setInt(2, att.getStudent().getStdid());
                stm_insert.setBoolean(3, att.isPresent());
                stm_insert.setString(4, att.getDescription());
                stm_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
