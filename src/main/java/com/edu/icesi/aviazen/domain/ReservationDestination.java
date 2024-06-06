package com.edu.icesi.aviazen.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation_destinations")
public class ReservationDestination {

    public ReservationDestination(Integer id) {
        this.id = Long.valueOf(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "reservation_date", nullable = false)
    private Date reservation_date;

}
