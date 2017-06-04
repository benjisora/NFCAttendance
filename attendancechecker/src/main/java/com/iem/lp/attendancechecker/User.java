package com.iem.lp.attendancechecker;

/**
 * Created by aturlier on 31/05/17.
 */

public class User {

    private String firstname = "";
    private String lastname = "";
    private String group = "";
    private String lastattendance = "";
    private String studentId = "";


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLastAttendance() {
        return lastattendance;
    }

    public void setLastAttendance(String lastAttendance) {
        this.lastattendance = lastAttendance;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
