package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDestinationRepository extends JpaRepository<ReservationDestination, Long> {

    @Query("SELECT d.name, COUNT(r.id) FROM ReservationDestination rd JOIN rd.destination d JOIN rd.reservation r GROUP BY d.name ORDER BY COUNT(r.id) DESC")
    List<Object[]> getTopFiveMostSalesDestinations(Pageable pageable);

    @Query("SELECT rd.destination.name, r.reservationDate, rd.total FROM ReservationDestination rd JOIN rd.reservation r WHERE r.customer.id = ?1")
    List<Object[]> getReservationDestinationsByClient(Long id);

    @Query("SELECT rd.destination.name, r.reservationDate, rd.total FROM ReservationDestination rd JOIN rd.reservation r WHERE r.seller.id = ?1")
    List<Object[]> getReservationDestinationsBySeller(Long id);

}
