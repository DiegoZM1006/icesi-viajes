package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.dto.ReservationDTO;
import com.edu.icesi.aviazen.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long aLong) {
        return reservationRepository.findById(aLong);
    }

    @Override
    public Reservation save(Reservation entity) throws Exception {
        return reservationRepository.save(entity);
    }

    @Override
    public Reservation update(Reservation entity) throws Exception {
        return null;
    }

    @Override
    public void delete(Reservation entity) throws Exception {

    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public void validate(Reservation entity) throws Exception {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public Optional<ReservationDTO> findById(Integer id) {
        return reservationRepository.findById(id);
    }
}
