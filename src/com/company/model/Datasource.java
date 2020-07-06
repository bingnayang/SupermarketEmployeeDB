package com.company.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    // Database name
    // SQLite connection strings
    public static final String DB_Name = "supermarketemployee.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/Bing/Documents/GitHub/BingNaYang.github.io/SupermarketEmployeeDB/" + DB_Name;
    // Constant Variables
    // Employee Table
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String COLUMN_EMPLOYEE_ID = "employee_Id";
    public static final String COLUMN_EMPLOYEE_FIRST_NAME = "first_name";
    public static final String COLUMN_EMPLOYEE_LAST_NAME = "last_name";
    public static final String COLUMN_EMPLOYEE_TITLE_ID = "title_Id";
    public static final String COLUMN_EMPLOYEE_SALARY = "salary";
    public static final String COLUMN_EMPLOYEE_START_DATE = "startDate";
    public static final String COLUMN_EMPLOYEE_END_DATE = "endDate";
    public static final String COLUMN_EMPLOYEE_STATUS_ID = "employmentStatus_Id";
    // Department Table
    public static final String TABLE_DEPARTMENT = "department";
    public static final String COLUMN_DEPARTMENT_ID = "department_Id";
    public static final String COLUMN_DEPARTMENT_NAME = "department_Name";
    // Title Table
    public static final String TABLE_Title = "title";
    public static final String COLUMN_TITLE_ID = "title_Id";
    public static final String COLUMN_TITLE_NAME = "title_Name";
    public static final String COLUMN_TITLE_DEPARTMENT_ID = "department_Id";
    // Employment Status Table
    public static final String TABLE_Employment_Status = "employmentStatus";
    public static final String COLUMN_EMPLOYMENT_STATUS_ID = "employment_Status_Id";
    public static final String COLUMN_EMPLOYMENT_STATUS = "employment_Status";
    // EmployeeInfo Table
    public static final String TABLE_EMPLOYEEINFO = "employeeInfo";
    public static final String COLUMN_EMPLOYEEINFO_EMPLOYEE_ID = "employee_Id";
    public static final String COLUMN_EMPLOYEEINFO_FIRST_NAME = "first_Name";
    public static final String COLUMN_EMPLOYEEINFO_LAST_NAME = "last_Name";
    public static final String COLUMN_EMPLOYEEINFO_SALARY = "salary";
    public static final String COLUMN_EMPLOYEEINFO_TITLE_NAME = "title_Name";
    public static final String COLUMN_EMPLOYEEINFO_DEPARTMENT_NAME = "department_Name";
    public static final String COLUMN_EMPLOYEEINFO_START_DATE = "startDate";
    public static final String COLUMN_EMPLOYEEINFO_END_DATE = "endDate";

    /**
     * SQL:
     */
    //    SELECT * FROM employeeInfo
    private static final String QUERY_EMPLOYEES = "SELECT * FROM "+TABLE_EMPLOYEEINFO;


    private Connection connection;

    // Use Prepared Statement
    private PreparedStatement queryEmployees;


    // Open Connection
    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);

            queryEmployees = connection.prepareStatement(QUERY_EMPLOYEES);

            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            e.getMessage();
            return false;
        }
    }
    // Close Connection
    public void close(){
        try{
            if(queryEmployees != null){
                queryEmployees.close();
            }

            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<EmployeeInfo> queryEmployeeInfo(){
        try{
            ResultSet resultSet = queryEmployees.executeQuery();
            List<EmployeeInfo> employeesInfoList = new ArrayList<>();
            while(resultSet.next()){
                EmployeeInfo employeeInfo = new EmployeeInfo();
                employeeInfo.setEmployee_Id(resultSet.getInt(1));
                employeeInfo.setFirst_Name(resultSet.getString(2));
                employeeInfo.setLast_Name(resultSet.getString(3));
                employeesInfoList.add(employeeInfo);
            }

            return employeesInfoList;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

}
