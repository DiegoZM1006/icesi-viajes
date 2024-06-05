package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.dto.ReservationDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService extends GenericService<Reservation, Long>{

    Optional<ReservationDTO> findById(Integer id);

    @Query("SELECT SUM(r.total) FROM Reservation r")
    Long getTotalPriceReservations();

    @Query("SELECT r.reservationDate, r.total " +
            "FROM Reservation r " +
            "WHERE r.reservationDate >= :startOfWeek " +
            "AND r.reservationDate <= :endOfWeek " +
            "GROUP BY r.reservationDate, r.total"
    )
    List<Object> findWeeklySales();

    @Query("SELECT r.seller.name, SUM(r.total) FROM Reservation r GROUP BY r.seller.id, r.seller.name ORDER BY SUM(r.total) DESC LIMIT 5")
    List<Object> findTopTenBetterSeller();

    @Query("SELECT r.seller.name, r.customer.name, rd.destination.name, r.reservationDate, rd.total " +
            "FROM Reservation r " +
            "JOIN ReservationDestination rd ON r.id = rd.reservation.id " +
            "ORDER BY r.reservationDate DESC LIMIT 10")
    List<Object[]> findLatestSales();


}
