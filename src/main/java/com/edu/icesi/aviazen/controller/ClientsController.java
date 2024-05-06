package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.jwt.JwtService;
import com.edu.icesi.aviazen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping(value = "allClients")
    public ResponseEntity<List<User>> allClients(@RequestHeader("Authorization") String token)
    {
        List<User> clients = userRepository.findClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

}
