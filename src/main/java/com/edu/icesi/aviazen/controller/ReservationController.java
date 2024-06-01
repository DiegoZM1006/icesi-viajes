package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthResponse;
import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(value = "addReservation")
    public ResponseEntity<?> addReservation(@RequestHeader("Authorization") String token, @RequestBody Reservation request) {
        try {
            reservationService.save(request);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo guardar la reservación", HttpStatus.BAD_REQUEST);
        }
    }

}
