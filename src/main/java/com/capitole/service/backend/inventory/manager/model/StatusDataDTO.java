package com.capitole.service.backend.inventory.manager.model;

import com.capitole.service.backend.inventory.manager.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusDataDTO {

    protected String code;
    protected String description;
    private HttpStatus httpStatus;

}