package com.example.projudent;

import java.io.Serializable;

public class User implements Serializable {
    private int studentID;
    private String First_Name;
    private String Last_Name;
    private String Password;

    public User(int studentID, String first_Name, String last_Name, String password) {
        this.studentID = studentID;
        First_Name = first_Name;
        Last_Name = last_Name;
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentID=" + studentID +
                ", First_Name='" + First_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getPassword() {
        return Password;
    }
}
