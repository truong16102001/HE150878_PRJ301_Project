/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ThinkPro
 */
public class Attendance {

    private int id;
    private Session session;
    private Student student;
    private boolean present;
    private String description;
    private Date record_time;

    public int getId() {
        return id;
    }

    public Attendance() {
    }

    
    public void setId(int id) {
        this.id = id;
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

    public Attendance(int id, Session session, Student student, boolean present, String description, Date record_time) {
        this.id = id;
        this.session = session;
        this.student = student;
        this.present = present;
        this.description = description;
        this.record_time = record_time;
    }

    public Date getRecord_time() {
        return record_time;
    }

    public void setRecord_time(Date record_time) {
        this.record_time = record_time;
    }

}
