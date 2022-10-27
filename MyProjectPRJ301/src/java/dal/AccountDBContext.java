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
import model.Account;
import model.Feature;
import model.Group;
import model.Lecturer;
import model.Role;
import model.Room;
import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author ThinkPro
 */
public class AccountDBContext extends DBContext<Account> {

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Account getAccountByLogin(String username, String password) {
        try {
            String sql = "select a.username, a.password,a.displayname from "
                    + "Account a where a.username = ? and a.password= ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setDisplayname(rs.getString("displayname"));
                return acc;
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Account get(String username, String password) {
        try {
            String sql = "SELECT\n"
                    + "                   	a.username, a.password, a.displayname\n"
                    + "                    	,r.roid,r.roname\n"
                    + "                    	,f.fid,f.fname,f.url\n"
                    + "                   	FROM Account a \n"
                    + "                    	LEFT JOIN Role_Account ra ON a.username = ra.username\n"
                    + "                    	LEFT JOIN [Role] r ON r.roid = ra.roid\n"
                    + "                    	LEFT JOIN [Role_Feature] rf ON rf.roid = r.roid\n"
                    + "                    	LEFT JOIN [Feature] f ON f.fid = rf.fid\n"
                    + "                    WHERE a.username =? AND a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account account = null;
            Role currentRole = new Role();
            currentRole.setRoid(-1);
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setUsername(username);
                    account.setPassword(rs.getString("password"));
                    account.setDisplayname(rs.getString("displayname"));
                }
                int rid = rs.getInt("roid");
                if (rid != 0) {
                    if (rid != currentRole.getRoid()) {
                        currentRole = new Role();
                        currentRole.setRoid(rs.getInt("roid"));
                        currentRole.setRoname(rs.getString("roname"));
                        account.getRoles().add(currentRole);
                    }
                }
                int fid = rs.getInt("fid");
                if (fid != 0) {
                    Feature f = new Feature();
                    f.setFid(fid);
                    f.setFname(rs.getString("fname"));
                    f.setUrl(rs.getString("url"));
                    currentRole.getFeatures().add(f);
                }

            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturer getAcReturnLecturer(String username, String password) {
        try {
            String sql = "select l.lid, l.lname from Lecturer l, Account a "
                    + "where l.username = a.username and a.username = ? and a.password = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Lecturer lec = null;
            if (rs.next()) {
                if (lec == null) {
                    lec = new Lecturer();
                    lec.setLid(rs.getInt("lid"));
                    lec.setLname(rs.getString("lname"));
                }
            }
            return lec;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturer getAcReturnLecturer2(String username, String password) {
        try {
            String sql = "select l.lid, l.lname, g.gid,g.sem, g.subid,s.stdid,s.stdname, g.year, g.gname, ses.sesid, r.rid\n" +
"                     ,r.rname, t.tid,t.[description] tdescription, ses.[index], ses.date, ses.attanded\n" +
"                   from Lecturer l, Account a,[Student] s,[Student_Group] sg, [Group] g, Session ses, Room r, TimeSlot t, Subject sub\n" +
"                                    where sg.gid = g.gid and s.stdid = sg.stdid and   sub.subid = g.subid and  r.rid = ses.rid and t.tid = ses.tid and l.lid = g.lid \n" +
"                   			and l.lid = ses.lid and l.username = a.username and a.username = ? and a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Lecturer lec = null;
            while (rs.next()) {
                if (lec == null) {
                    lec = new Lecturer();
                    lec.setLid(rs.getInt("lid"));
                    lec.setLname(rs.getString("lname"));

                    Group g = new Group();
                    g.setGid(rs.getInt("gid"));
                    g.setGname(rs.getString("gname"));

                    Subject sub = new Subject();
                    sub.setSubid(rs.getInt("subid"));
                    sub.setSubname(rs.getString("subname"));
                    g.setSubject(sub);

                    lec.getGroups().add(g);

                    Session ses = new Session();
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
                    ses.setGroup(g);
                    ses.setDate(rs.getDate("date"));
                    ses.setIndex(rs.getInt("index"));
                    ses.setAttendated(rs.getBoolean("attanded"));
                    lec.getSessions().add(ses);
                }
            }
            return lec;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
