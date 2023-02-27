package com.capitole.service.backend.inventory.manager.logic;

import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.impl.PriceValidateLogicImpl;
import com.capitole.service.backend.inventory.manager.mapper.PricesMapper;
import com.capitole.service.backend.inventory.manager.dto.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.capitole.service.backend.inventory.manager.enums.Status.NOT_FOUND;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceValidateLogicImplTest {

    @InjectMocks
    PriceValidateLogicImpl priceValidateLogic;

    @Mock
    PriceRepository repository;

    @Mock
    PricesMapper mapper;

    private static PriceValidateRequestDTO request = new PriceValidateRequestDTO();

    private static PricesDTO prices = new PricesDTO();

    private static PriceOutputDTO response = new PriceOutputDTO();

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
    public void handleASuccessRequestNotFound(){

        List<PricesDTO> values = new ArrayList<>();
        values.add(prices);

        List<PriceOutputDTO> listPrices = new ArrayList<>();
        listPrices.add(response);

        try {
            priceValidateLogic.invoke(request);
        } catch (BusinessCapabilityException e) {
            Assertions.assertEquals(NOT_FOUND.getCode(), e.getReturnCode());
        }

    }
}
