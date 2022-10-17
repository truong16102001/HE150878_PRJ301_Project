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
public class Student {

    private int stdid;
    private String stdname;
    private String username;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Attendance> attandances = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Attendance> getAttandances() {
        return attandances;
    }

    public void setAttandances(ArrayList<Attendance> attandances) {
        this.attandances = attandances;
    }

    
    
    public Student() {
    }

    public Student(int stdid, String stdname) {
        this.stdid = stdid;
        this.stdname = stdname;
    }

    public Student(int stdid, String stdname, String username) {
        this.stdid = stdid;
        this.stdname = stdname;
        this.username = username;
    }

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public String getStdname() {
        return stdname;
    }

    public void setStdname(String stdname) {
        this.stdname = stdname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
