package com.edu.icesi.aviazen.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private Long sellerId;
    private Long customerId;
    private LocalDateTime reservationDate;
    private String status;
    private BigDecimal total;

}