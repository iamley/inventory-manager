package com.capitole.service.backend.inventory.manager.logic.impl;

import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.entities.Prices;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.PriceValidateLogic;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.loading.MLetContent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.capitole.service.backend.inventory.manager.enums.Status.BAD_REQUEST;
import static com.capitole.service.backend.inventory.manager.enums.Status.FATAL_ERROR;

@Slf4j
@Component("PriceValidateLogicImpl")
public class PriceValidateLogicImpl implements PriceValidateLogic {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceValidateLogicImpl.class);

    private static final String LABEL_ERROR = "Error generate {}";

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceValidateResponseDTO invoke(PriceValidateRequestDTO request) {

        try {
            validateRequest(request);
            LOGGER.info("Resquest send to service {}", request);

            final Optional<Prices> reply = priceRepository.findAll();

            LOGGER.info("Response send to service {}", reply);

        } catch (BusinessCapabilityException e) {
            LOGGER.error(LABEL_ERROR, e);
            throw new BusinessCapabilityException(e.getReturnCode(), e.getReturnCodeDesc());
        } catch (Exception e) {
            LOGGER.error(LABEL_ERROR, e);
            throw new BusinessCapabilityException(FATAL_ERROR.getCode(), FATAL_ERROR.getDescription());
        }
    }

    private void validateRequest(PriceValidateRequestDTO request) throws BusinessCapabilityException {

        List<Object> requestList = new ArrayList<>();
        requestList.add(request.getDate());
        requestList.add(request.getBrandId());
        requestList.add(request.getProductId());

        for(Object value : requestList) {
            if(value == null) {
                throw new BusinessCapabilityException(
                        BAD_REQUEST.getCode(),
                        BAD_REQUEST.getDescription());
            }

            if(value instanceof String && ((String) value).isBlank()) {
                throw new BusinessCapabilityException(
                        BAD_REQUEST.getCode(),
                        BAD_REQUEST.getDescription());
            }
        }
    }

    private long getMonthsBetween(LocalDateTime start, LocalDateTime end) {

        var startDate = start.toLocalDate();
        var endDate = end.toLocalDate();

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

}