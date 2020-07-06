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

        List<EmployeeInfo> employeeInfos = datasource.queryEmployeeInfo();
        System.out.println("___Print All Employee___");
        if(employeeInfos.isEmpty()){
            System.out.println("Couldn't find");
        }
        for(EmployeeInfo employee: employeeInfos){
            System.out.println(employee.getEmployee_Id()+" "+employee.getFirst_Name()+" "+employee.getLast_Name());
        }

        // Close connection
        datasource.close();
    }
}
