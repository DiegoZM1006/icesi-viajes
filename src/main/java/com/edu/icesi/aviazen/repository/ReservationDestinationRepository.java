package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationDestinationRepository extends JpaRepository<ReservationDestination, Long> {

}
