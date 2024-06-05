package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import com.edu.icesi.aviazen.service.DestinationService;
import com.edu.icesi.aviazen.service.ReservationDestinationService;
import com.edu.icesi.aviazen.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            System.out.println(request);
            reservationDestinationService.save(request);
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudo guardar la reservación del destino", HttpStatus.BAD_REQUEST);
        }
    }

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

    private ReservationDestination mapToEntity(ReservationDestinationDTO dto) {
        ReservationDestination entity = new ReservationDestination();
        entity.setId(dto.getId());
        entity.setDestination(destinationService.findById(dto.getDestination_id()).get());
        entity.setReservation(reservationService.findById(dto.getReservation_id()).get());
        entity.setTotal(dto.getTotal());
        return entity;
    }

}

