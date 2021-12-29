package com.example.projudent;

public class Project {
    private String Title;
    private String Desc;
    private int year;
    private String image;

    public Project(String title, String desc, int year, String image) {
        Title = title;
        Desc = desc;
        this.year = year;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Title='" + Title + '\'' +
                ", Desc='" + Desc + '\'' +
                ", year=" + year +
                ", image='" + image + '\'' +
                '}';
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
}
