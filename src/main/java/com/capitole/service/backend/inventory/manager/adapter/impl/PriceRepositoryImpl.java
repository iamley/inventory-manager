package com.capitole.service.backend.inventory.manager.adapter.impl;

import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.adapter.repository.PriceRepository;
import com.capitole.service.backend.inventory.manager.exception.BusinessCapabilityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.capitole.service.backend.inventory.manager.enums.Status.DATABASE_ERROR;

@Repository
public class PriceRepositoryImpl {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceRepositoryImpl.class);

    @Lazy
    @Autowired
    private PriceRepository repository;

    public List<PricesDTO> findProductsByBrand(Integer brand, Integer product) {
        try {
            return repository.findPricesList(brand, product);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessCapabilityException(DATABASE_ERROR.getCode(), DATABASE_ERROR.getDescription());
        }
    }
}
