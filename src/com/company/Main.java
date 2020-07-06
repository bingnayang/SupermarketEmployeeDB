package com.company;

import com.company.model.Datasource;
import com.company.model.EmployeeInfo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Could not open datasource");
            return;
        }

        // Print all employees information in database
        System.out.println("----> Print All Employee Information <----");
        List<EmployeeInfo> employeeInfos = datasource.queryEmployeeInfo();
        if(employeeInfos.isEmpty()){
            System.out.println("Couldn't find");
        }
        for(EmployeeInfo employee: employeeInfos){
            System.out.println();
            System.out.println("Employee ID: "+employee.getEmployee_Id()+
                    "\nName: "+employee.getFirst_Name()+" "+employee.getLast_Name()+
                    "\nSalary: $"+employee.getSalary()+
                    "\nTitle: "+employee.getTitle_Name()+
                    "\nDepartment: "+employee.getDepartment_Name()+
                    "Start Date: "+employee.getStart_Date()+
                    "\nEnd Date: "+employee.getEnd_Date());
        }


        // Print employees by department
        System.out.println("----> Print Employees By Department <----");
        List<String> employeesByDepartment = datasource.queryEmployeeByDepartment("Front End");
        if(employeesByDepartment.isEmpty()){
            System.out.println("Couldn't find");
        }
        for(String list: employeesByDepartment){
            System.out.println(list);
        }

        // Close connection
        datasource.close();
    }
}
