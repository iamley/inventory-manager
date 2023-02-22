package com.capitole.service.backend.inventory.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    BAD_REQUEST("0001", "Bad request, missing input data in the request."),
    FATAL_ERROR("0002", "Unexpected error in service execution.");

    private final String code;
    private final String description;
}
