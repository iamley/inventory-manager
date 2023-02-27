package com.capitole.service.backend.inventory.manager.command;

import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.dto.CapitoleResponseEntity;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateResponseDTO;

public interface PriceValidateCommand {

    CapitoleResponseEntity<PriceValidateResponseDTO> priceValidate(PriceValidateRequestDTO request) throws BusinessCapabilityException;

    CapitoleResponseEntity<PriceValidateResponseDTO> priceValidateFallback(PriceValidateRequestDTO request, Throwable throwable) throws BusinessCapabilityException;

}