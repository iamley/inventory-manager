package com.capitole.service.backend.inventory.manager.command.impl;

import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import com.capitole.service.backend.inventory.manager.dto.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import com.capitole.service.backend.inventory.manager.logic.PriceValidateLogic;
import com.capitole.service.backend.inventory.manager.mapper.PricesMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.capitole.service.backend.inventory.manager.enums.Status.NOT_FOUND;
import static com.capitole.service.backend.inventory.manager.enums.Status.SUCCESS;
import static com.capitole.service.backend.inventory.manager.enums.Status.FATAL_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceValidateCommandImplTest {

    @SpyBean
    private PriceValidateCommand command;

    @MockBean
    private PriceValidateLogic logic;

    @MockBean
    private PriceRepository repository;

    @Autowired
    private PricesMapper mapper;

    @Captor
    private ArgumentCaptor<Throwable> exceptionCaptor;

    private static PriceValidateRequestDTO request = new PriceValidateRequestDTO();

    private static PriceOutputDTO response = new PriceOutputDTO();

    @BeforeAll
    public static void setUp() {
        request.setBrandId(1);
        request.setProductId(35455);
        request.setDate(LocalDateTime.now());

        response.setPrice(BigDecimal.valueOf(15.20));
        response.setProductId(25648);
        response.setBrandId(1);
        response.setStartDate(LocalDateTime.now());
        response.setEndDate(LocalDateTime.now());
    }

    @AfterAll
    public static void tearDown() {
        request = null;
        response = null;
    }

    @Test
    void handleComplete() {

        Mockito.when(logic.invoke(any())).thenReturn(response);

        final var response = command.priceValidate(request);

        assertNotNull(response);
        assertEquals(SUCCESS.getCode(), response.getStatus().getCode());
    }

    @Test
    void handleCompleteWithException() {

        Mockito.when(logic.invoke(any())).thenThrow(RuntimeException.class);

        final var response = command.priceValidate(request);

        assertNotNull(response);
        assertEquals(FATAL_ERROR.getCode(), response.getStatus().getCode());
    }

}