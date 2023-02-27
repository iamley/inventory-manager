package com.capitole.service.backend.inventory.manager.logic.impl;

import com.capitole.service.backend.inventory.manager.adapter.impl.PriceRepositoryImpl;
import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.mapper.PricesMapper;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PricesModelDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.capitole.service.backend.inventory.manager.enums.Status.BAD_REQUEST;
import static com.capitole.service.backend.inventory.manager.enums.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceValidateLogicImplTest {

    @Mock
    private PriceValidateLogicImpl priceValidateLogic;

    @Mock
    private PriceRepository repository;

    @Mock
    private PriceRepositoryImpl priceRepository;

    @Mock
    private PricesMapper mapper;

    private static PriceValidateRequestDTO request = new PriceValidateRequestDTO();

    private static PricesDTO prices = new PricesDTO();

    private static PricesModelDTO response = new PricesModelDTO();

    @BeforeAll
    public static void setUp() {

        request.setBrandId(1);
        request.setProductId(35455);
        request.setDate(LocalDateTime.now());

        prices.setPrice(BigDecimal.valueOf(15.20));
        prices.setProductId(25648);
        prices.setBrandId(1);
        prices.setPriceList(1);
        prices.setStartDate(LocalDateTime.now());
        prices.setEndDate(LocalDateTime.now());
        prices.setMoney("EUR");
        prices.setPriority(1);

        response.setPrice(BigDecimal.valueOf(15.20));
        response.setProductId(25648);
        response.setBrandId(1);
        response.setStartDate(LocalDateTime.now());
        response.setEndDate(LocalDateTime.now());
    }

    @AfterAll
    public static void tearDown() {
        request = null;
        prices = null;
        response = null;
    }

    @Test
    public void handleABadRequest() {

        request.setBrandId(null);
        request.setProductId(null);

        try {
            priceValidateLogic.invoke(request);
        } catch (BusinessCapabilityException e) {
            Assertions.assertEquals(BAD_REQUEST.getCode(), e.getReturnCode());
        }

    }

    @Test
    public void handleASuccessRequestNotFound(){

        try {
            priceValidateLogic.invoke(request);
        } catch (BusinessCapabilityException e) {
            Assertions.assertEquals(NOT_FOUND.getCode(), e.getReturnCode());
        }

    }
}
