/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ThinkPro
 */
public class Group {

    private int gid;
    private String gname;
    private ArrayList<Student> students = new ArrayList();
    private Subject subject;
    private Lecturer supervisor;

    public Group() {
    }

    public Group(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    public Group(int gid, String gname, Subject subject, Lecturer supervisor) {
        this.gid = gid;
        this.gname = gname;
        this.subject = subject;
        this.supervisor = supervisor;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Lecturer supervisor) {
        this.supervisor = supervisor;
    }

}
