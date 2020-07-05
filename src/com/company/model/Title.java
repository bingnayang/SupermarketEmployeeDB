package com.company.model;

public class Title {
    private int title_Id;
    private String title;
    private int department_Id;

    public int getTitle_Id() {
        return title_Id;
    }

    public void setTitle_Id(int title_Id) {
        this.title_Id = title_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDepartment_Id() {
        return department_Id;
    }

    public void setDepartment_Id(int department_Id) {
        this.department_Id = department_Id;
    }
}
