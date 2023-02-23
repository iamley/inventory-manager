package com.capitole.service.backend.inventory.manager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceValidateResponseDTO {

    private PriceOutputDTO body;
    private StatusDataDTO status;

}