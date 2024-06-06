package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import com.edu.icesi.aviazen.service.DestinationService;
import com.edu.icesi.aviazen.service.ReservationDestinationService;
import com.edu.icesi.aviazen.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing reservation destination-related operations.
 */

@RestController
@RequestMapping("/api/v1/reservation_destination")
@RequiredArgsConstructor
public class ReservationDestinationController {

    private final ReservationDestinationService reservationDestinationService;
    private final DestinationService destinationService;
    private final ReservationService reservationService;

    /**
     * Adds a new reservation destination.
     *
     * @param token the authorization token
     * @param requestDTO the reservation destination details
     * @return the added reservation destination if successful, otherwise an error message
     */

    @PostMapping(value = "addReservationDestination")
    public ResponseEntity<?> addReservationDestination(@RequestHeader("Authorization") String token, @RequestBody ReservationDestinationDTO requestDTO) {
        try {
            ReservationDestination request = mapToEntity(requestDTO);
            System.out.println(request);
            reservationDestinationService.save(request);
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo guardar la reservación del destino", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets the top five most sold destinations.
     *
     * @return a list of the top five most sold destinations if successful, otherwise an error message
     */

    @GetMapping(value = "topFiveMostSalesDestinations")
    public ResponseEntity<?> getTopFiveMostSalesDestinations() {
        try {
            Pageable pageable = PageRequest.of(0, 5);
            List<Object[]> response = reservationDestinationService.getTopFiveMostSalesDestinations(pageable);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo obtener los destinos más vendidos", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets the reservation destinations for a specific client.
     *
     * @param id the client ID
     * @return a list of reservation destinations for the specified client if successful, otherwise an error message
     */

    @GetMapping(value = "getReservationDestinationsByClient/{id}")
    public ResponseEntity<?> getReservationDestinationsByUser(@PathVariable("id") Long id) {
        try {
            List<Object[]> response = reservationDestinationService.getReservationDestinationsByClient(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo obtener las reservaciones de destinos del usuario", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets the reservation destinations for a specific seller.
     *
     * @param id the seller ID
     * @return a list of reservation destinations for the specified seller if successful, otherwise an error message
     */

    @GetMapping(value = "getReservationDestinationsBySeller/{id}")
    public ResponseEntity<?> getReservationDestinationsBySeller(@PathVariable("id") Long id) {
        try {
            List<Object[]> response = reservationDestinationService.getReservationDestinationsBySeller(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo obtener las reservaciones de destinos del usuario", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Maps a ReservationDestinationDTO to a ReservationDestination entity.
     *
     * @param dto the reservation destination DTO
     * @return the mapped ReservationDestination entity
     */

    private ReservationDestination mapToEntity(ReservationDestinationDTO dto) {
        ReservationDestination entity = new ReservationDestination();
        entity.setId(dto.getId());
        entity.setDestination(destinationService.findById(dto.getDestination_id()).get());
        entity.setReservation(reservationService.findById(dto.getReservation_id()).get());
        entity.setTotal(dto.getTotal());
        entity.setReservation_date(dto.getReservation_date());
        return entity;
    }

}

