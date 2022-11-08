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
    private ArrayList<Session> sess = new ArrayList();
    private String sem;
    private int year;

    
    public Group(int gid, String gname, Subject subject, Lecturer supervisor, String sem, int year) {
        this.gid = gid;
        this.gname = gname;
        this.subject = subject;
        this.supervisor = supervisor;
        this.sem = sem;
        this.year = year;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    public ArrayList<Session> getSess() {
        return sess;
    }

    public void setSess(ArrayList<Session> sess) {
        this.sess = sess;
    }

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
