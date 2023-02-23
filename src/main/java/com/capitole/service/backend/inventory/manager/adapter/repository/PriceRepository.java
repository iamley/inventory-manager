package com.capitole.service.backend.inventory.manager.adapter.repository;

import com.capitole.service.backend.inventory.manager.entities.PricesDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceRepository extends CrudRepository<PricesDTO, PricesDTO> {

    @Query(value = "SELECT * FROM prices t WHERE t.brand_id= :brand and t.product_id= :product",
            nativeQuery = true)
    List<PricesDTO> findPricesList(
            @Param("brand") Integer brand,
            @Param("product") Integer product);

}