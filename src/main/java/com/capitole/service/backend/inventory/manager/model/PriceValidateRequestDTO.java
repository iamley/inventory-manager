package com.capitole.service.backend.inventory.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PriceValidateRequestDTO {

    private LocalDateTime date;
    private Integer productId;
    private Integer brandId;

}