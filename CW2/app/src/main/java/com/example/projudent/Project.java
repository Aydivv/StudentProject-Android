package com.example.projudent;

import java.io.Serializable;

public class Project implements Serializable {
    private int projectID;
    private int studentID;
    private String Title;
    private String Desc;
    private int year;
    private String image;
    private Boolean isExpanded;


    public Project(int projectID, int studentID, String title, String desc, int year, String image) {
        this.projectID = projectID;
        this.studentID = studentID;
        Title = title;
        Desc = desc;
        this.year = year;
        this.image = image;
        this.isExpanded = false;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", studentID=" + studentID +
                ", Title='" + Title + '\'' +
                ", Desc='" + Desc + '\'' +
                ", year=" + year +
                ", image='" + image + '\'' +
                ", isExpanded=" + isExpanded +
                '}';
    }


    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }
}
