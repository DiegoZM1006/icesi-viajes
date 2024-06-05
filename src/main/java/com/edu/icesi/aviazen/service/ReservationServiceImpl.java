package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.Reservation;
import com.edu.icesi.aviazen.dto.ReservationDTO;
import com.edu.icesi.aviazen.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
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
        return reservationRepository.count();
    }

    @Override
    public Optional<ReservationDTO> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Long getTotalPriceReservations() {
        return reservationRepository.getTotalPriceReservations();
    }

    @Override
    public List<Object> findWeeklySales() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        System.out.println(startOfWeek);
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
        System.out.println(endOfWeek);

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atTime(LocalTime.MAX);

        return reservationRepository.findWeeklySales(startOfWeekDateTime, endOfWeekDateTime);
    }

    @Override
    public List<Object> findTopTenBetterSeller() {
        return reservationRepository.findTopTenBetterSeller();
    }

    @Override
    public List<Object[]> findLatestSales() {
        return reservationRepository.findLatestSales();
    }

}
