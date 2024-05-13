package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.dto.DestinationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<DestinationDTO> findById(Integer id);

}
