package com.capitole.service.backend.inventory.manager.command.impl;

import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.PriceValidateLogic;
import com.capitole.service.backend.inventory.manager.model.CapitoleResponseEntity;
import com.capitole.service.backend.inventory.manager.model.LoggerDataDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;
import com.capitole.service.backend.inventory.manager.model.StatusDataDTO;
import com.capitole.service.backend.inventory.manager.utils.CapitoleStatusResponseUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;

import org.springframework.stereotype.Component;

import static com.capitole.service.backend.inventory.manager.enums.Status.FALLBACK;
import static com.capitole.service.backend.inventory.manager.enums.Status.FATAL_ERROR;
import static com.capitole.service.backend.inventory.manager.enums.Status.SUCCESS;

@Slf4j
@Component("PriceValidateCommand")
public class PriceValidateCommandImpl implements PriceValidateCommand {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceValidateCommandImpl.class);

    private static final String EXCEPTION = "Request: {}\nException: {}";

    @Autowired
    private PriceValidateLogic priceValidateLogic;

    @Autowired
    private CapitoleStatusResponseUtil statusResponseUtil;

    @Override
    @CircuitBreaker(name = "priceValidate", fallbackMethod = "priceValidateFallback")
    public CapitoleResponseEntity<PriceValidateResponseDTO> priceValidate(final PriceValidateRequestDTO request) throws BusinessCapabilityException {

        LOGGER.debug("Inicia la ejecucion del comando para obtener la tarifa final del producto");

        final var response = new CapitoleResponseEntity<PriceValidateResponseDTO>();
        final var data = new PriceValidateResponseDTO();
        var status = new StatusDataDTO();

        try {
            data.setBody(priceValidateLogic.invoke(request));
            response.setBody(data);

            status.setCode(SUCCESS.getCode());
            status.setDescription(SUCCESS.getDescription());

        } catch (BusinessCapabilityException e) {
            LOGGER.error("BusinessCapabilityException", e);
            status.setCode(e.getReturnCode());
            status.setDescription(e.getReturnCodeDesc());
        }  catch (Exception e){
            LOGGER.error(EXCEPTION, request, e.toString());
            status.setCode(FATAL_ERROR.getCode());
            status.setDescription(FATAL_ERROR.getDescription());
        }
        data.setStatus(status);
        response.setBody(data);
        response.setStatus(status);
        return response;
    }

    @Override
    public CapitoleResponseEntity<PriceValidateResponseDTO> priceValidateFallback(final PriceValidateRequestDTO request, Throwable throwable) throws BusinessCapabilityException {

        final var dataLog = new LoggerDataDTO();
        dataLog.setMessage(throwable.toString());
        dataLog.setCode(FALLBACK.getCode());
        dataLog.setCodeMessage(FALLBACK.getDescription());
        dataLog.setLevel(LogLevel.ERROR);
        LOGGER.error("Fallo el comando para obtener la tarifa final del producto", throwable);

        final var response = new CapitoleResponseEntity<PriceValidateResponseDTO>();
        response.setStatus(statusResponseUtil.getStatusResponse(FALLBACK, throwable));

        return response;
    }

}