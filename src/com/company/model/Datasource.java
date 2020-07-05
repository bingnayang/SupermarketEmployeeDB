package com.company.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
    // Database name
    // SQLite connection strings
    public static final String DB_Name = "supermarketemployee.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/Bing/Documents/GitHub/BingNaYang.github.io/SupermarketEmployeeDB/" + DB_Name;



    private Connection connection;
    // Open Connection
    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);


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
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }


}
