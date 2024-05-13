package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.dto.DestinationDTO;

import java.util.List;
import java.util.Optional;

public interface DestinationService extends GenericService<Destination, Long>{

    Optional<DestinationDTO> findById(Integer id);

}
