package com.capitole.service.backend.inventory.manager.command;

import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.model.CapitoleResponseEntity;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;

public interface PriceValidateCommand {

    CapitoleResponseEntity<PriceValidateResponseDTO> priceValidate(PriceValidateRequestDTO request) throws BusinessCapabilityException;

    CapitoleResponseEntity<PriceValidateResponseDTO> priceValidateFallback(PriceValidateRequestDTO request, Throwable throwable) throws BusinessCapabilityException;

}