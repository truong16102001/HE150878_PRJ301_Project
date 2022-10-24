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
import model.Student;

/**
 *
 * @author ThinkPro
 */
public class StudentDAO extends DBContext<Student>{

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<Student> filterStudent(int gid){
        ArrayList<Student> list = new ArrayList();
        try {
            String sql = " select st.stdid, st.stdname from Student st inner join "
                    + "Student_Group sg on sg.stdid = st.stdid where sg.gid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
}
