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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "saveDestination")
    public ResponseEntity<Destination> saveDestination(@RequestBody DestinationDTO destinationDTO, @RequestHeader("Authorization") String token) throws Exception {
        Destination destination = new Destination();
        destination.setName(destinationDTO.getName());
        destination.setDescription(destinationDTO.getDescription());
        destination.setAditional_information(destinationDTO.getAditional_information());
        destination.setCountry(destinationDTO.getCountry());
        destination.setCity(destinationDTO.getCity());
        destination.setDays(destinationDTO.getDays());
        destination.setNights(destinationDTO.getNights());
        destination.setTickets(destinationDTO.getTickets());
        destination.setHotel(destinationDTO.getHotel());
        destination.setImage(destinationDTO.getImage());
        destination.setPrice(destinationDTO.getPrice());
        destinationService.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

}
