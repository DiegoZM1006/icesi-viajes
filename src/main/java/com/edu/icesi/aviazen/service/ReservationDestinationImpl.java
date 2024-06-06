package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.ReservationDestination;
import com.edu.icesi.aviazen.dto.ReservationDestinationDTO;
import com.edu.icesi.aviazen.repository.ReservationDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class ReservationDestinationImpl implements ReservationDestinationService {

    @Autowired
    private ReservationDestinationRepository reservationDestinationRepository;

    @Override
    public List<ReservationDestination> findAll() {
        return null;
    }

    @Override
    public Optional<ReservationDestination> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ReservationDestination save(ReservationDestination entity) throws Exception {
        return reservationDestinationRepository.save(entity);
    }

    @Override
    public ReservationDestination update(ReservationDestination entity) throws Exception {
        return null;
    }

    @Override
    public void delete(ReservationDestination entity) throws Exception {

    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public void validate(ReservationDestination entity) throws Exception {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<Object[]> getTopFiveMostSalesDestinations(Pageable pageable) {
        return reservationDestinationRepository.getTopFiveMostSalesDestinations(pageable);
    }

    @Override
    public List<Object[]> getReservationDestinationsByClient(Long id) {
        return reservationDestinationRepository.getReservationDestinationsByClient(id);
    }

    @Override
    public List<Object[]> getReservationDestinationsBySeller(Long id) {
        return reservationDestinationRepository.getReservationDestinationsBySeller(id);
    }

}
