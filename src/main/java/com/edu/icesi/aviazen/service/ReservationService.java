package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.dto.ReservationDTO;

import java.util.Optional;

public interface ReservationService extends GenericService<Reservation, Long>{

    Optional<ReservationDTO> findById(Integer id);


}
