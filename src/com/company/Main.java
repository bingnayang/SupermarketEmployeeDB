package com.company;

import com.company.model.Datasource;
import com.company.model.EmployeeInfo;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        Scanner scanner = new Scanner(System.in);
        int option;
        if(!datasource.open()){
            System.out.println("Could not open datasource");
            return;
        }
        do{
            startMenu();
            option = scanner.nextInt();
            switch (option){
                case 1:
                    displayAllEmployeeInfo(datasource);
                    break;
                case 2:
                    searchEmployeesByDepartment(datasource);
                    break;
                case 3:
                    searchEmployeesByTitle(datasource);
                    break;
                case 4:
                    searchEmployeeByName(datasource);
                    break;
                case 5:
                    insertNewEmployeeToDB(datasource);
                    break;
                case 6:
                    updateEmployeeSalary(datasource);
                    break;
                case 7:
                    updateEmployeeEndDate(datasource);
                    break;
                case 8:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Not an option");
                    break;
            }
        }while (option != 8);

        // Close connection
        datasource.close();
    }
    public static void startMenu(){
        System.out.println("========SuperMarket Employee DB========");
        System.out.println("1) Display All Employees Information from Database");
        System.out.println("2) Search Employees By Department Name");
        System.out.println("3) Search Employees By Title");
        System.out.println("4) Search Employee Info By Name");
        System.out.println("5) Add New Employee");
        System.out.println("6) Update Employee Salary");
        System.out.println("7) Update Employee End Date");
        System.out.println("8) Exit");
        System.out.print("Enter Your Option: ");
        System.out.println();
    }
    public static void displayAllEmployeeInfo(Datasource datasource){
        // Print all employees information from database
        System.out.println("----> Print All Employee Information <----");
        List<EmployeeInfo> employeeInfo = datasource.queryEmployeeInfo();
        if(employeeInfo.isEmpty()){
            System.out.println("Couldn't find");
        }
        for(EmployeeInfo employee: employeeInfo){
            System.out.println();
            System.out.println("Employee ID: "+employee.getEmployee_Id()+
                    "\nName: "+employee.getFirst_Name()+" "+employee.getLast_Name()+
                    "\nSalary: $"+employee.getSalary()+
                    "\nTitle: "+employee.getTitle_Name()+
                    "\nDepartment: "+employee.getDepartment_Name()+
                    "\nStart Date: "+employee.getStart_Date()+
                    "\nEnd Date: "+employee.getEnd_Date());
        }
    }
    public static void searchEmployeesByDepartment(Datasource datasource){
        // Print employees by department
        System.out.println("----> Print Employees By Department <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Department Name: ");
        String departmentName = scanner.nextLine();

        List<String> employeesByDepartment = datasource.queryAllEmployeesByDepartment(departmentName);
        if(employeesByDepartment.isEmpty()){
            System.out.println("Couldn't find");
        }
        System.out.println("----- "+departmentName+" Department Employees -----");
        for(String list: employeesByDepartment){
            System.out.println(list);
        }
    }
    public static void searchEmployeesByTitle(Datasource datasource){
        // Print employees by Title
        System.out.println("----> Print Employees By Title <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Title: ");
        String titleName = scanner.nextLine();

        List<String> employeesByTitle = datasource.queryEmployeesByTitle(titleName);
        if(employeesByTitle.isEmpty()){
            System.out.println("Couldn't find");
        }
        for(String list: employeesByTitle){
            System.out.println(list);
        }
    }

    public static void searchEmployeeByName(Datasource datasource){
        // Print employee by Name
        System.out.println("----> Print Employee Information By Name <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = scanner.next();
        System.out.println("Enter Last Name: ");
        String lastName = scanner.next();

        List<EmployeeInfo> employeeInfoByName = datasource.queryEmployeeByName(firstName,lastName);
        if (employeeInfoByName.isEmpty()) {
            System.out.println("Couldn't find");
            return;
        }
        for(EmployeeInfo employee: employeeInfoByName){
            System.out.println("Employee ID: "+employee.getEmployee_Id());
            System.out.println("Name: "+employee.getFirst_Name()+" "+employee.getLast_Name());
            System.out.println("Title/Department: "+employee.getTitle_Name()+"/"+employee.getDepartment_Name());
            System.out.println("Salary: $"+employee.getSalary());
            System.out.println("Start Date: "+employee.getStart_Date());
            System.out.println("End Date: "+employee.getEnd_Date());
        }
    }

    public static void insertNewEmployeeToDB(Datasource datasource){
        System.out.println("----> Insert New Employee To Database <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = scanner.next();
        System.out.println("Enter Last Name: ");
        String lastName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Job Title: ");
        String titleName = scanner.nextLine();
        while(datasource.queryTitleIdByTitle(titleName) == -1){
            System.out.println("No Such Job Title, Please Enter Again: ");
            titleName = scanner.nextLine();
        }
        int title = datasource.queryTitleIdByTitle(titleName);
        System.out.println("Enter Salary: ");
        double salary = scanner.nextDouble();
        System.out.println("Enter startDate: ");
        String startDate = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Status: ");
        String status = scanner.nextLine();
        while(datasource.queryEmploymentStatusIdByStatus(status) == -1){
            System.out.println("No Such Status, Please Enter Again: ");
            status = scanner.nextLine();
        }
        int statusName = datasource.queryEmploymentStatusIdByStatus(status);

        datasource.insertNewEmployee(firstName,lastName,title,salary,startDate,statusName);
    }

    public static void updateEmployeeSalary(Datasource datasource){
        System.out.println("----> Update Employee Salary By Name <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee First Name: ");
        String firstName = scanner.next();
        System.out.println("Enter Employee Last Name: ");
        String lastName = scanner.next();
        System.out.println("Enter Employee New Salary: ");
        double newSalary = scanner.nextDouble();

        datasource.updateEmployeeSalaryByName(newSalary,firstName,lastName);
    }

    public static void updateEmployeeEndDate(Datasource datasource){
        System.out.println("----> Update Employee End Date By Name <----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee First Name: ");
        String firstName = scanner.next();
        System.out.println("Enter Employee Last Name: ");
        String lastName = scanner.next();
        System.out.println("Enter Employee End Date: ");
        String endDate = scanner.next();

        datasource.updateEmployeeEndDateByName(endDate,firstName,lastName);
    }
}
