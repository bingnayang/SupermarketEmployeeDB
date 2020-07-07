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
    public static final String TABLE_EMPLOYMENT_STATUS = "employmentStatus";
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

    // INSERT INTO employee
    // (first_name,last_name,title_Id,salary,startDate,endDate,employmentStatus) VALUES(?,?,?,?,?,?,?)
    private static final String INSERT_NEW_EMPLOYEE =
            "INSERT INTO " +TABLE_EMPLOYEE+
            "("+COLUMN_EMPLOYEE_FIRST_NAME+","+
                    COLUMN_EMPLOYEE_LAST_NAME+","+
                    COLUMN_EMPLOYEE_TITLE_ID+","+
                    COLUMN_EMPLOYEE_SALARY+","+
                    COLUMN_EMPLOYEE_START_DATE+","+
                    COLUMN_EMPLOYEE_END_DATE+","+
                    COLUMN_EMPLOYEE_STATUS_ID+") VALUES(?,?,?,?,?,?,?)";


//    UPDATE employee
//    SET salary = "15.00"
//    WHERE employee.first_name = "Tom"
//    AND employee.last_name = "Burlington"
    private static final String UPDATE_EMPLOYEE_SALARY =
            "UPDATE "+TABLE_EMPLOYEE+
            " SET "+COLUMN_EMPLOYEE_SALARY +"=?"+
            " WHERE "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_FIRST_NAME+"=?"+
            " AND "+TABLE_EMPLOYEE+"."+COLUMN_EMPLOYEE_LAST_NAME+"=?";

    private static final String UPDATE_EMPLOYEE_STATUS = "";
    private static final String UPDATE_EMPLOYEE_TITLE = "";

    //    SELECT employmentStatus.employment_Status_Id
    //    FROM employmentStatus
    //    WHERE employmentStatus.employment_Status = "Full Time"
    private static final String QUERY_EMPLOYMENT_STATUS_ID =
            "SELECT "+TABLE_EMPLOYMENT_STATUS+"."+COLUMN_EMPLOYMENT_STATUS_ID+
            " FROM "+TABLE_EMPLOYMENT_STATUS+
            " WHERE "+TABLE_EMPLOYMENT_STATUS+"."+COLUMN_EMPLOYMENT_STATUS+"=?";

    //    SELECT title.title_Id
    //    FROM title
    //    WHERE title.title_Name = "Cashier"
    private static final String QUERY_TITLE_ID =
            "SELECT "+TABLE_TITLE+"."+COLUMN_TITLE_ID+
            " FROM "+TABLE_TITLE+
            " WHERE "+TABLE_TITLE+"."+COLUMN_TITLE_NAME+"=?";

    private Connection connection;

    private PreparedStatement queryEmployees;
    private PreparedStatement queryEmployeesByDepartment;
    private PreparedStatement queryEmployeesByTitle;
    private PreparedStatement queryEmployeeByName;
    private PreparedStatement insertIntoEmployee;
    private PreparedStatement updateEmployeeSalary;
//
    private PreparedStatement queryEmploymentStatusId;
    private PreparedStatement queryTitleId;

    // Open Connection
    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);

            queryEmployees = connection.prepareStatement(QUERY_EMPLOYEES);
            queryEmployeesByDepartment = connection.prepareStatement(QUERY_EMPLOYEES_BY_DEPARTMENT);
            queryEmployeesByTitle = connection.prepareStatement(QUERY_EMPLOYEES_BY_TITLE);
            queryEmployeeByName = connection.prepareStatement(QUERY_EMPLOYEE_BY_NAME);
            insertIntoEmployee = connection.prepareStatement(INSERT_NEW_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            updateEmployeeSalary = connection.prepareStatement(UPDATE_EMPLOYEE_SALARY);

            queryEmploymentStatusId = connection.prepareStatement(QUERY_EMPLOYMENT_STATUS_ID);
            queryTitleId = connection.prepareStatement(QUERY_TITLE_ID);

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
            if(insertIntoEmployee != null){
                insertIntoEmployee.close();
            }
            if(queryEmploymentStatusId != null){
                queryEmploymentStatusId.close();
            }
            if(queryTitleId != null){
                queryTitleId.close();
            }
            if(updateEmployeeSalary != null){
                updateEmployeeSalary.close();
            }
            // Close Connection
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public int queryTitleIdByTitle(String titleName){
        try{
            queryTitleId.setString(1,titleName);
            ResultSet resultSet = queryTitleId.executeQuery();

            int titleId = resultSet.getInt(1);
            return titleId;
        }catch (SQLException e){
            System.out.println("Title Id Query Failed: " + e.getMessage());
            return -1;
        }
    }

    public int queryEmploymentStatusIdByStatus(String status){
        try{
            queryEmploymentStatusId.setString(1,status);
            ResultSet resultSet = queryEmploymentStatusId.executeQuery();

            int statusId = resultSet.getInt(1);
            return statusId;
        }catch (SQLException e){
            System.out.println("Title Id Query Failed: " + e.getMessage());
            return -1;
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

    public void insertNewEmployee(String firstName, String lastName, String title, double salary, String startDate, String endDate, String status){

        try{
            int titleID = queryTitleIdByTitle("Scanning Coordinator");
            int statusID = queryEmploymentStatusIdByStatus("Part Time");

            connection.setAutoCommit(false);
            insertIntoEmployee.setString(1,"Tom"); // first name
            insertIntoEmployee.setString(2,"Burlington"); // last
            insertIntoEmployee.setInt(3,titleID); // title
            insertIntoEmployee.setDouble(4,15.50); // salary
            insertIntoEmployee.setString(5,"2019/01/01"); // start date
            insertIntoEmployee.setString(6,""); // end date
            insertIntoEmployee.setInt(7,statusID); // status
            int affectedRows = insertIntoEmployee.executeUpdate();
            if(affectedRows == 1) {
                System.out.println("Employee Insert Succeed");
                connection.commit();
            } else {
                throw new SQLException("The employee insert failed");
            }
        }catch (Exception e){
            System.out.println("Insert Employee exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch(SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }finally {
                try {
                    System.out.println("Resetting default commit behavior");
                    connection.setAutoCommit(true);
                } catch(SQLException e1) {
                    System.out.println("Couldn't reset auto-commit! " + e1.getMessage());
                }
            }
        }
    }

    public void updateEmployeeSalaryByName(){
        try{
            connection.setAutoCommit(false);
            updateEmployeeSalary.setString(1,"15.00");
            updateEmployeeSalary.setString(2,"Tom");
            updateEmployeeSalary.setString(3,"Burlington");
            int affectedRows = updateEmployeeSalary.executeUpdate();
            if(affectedRows == 1) {
                System.out.println("Employee Update Succeed");
                connection.commit();
            } else {
                throw new SQLException("The employee insert failed");
            }
        }catch (Exception e){
            System.out.println("Update Employee exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch(SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }finally {
                try {
                    System.out.println("Resetting default commit behavior");
                    connection.setAutoCommit(true);
                } catch(SQLException e1) {
                    System.out.println("Couldn't reset auto-commit! " + e1.getMessage());
                }
            }
        }
    }

}
