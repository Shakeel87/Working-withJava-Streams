package com.sa.test;

import com.sa.model.Employee;
import com.sa.repo.AppDataRepo;


import java.util.stream.Stream;

public class StreamCreationTestMain {

    public static void main(String... args){


        // Create streams

        // Approach 1: Stream.of
        System.out.println("Stream creation 1::");
        Stream.of(AppDataRepo.getAllEmployeesArray())
                .forEach(System.out::println);

        // Approach 2: Stream.builder

        System.out.println("Stream creation 2::");
       Stream.Builder streamBuilder =  Stream.builder();
        for (Employee emp:AppDataRepo.getAllEmployeesArray()
             ) {
            streamBuilder.accept(emp);
        }
      streamBuilder.build().forEach(System.out::println);

        System.out.println("Stream creation 3::");

        AppDataRepo.getAllEmployeesList().stream()
                .forEach(System.out::println);


        // Approach 4 infinite stream
        System.out.println("Stream creation 4::");

        Stream.iterate(1,i->i*2)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .skip(2)
                .limit(20)
                .forEach(System.out::println);

        // Java 9 , new concept
        Stream.iterate(2, i->i+2)
                .takeWhile(i->i<15)
                .forEach(System.out::println);

        Stream.iterate(2, i->i+2)
                .dropWhile(i->i<5)
                .takeWhile(i->i<100)
                .forEach(System.out::println);
    }

}
