package com.capitole.service.backend.inventory.manager.controller;

import com.capitole.service.backend.inventory.manager.command.PriceValidateCommand;
import com.capitole.service.backend.inventory.manager.model.CapitoleResponseEntity;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("${api.basepath}/search")
@Api(tags = "Controlador de operaciones de consulta de precios")
public class PriceController {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    private PriceValidateCommand priceValidateCommand;

    @PostMapping(value = "/price/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consulta de precios", httpMethod = "POST", nickname = "Consulta Precios")
    public CapitoleResponseEntity<PriceValidateResponseDTO> priceValidate(PriceValidateRequestDTO request) {

        LOGGER.debug("Inicio de la consulta de precios");

        final CapitoleResponseEntity<PriceValidateResponseDTO> response = priceValidateCommand.priceValidate(request);

        LOGGER.debug("Fin e la consulta de precios");

        return response;
    }
}