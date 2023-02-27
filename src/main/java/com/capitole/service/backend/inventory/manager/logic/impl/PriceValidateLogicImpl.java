package com.capitole.service.backend.inventory.manager.logic.impl;

import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.PriceValidateLogic;
import com.capitole.service.backend.inventory.manager.mapper.PricesMapper;
import com.capitole.service.backend.inventory.manager.dto.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PricesModelDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.capitole.service.backend.inventory.manager.enums.Status.BAD_REQUEST;
import static com.capitole.service.backend.inventory.manager.enums.Status.NOT_FOUND;

@Slf4j
@Service
public class PriceValidateLogicImpl implements PriceValidateLogic {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceValidateLogicImpl.class);

    @Autowired
    private PricesMapper mapper;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceOutputDTO invoke(PriceValidateRequestDTO request) {

        validateRequest(request);
        LOGGER.info("Resquest send to service {}", request);

        final List<PricesDTO> reply = priceRepository
                .findPricesList(request.getBrandId(), request.getProductId());

        if (reply.stream().count() > 0) {
            final var price = mapper.toPricesValidateList(reply);
            final var output = validateTimeframe(price, request);
            LOGGER.info("Response send to service {}", output);
            return output;
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

    private PriceOutputDTO validateTimeframe(List<PricesModelDTO> values, PriceValidateRequestDTO request) {

        LocalDateTime dateNow = request.getDate();
        List<PricesModelDTO> outputList = new ArrayList<>();

        for (PricesModelDTO value : values) {
            if (dateNow.isAfter(value.getStartDate()) && dateNow.isBefore(value.getEndDate())) {
                outputList.add(value);
            } else {
                throw new BusinessCapabilityException(
                        NOT_FOUND.getCode(),
                        NOT_FOUND.getDescription());
            }
        }

        PricesModelDTO value = outputList
                .stream()
                .max(Comparator.comparing(PricesModelDTO::getPriority))
                .orElseThrow(NoSuchElementException::new);

        return mapper.toPricesOutputDto(value);
    }

}