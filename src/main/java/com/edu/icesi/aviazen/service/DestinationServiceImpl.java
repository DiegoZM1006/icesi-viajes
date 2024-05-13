package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.dto.DestinationDTO;
import com.edu.icesi.aviazen.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public Optional<DestinationDTO> findById(Integer id) {
        return destinationRepository.findById(id);
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Optional<Destination> findById(Long aLong) {
        return destinationRepository.findById(aLong);
    }

    @Override
    public Destination save(Destination entity) throws Exception {
        return destinationRepository.save(entity);
    }

    @Override
    public Destination update(Destination entity) throws Exception {
        return destinationRepository.save(entity);
    }

    @Override
    public void delete(Destination entity) throws Exception {
        destinationRepository.delete(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        destinationRepository.deleteById(aLong);
    }

    @Override
    public void validate(Destination entity) throws Exception {

    }

    @Override
    public Long count() {
        return destinationRepository.count();
    }
}
