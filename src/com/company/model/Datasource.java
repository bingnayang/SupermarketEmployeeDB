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
    public static final String TABLE_TITLE = "title";
    public static final String COLUMN_TITLE_ID = "title_Id";
    public static final String COLUMN_TITLE_NAME = "title_Name";
    public static final String COLUMN_TITLE_DEPARTMENT_ID = "department_Id";
    // Employment Status Table
    public static final String TABLE_Employment_Status = "employmentStatus";
    public static final String COLUMN_EMPLOYMENT_STATUS_ID = "employment_Status_Id";
    public static final String COLUMN_EMPLOYMENT_STATUS = "employment_Status";
    // EmployeeInfo View Table
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
     * SQL: SELECT, DELETE, UPDATE, INSERT
     */
    //  SELECT *
    //  FROM employeeInfo
    private static final String QUERY_EMPLOYEES =
            "SELECT * FROM "+TABLE_EMPLOYEEINFO;

    //  SELECT employee.first_name, employee.last_name, title.title_Name
    //  FROM employee
    //  INNER JOIN title, department
    //  WHERE employee.title_Id = title.title_Id
    //  AND department.department_Id = title.department_Id
    //  AND department.department_Name ="Front End"
    private static final String QUERY_EMPLOYEES_BY_DEPARTMENT =
            "SELECT "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_FIRST_NAME+","+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_LAST_NAME+","+TABLE_TITLE+"."+COLUMN_TITLE_NAME+
            " FROM "+TABLE_EMPLOYEE+
            " INNER JOIN "+TABLE_TITLE+","+TABLE_DEPARTMENT+
            " WHERE "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_TITLE_ID+"="+TABLE_TITLE+"."+COLUMN_TITLE_ID+
            " AND "+TABLE_DEPARTMENT+"."+COLUMN_DEPARTMENT_ID+"="+TABLE_TITLE+"."+COLUMN_TITLE_DEPARTMENT_ID+
            " AND "+TABLE_DEPARTMENT+"."+COLUMN_DEPARTMENT_NAME+"=?";

//    SELECT employee.first_name, employee.last_name
//    FROM employee
//    INNER JOIN title
//    WHERE employee.title_Id = title.title_Id
//    AND title.title_Name = "Cashier"
    private static final String QUERY_EMPLOYEES_BY_TITLE =
            "SELECT "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_FIRST_NAME+","+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_LAST_NAME+
            " FROM "+TABLE_EMPLOYEE+
            " INNER JOIN "+TABLE_TITLE+
            " WHERE "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_TITLE_ID+"="+TABLE_TITLE+"."+COLUMN_TITLE_ID+
            " AND "+TABLE_TITLE+"."+COLUMN_TITLE_NAME+"=?";

//    SELECT *
//    FROM employeeInfo
//    WHERE employeeInfo.first_name="Pedro" AND employeeInfo.last_name="Mose"
    private static final String QUERY_EMPLOYEE_BY_NAME =
            "SELECT * "+
            " FROM "+TABLE_EMPLOYEEINFO+
            " WHERE "+TABLE_EMPLOYEEINFO+"."+COLUMN_EMPLOYEEINFO_FIRST_NAME+"=?"+
            " AND "+TABLE_EMPLOYEEINFO+"."+COLUMN_EMPLOYEEINFO_LAST_NAME+"=?";

    private Connection connection;

    private PreparedStatement queryEmployees;
    private PreparedStatement queryEmployeesByDepartment;
    private PreparedStatement queryEmployeesByTitle;
    private PreparedStatement queryEmployeeByName;


    // Open Connection
    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);

            queryEmployees = connection.prepareStatement(QUERY_EMPLOYEES);
            queryEmployeesByDepartment = connection.prepareStatement(QUERY_EMPLOYEES_BY_DEPARTMENT);
            queryEmployeesByTitle = connection.prepareStatement(QUERY_EMPLOYEES_BY_TITLE);
            queryEmployeeByName = connection.prepareStatement(QUERY_EMPLOYEE_BY_NAME);

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
            if(queryEmployeesByDepartment != null){
                queryEmployeesByDepartment.close();
            }
            if(queryEmployeesByTitle != null){
                queryEmployeesByTitle.close();
            }
            if(queryEmployeeByName != null){
                queryEmployeeByName.close();
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
                employeeInfo.setSalary(resultSet.getDouble(4));
                employeeInfo.setTitle_Name(resultSet.getString(5));
                employeeInfo.setDepartment_Name(resultSet.getString(6));
                employeeInfo.setStart_Date(resultSet.getString(7));
                employeeInfo.setEnd_Date(resultSet.getString(8));
                employeesInfoList.add(employeeInfo);
            }

            return employeesInfoList;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAllEmployeesByDepartment(String deptName){
        try{
            queryEmployeesByDepartment.setString(1,deptName);
            ResultSet resultSet = queryEmployeesByDepartment.executeQuery();

            List<String> employeesList = new ArrayList<>();
            while (resultSet.next()){
                employeesList.add("Name: "+resultSet.getString(1)+" "+resultSet.getString(2)+
                                    "\nTitle: "+resultSet.getString(3)+"\n");
            }
            return employeesList;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryEmployeesByTitle(String titleName){
        try{
            queryEmployeesByTitle.setString(1,titleName);
            ResultSet resultSet = queryEmployeesByTitle.executeQuery();
            List<String> employeesList = new ArrayList<>();
            while (resultSet.next()){
                employeesList.add("Name: "+resultSet.getString(1)+" "+resultSet.getString(2));
            }
            return employeesList;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<EmployeeInfo> queryEmployeeByName(String firstName, String lastName){
        try{
            queryEmployeeByName.setString(1,firstName);
            queryEmployeeByName.setString(2,lastName);
            ResultSet resultSet = queryEmployeeByName.executeQuery();
            List<EmployeeInfo> employeesList = new ArrayList<>();
            while (resultSet.next()){
                EmployeeInfo employeeInfo = new EmployeeInfo();
                employeeInfo.setEmployee_Id(resultSet.getInt(1));
                employeeInfo.setFirst_Name(resultSet.getString(2));
                employeeInfo.setLast_Name(resultSet.getString(3));
                employeeInfo.setSalary(resultSet.getDouble(4));
                employeeInfo.setTitle_Name(resultSet.getString(5));
                employeeInfo.setDepartment_Name(resultSet.getString(6));
                employeeInfo.setStart_Date(resultSet.getString(7));
                employeeInfo.setEnd_Date(resultSet.getString(8));
                employeesList.add(employeeInfo);
            }
            return employeesList;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
