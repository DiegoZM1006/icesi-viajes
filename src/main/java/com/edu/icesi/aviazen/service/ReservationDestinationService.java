package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDestinationService extends GenericService<ReservationDestination, Long>{

    @Query("SELECT d.name, COUNT(r.id) FROM ReservationDestination rd JOIN rd.destination d JOIN rd.reservation r GROUP BY d.name ORDER BY COUNT(r.id) DESC")
    List<Object[]> getTopFiveMostSalesDestinations(Pageable pageable);

}
