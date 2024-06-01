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
import java.util.Optional;

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

    @GetMapping(value = "searchDestination/{id}")
    public ResponseEntity<?> searchDestination(@RequestHeader("Authorization") String token, @PathVariable Long id)
    {
        Optional<Destination> destination = destinationService.findById(id);
        if (destination.isPresent()) {
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Destino no encontrado", HttpStatus.NOT_FOUND);
        }
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

    @DeleteMapping(value = "deleteDestination/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception {
        destinationService.deleteById(id);
        return new ResponseEntity<>("Destino eliminado", HttpStatus.OK);
    }

    @PostMapping(value = "updateDestination/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO, @RequestHeader("Authorization") String token) throws Exception {
        Optional<Destination> destinationOp = destinationService.findById(id);
        if (destinationOp.isPresent()) {
            Destination destination = destinationOp.get();
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
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
