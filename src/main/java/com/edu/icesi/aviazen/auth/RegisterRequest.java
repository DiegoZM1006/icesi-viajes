package com.edu.icesi.aviazen.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String name;
    String lastname;
    String phone_number;
    String card_number;
    String email;
    String address;
    String username;
    String password;

}

