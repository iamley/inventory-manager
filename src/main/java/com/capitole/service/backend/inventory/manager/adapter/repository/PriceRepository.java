package com.capitole.service.backend.inventory.manager.adapter.repository;

import com.capitole.service.backend.inventory.manager.entities.PricesDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends CrudRepository<PricesDTO, PricesDTO> {

}