package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import com.edu.icesi.aviazen.service.DestinationService;
import com.edu.icesi.aviazen.service.ReservationDestinationService;
import com.edu.icesi.aviazen.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation_destination")
@RequiredArgsConstructor
public class ReservationDestinationController {

    private final ReservationDestinationService reservationDestinationService;
    private final DestinationService destinationService;
    private final ReservationService reservationService;

    @PostMapping(value = "addReservationDestination")
    public ResponseEntity<?> addReservationDestination(@RequestHeader("Authorization") String token, @RequestBody ReservationDestinationDTO requestDTO) {
        try {
            ReservationDestination request = mapToEntity(requestDTO);
            reservationDestinationService.save(request);
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo guardar la reservación del destino", HttpStatus.BAD_REQUEST);
        }
    }

    private ReservationDestination mapToEntity(ReservationDestinationDTO dto) {
        ReservationDestination entity = new ReservationDestination();
        entity.setId(dto.getId());
        entity.setDestination(destinationService.findById(dto.getDestination_id()).get());
        entity.setReservation(reservationService.findById(dto.getReservation_id()).get());
        entity.setTotal(dto.getTotal());
        return entity;
    }

}

