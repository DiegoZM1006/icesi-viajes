package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.dto.DestinationDTO;
import com.edu.icesi.aviazen.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing destination-related operations.
 */

@RestController
@RequestMapping("/api/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    /**
     * Retrieves all destinations.
     *
     * @param token the authorization token
     * @return a list of all destinations
     */


    @GetMapping(value = "allDestinations")
    public ResponseEntity<List<Destination>> allClients(@RequestHeader("Authorization") String token)
    {
        List<Destination> destinations = destinationService.findAll();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    /**
     * Searches for a destination by its ID.
     *
     * @param token the authorization token
     * @param id the ID of the destination to search for
     * @return the destination if found, otherwise a not found response
     */

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

    /**
     * Saves a new destination.
     *
     * @param destinationDTO the details of the destination to save
     * @param token the authorization token
     * @return the saved destination
     * @throws Exception if an error occurs during the save
     */

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

    /**
     * Deletes a destination by its ID.
     *
     * @param id the ID of the destination to delete
     * @param token the authorization token
     * @return a success message if the destination is deleted
     * @throws Exception if an error occurs during the deletion
     */

    @DeleteMapping(value = "deleteDestination/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception {
        destinationService.deleteById(id);
        return new ResponseEntity<>("Destino eliminado", HttpStatus.OK);
    }

    /**
     * Updates an existing destination.
     *
     * @param id the ID of the destination to update
     * @param destinationDTO the new details of the destination
     * @param token the authorization token
     * @return the updated destination if found, otherwise a not found response
     * @throws Exception if an error occurs during the update
     */

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

    /**
     * Counts the total number of destinations.
     *
     * @param token the authorization token
     * @return the total number of destinations
     */

    @GetMapping(value = "countDestinations")
    public ResponseEntity<Long> allDestinations(@RequestHeader("Authorization") String token)
    {
        Long destinations = destinationService.count();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

}
