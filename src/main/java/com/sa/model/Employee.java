package com.sa.model;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private double Salary;
}
