package com.sa.repo;

import com.sa.model.Employee;

import java.util.Arrays;
import java.util.List;




public class AppDataRepo {

   private static  final Employee[] empArray=
       {new Employee(1,"Shakeel","Ahmad",1000.0),
               new Employee(2,"Ahamd","Bhai",12000.0),
               new Employee(3,"Ankur","Kumar",15000.0),
               new Employee(4,"Abhishek","Kumar",17000.0),
               new Employee(5,"Bhuvi","Kumar",17000.0)};


    public  static  List<Employee> getAllEmployeesList(){
        return Arrays.asList(empArray);
    }

   public static Employee[] getAllEmployeesArray(){
        return empArray;
    }

}
