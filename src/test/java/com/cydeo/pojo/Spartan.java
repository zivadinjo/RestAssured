package com.cydeo.pojo;

import lombok.Data;

/**
 {
 "id": 10,
 "name": "Lorenza",
 "gender": "Female",
 "phone": 3312820936
 }
 */

@Data // this will add data for each getter and setter variable
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;

}
