package com.capitole.service.backend.inventory.manager.logic.impl;

import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.entities.PricesDTO;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.PriceValidateLogic;
import com.capitole.service.backend.inventory.manager.mapper.PricesMapper;
import com.capitole.service.backend.inventory.manager.model.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.capitole.service.backend.inventory.manager.enums.Status.BAD_REQUEST;
import static com.capitole.service.backend.inventory.manager.enums.Status.NOT_FOUND;

@Slf4j
@Component("PriceValidateLogic")
public class PriceValidateLogicImpl implements PriceValidateLogic {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceValidateLogicImpl.class);

    private static final String LABEL_ERROR = "Error generate {}";

    @Autowired
    private PriceRepository priceRepository;

    @Lazy
    @Autowired
    private PricesMapper mapper;

    @Override
    public PriceOutputDTO invoke(PriceValidateRequestDTO request) {

        validateRequest(request);
        LOGGER.info("Resquest send to service {}", request);

        final List<PricesDTO> reply = priceRepository
                .findPricesList(request.getBrandId(), request.getProductId());

        if (reply.size() > 0) {
            final var price = mapper.toPricesValidateResponseDto(reply.get(0));
            LOGGER.info("Response send to service {}", price);
            return price;
        } else {
            throw new BusinessCapabilityException(
                    NOT_FOUND.getCode(),
                    NOT_FOUND.getDescription());
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

    private long getBetween(LocalDateTime start, LocalDateTime end) {

        var startDate = start.toLocalDate();
        var endDate = end.toLocalDate();

        return ChronoUnit.MINUTES.between(startDate, endDate);
    }

}