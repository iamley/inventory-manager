package com.capitole.service.backend.inventory.manager.command.impl;

import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.model.CapitoleResponseEntity;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.stereotype.Service;

@Service("PriceValidateCommand")
@RefreshScope
@Slf4j
public class PriceValidateCommandImpl implements PriceValidateCommand {

    @Override
    @CircuitBreaker(name = "priceValidate", fallbackMethod = "priceValidateFallback")
    public CapitoleResponseEntity<PriceValidateResponseDTO> priceValidate(final PriceValidateRequestDTO request) throws BusinessCapabilityException {

        log.debug("Inicia la ejecucion del comando para obtener la tarifa final del producto");

        final CapitoleResponseEntity<PriceValidateResponseDTO> response = new CapitoleResponseEntity<>();

        try {
            //operationalInstructionsAdapter.saveApprovalInstruction(request);
            //response.setStatus(new StatusDTO(ServiceResponseCode.SUCCESS));

        } catch (BusinessCapabilityException e) {
            log.error("BusinessCapabilityException", e);
            //response.setStatus(responseUtils.getStatusFromBgpRestBusinessException(e));
        }

        return response;
    }

    @Override
    public CapitoleResponseEntity<PriceValidateResponseDTO> priceValidateFallback(final PriceValidateRequestDTO request, Throwable throwable) throws BusinessCapabilityException {
        log.error("Fallo el comando para obtener la tarifa final del producto", throwable);

        final CapitoleResponseEntity<PriceValidateResponseDTO> response = new CapitoleResponseEntity<>();

        //response.setStatus(responseUtils.genericFallBack(request, throwable).getStatus());

        return response;
    }

}