package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthService;
import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.dto.DestinationDTO;
import com.edu.icesi.aviazen.service.ClientService;
import com.edu.icesi.aviazen.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping(value = "allDestinations")
    public ResponseEntity<List<Destination>> allClients(@RequestHeader("Authorization") String token)
    {
        List<Destination> destinations = destinationService.findAll();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

}
