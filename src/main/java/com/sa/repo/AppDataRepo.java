package com.sa.repo;

import com.sa.model.Address;
import com.sa.model.Employee;

import java.util.Arrays;
import java.util.List;




public class AppDataRepo {

    private  static final Address[] addressArrBlr = {new Address("22nd main",560023,"Bangalore","Karnataka","India"),
            new Address("20th main",560020,"Bangalore","Karnataka","India")};

private  static final Address[] addressArrHyd = {new Address("2nd main",556721,"Hyderabad","Telangana","India"),
        new Address("1st main",556723,"Hyderabad","Telangana","India")};

   private static  final Employee[] empArray=
       {new Employee(1,"Shakeel","Ahmad",11000.0,Arrays.asList(addressArrBlr)),
               new Employee(2,"Ahamd","Bhai",12000.0,Arrays.asList(addressArrHyd)),
               new Employee(3,"Ankur","Kumar",15000.0,Arrays.asList(addressArrBlr)),
               new Employee(4,"Abhishek","Kumar",17000.0,Arrays.asList(addressArrHyd)),
               new Employee(5,"Bhuvi","Kumar",17000.0,Arrays.asList(addressArrHyd))};


    public  static  List<Employee> getAllEmployeesList(){
        return Arrays.asList(empArray);
    }

   public static Employee[] getAllEmployeesArray(){
        return empArray;
    }

}
