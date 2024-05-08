package com.edu.icesi.aviazen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String phone_number;
    private String address;
    private String username;
    private String password;

}
