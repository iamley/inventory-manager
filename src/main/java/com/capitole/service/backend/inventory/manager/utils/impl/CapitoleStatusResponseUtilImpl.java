package com.capitole.service.backend.inventory.manager.utils.impl;

import com.capitole.service.backend.inventory.manager.enums.Status;
import com.capitole.service.backend.inventory.manager.model.StatusDataDTO;
import com.capitole.service.backend.inventory.manager.utils.CapitoleStatusResponseUtil;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.capitole.service.backend.inventory.manager.enums.Status.CIRCUIT_BREAKER;
import static com.capitole.service.backend.inventory.manager.enums.Status.REQUEST_FAILED;

@Component("CapitoleStatusResponseUtil")
public class CapitoleStatusResponseUtilImpl implements CapitoleStatusResponseUtil {

    public StatusDataDTO getStatusResponse(final Status status) {
        return StatusDataDTO.builder().code(status.getCode()).description(status.getDescription())
                .httpStatus(status.getHttpStatus()).build();
    }

    public StatusDataDTO getStatusResponse(final Status status, final Throwable throwable) {

        if (throwable instanceof CallNotPermittedException) {
            return getStatusResponse(CIRCUIT_BREAKER);
        }

        if (throwable instanceof MethodArgumentTypeMismatchException) {
            return getStatusResponse(REQUEST_FAILED);
        }

        if (throwable instanceof MethodArgumentNotValidException) {
            return getStatusResponse(REQUEST_FAILED);
        }

        if (throwable instanceof HttpMessageNotReadableException) {
            return getStatusResponse(REQUEST_FAILED);
        }

        if (throwable instanceof ServletRequestBindingException) {
            return getStatusResponse(REQUEST_FAILED);
        }

        if (throwable instanceof MissingServletRequestParameterException) {
            return getStatusResponse(REQUEST_FAILED);
        }

        return getStatusResponse(status);
    }

}