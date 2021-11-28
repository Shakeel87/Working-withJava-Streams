package com.sa.test;

import com.sa.model.Address;
import com.sa.model.Employee;
import com.sa.repo.AppDataRepo;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class StreamOperatorsTest {

    public  static void main(String... args){
       StreamOperatorsTest.filterTest();
      StreamOperatorsTest.partitionTest();
      StreamOperatorsTest.groupingTest();
      StreamOperatorsTest.flatMapTest();
    }

    private static  void filterTest(){

        // When an optional is needed mostly called by other part of the code.
        Optional<Employee> employeeOptional= AppDataRepo.getAllEmployeesList().stream()
                .filter(emp->emp.getSalary()>10000)
                .findFirst();

       if(employeeOptional.isPresent()){
           System.out.println("Employee Details::"+ employeeOptional.get());
       }else{
           System.out.println(" No Such employee exists!");
       }

       // When we need the Employee object when caller does not want the Optional check, but has to handle RunTime exception

       Employee employee = AppDataRepo.getAllEmployeesList()
               .stream()
               .filter(emp->emp.getSalary()>10000)
               .findFirst()
               .orElseThrow(NoSuchElementException::new);

        System.out.println("Employee Details::"+ employee);




        List<Employee> allWithSalaryMoreThan12k = AppDataRepo.getAllEmployeesList()
                                                 .stream()
                                                 .filter(emp->emp.getSalary()>12000)
                                                 .collect(Collectors.toList());

        System.out.println("All employees with salary > 12000::"+ allWithSalaryMoreThan12k);

        // AllMatch , AnyMatch none match

        System.out.println("Does all Employee getting salary>10k::" +AppDataRepo.getAllEmployeesList().stream()
                .allMatch(employee1 -> employee1.getSalary()>10000));

        System.out.println("Does any Employee getting salary>20k::" +AppDataRepo.getAllEmployeesList().stream()
                .anyMatch(employee1 -> employee1.getSalary()>20000));

        System.out.println("Does no Employee getting salary< 5k::" +AppDataRepo.getAllEmployeesList().stream()
                .noneMatch(
                        employee1 -> employee1.getSalary()<5000));


        // Sorting by Custom Comparator

        Comparator sortByFirstName = Comparator.comparing(Employee::getFirstName);

        Comparator sortBYId = Comparator.comparing(Employee::getId);

        Comparator sortBySalary = Comparator.comparing(Employee::getSalary);

        System.out.println("Employee list Sorted By firstName::"+  AppDataRepo.getAllEmployeesList().stream().sorted(sortByFirstName).collect(Collectors.toList()));
        System.out.println("Employee list Sorted By Salary then by firstname::"+  AppDataRepo.getAllEmployeesList().stream().sorted(sortBySalary.thenComparing(sortByFirstName)).collect(Collectors.toList()));


    }

    private  static  void partitionTest(){

        // Convert List to Map

        Map<Integer, String> empMapById = AppDataRepo.getAllEmployeesList()
                .stream()
                .collect(Collectors.toMap(Employee::getId,Employee::getFirstName));

        System.out.println("Employee list to Map of Id and firstname::"+ empMapById);



        Map<Boolean, List<Employee>> empPartition=AppDataRepo.getAllEmployeesList()
                .stream()
                .collect(Collectors.partitioningBy(emp->emp.getFirstName().startsWith("A")));

        System.out.println("Employees names starting with Letter 'A':: " + empPartition.get(Boolean.TRUE).stream().map(emp->emp.getFirstName().concat(emp.getLastName())).collect(Collectors.joining(",")));
        System.out.println("Employees names NOt starting with Letter 'A':: " + empPartition.get(Boolean.FALSE).stream().map(emp->emp.getFirstName().concat(emp.getLastName())).collect(Collectors.joining(",")));


    }

    private static  void groupingTest(){
        // Grouping  employees By their first C Letter of their firstName

        Map<Character,List<String>>  empGroupByFirstChar = AppDataRepo.getAllEmployeesList().stream()
                .collect(Collectors.groupingBy(emp->emp.getFirstName().charAt(0),Collectors.mapping(Employee::getFirstName,Collectors.toList())));


        System.out.println("Employee name grouped by first char::" + empGroupByFirstChar);

        DoubleSummaryStatistics salaryStats = AppDataRepo.getAllEmployeesList()
                .stream()
                .mapToDouble(emp->emp.getSalary())
                .summaryStatistics();

        System.out.println("Employee  Salary Summary::"+ salaryStats);

    }

    private static void flatMapTest()
    {
      List<Address> bangaloreAddress=  AppDataRepo.getAllEmployeesList()
                .stream()
                .map(employee -> employee.getAddress())
                .flatMap(Collection::stream)
                .filter(address -> address.getCity().equals("Bangalore"))
                .collect(Collectors.toList());

        System.out.println("Bangalore addresses:: " + bangaloreAddress);

        List<Employee> allEmployeesInBangalore = AppDataRepo.getAllEmployeesList().stream()
                .filter(employee -> employee.getAddress().stream().anyMatch(address -> address.getCity().equals("Bangalore")))
                .collect(Collectors.toList());

        System.out.println("All Employees in Bangalore::"+ allEmployeesInBangalore);
    }
}
