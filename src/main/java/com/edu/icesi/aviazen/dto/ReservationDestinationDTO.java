package com.edu.icesi.aviazen.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDestinationDTO {

    private Long id;
    private Long reservation_id;
    private Long destination_id;
    private BigDecimal total;

}
