package com.capitole.service.backend.inventory.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PricesModelDTO {

    private Integer productId;
    private Integer brandId;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;

}