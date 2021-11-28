package com.sa.model;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {

    private String addressLine;
    private int pinCode;
    private String city;
    private String state;
    private String Country;
}
