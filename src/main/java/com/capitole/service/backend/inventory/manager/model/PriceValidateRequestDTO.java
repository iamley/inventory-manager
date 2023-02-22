package com.capitole.service.backend.inventory.manager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceValidateRequestDTO {

    private LocalDateTime date;
    private Integer productId;
    private Integer brandId;

}