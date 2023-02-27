package com.capitole.service.backend.inventory.manager.controller;

import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.capitole.service.backend.inventory.manager.enums.Status.BAD_REQUEST;
import static com.capitole.service.backend.inventory.manager.enums.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private PriceController controller;

    @Mock
    private PriceValidateCommand command;

    private PriceValidateRequestDTO request;

    @BeforeEach
    public void setUp() throws Exception {
        request = new PriceValidateRequestDTO();
        request.setProductId(1);
        request.setBrandId(1);
        request.setDate(LocalDateTime.now());
    }

    @Test
    void getPricesListSuccess() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(post("/price/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultContent = mvcResult.getResponse().getContentAsString();

        var reply = objectMapper.readValue(resultContent, PriceValidateResponseDTO.class);
        assertNull(reply.getStatus().getCode());
    }

    @Test
    void getPricesListBadRequest() throws Exception {

        request.setBrandId(null);

        MvcResult mvcResult = mockMvc
                .perform(post("/price/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultContent = mvcResult.getResponse().getContentAsString();

        var reply = objectMapper.readValue(resultContent, PriceValidateResponseDTO.class);
        assertEquals(BAD_REQUEST.getCode(), reply.getStatus().getCode());
    }


}
