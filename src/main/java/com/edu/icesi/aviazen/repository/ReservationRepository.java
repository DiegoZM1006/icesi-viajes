package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.dto.DestinationDTO;
import com.edu.icesi.aviazen.dto.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<ReservationDTO> findById(Integer id);

}
