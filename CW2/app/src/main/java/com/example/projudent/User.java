package com.example.projudent;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int studentID;
    private String First_Name;
    private String Last_Name;
    private int Password;
    private ArrayList<Boolean> prefs;

    public User(int studentID, String first_Name, String last_Name, @NonNull String password) {
        this.studentID = studentID;
        First_Name = first_Name;
        Last_Name = last_Name;
        Password = password.hashCode();
        prefs = new ArrayList<Boolean>();
        prefs.add(true);
        prefs.add(true);
        prefs.add(true);

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

    public ArrayList<Boolean> getPrefs() {
        return prefs;
    }

    public void setPrefs(ArrayList<Boolean> prefs) {
        this.prefs = prefs;
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

    public int getPassword() {
        return Password;
    }
}
