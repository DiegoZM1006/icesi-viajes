package com.edu.icesi.aviazen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DestinationDTO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String aditional_information;
    private String country;
    private String city;
    private Integer days;
    private Integer nights;
    private String tickets;
    private String hotel;
    private String image;
    private Double price;

}
