package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthResponse;
import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "countReservations")
    public ResponseEntity<?> countReservations(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(reservationService.count(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo contar las reservaciones", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "totalPriceReservations")
    public ResponseEntity<?> getTotalPriceReservations(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(reservationService.getTotalPriceReservations(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo obtener el precio total de las reservaciones", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "salesOfTheWeek")
    public ResponseEntity<?> getSalesOfTheWeek(@RequestHeader("Authorization") String token) {
        try {
            List<Object> response = reservationService.findWeeklySales();
            System.out.println(response);
            return new ResponseEntity<>(reservationService.findWeeklySales(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo obtener las ventas de la semana", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "topFiveBetterSeller")
    public ResponseEntity<?> getTopFiveBetterSeller(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(reservationService.findTopTenBetterSeller(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo obtener los cinco mejores vendedores", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "latestSales")
    public ResponseEntity<?> getLatestSales(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(reservationService.findLatestSales(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo obtener las últimas ventas", HttpStatus.BAD_REQUEST);
        }
    }

}
