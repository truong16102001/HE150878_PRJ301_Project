/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author ThinkPro
 */
public class Attendance {
    private Session session;
    private Student student;
    private boolean present;
    private String description;
    private String   record_time;   
           

    public Attendance() {
    }

   

  
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getRecord_time() {
        return  record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

}
