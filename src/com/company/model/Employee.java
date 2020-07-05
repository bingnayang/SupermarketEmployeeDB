package com.company.model;

import java.util.Date;

public class Employee {
    private int employee_Id;
    private String first_name;
    private String last_name;
    private int title_Id;
    private double salary;
    private Date startDate;
    private Date endDate;
    private int employmentStatus;

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getTitle_Id() {
        return title_Id;
    }

    public void setTitle_Id(int title_Id) {
        this.title_Id = title_Id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(int employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
