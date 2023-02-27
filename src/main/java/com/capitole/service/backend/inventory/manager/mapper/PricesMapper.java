package com.capitole.service.backend.inventory.manager.mapper;

import com.capitole.service.backend.inventory.manager.adapter.dto.PricesDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.model.PricesModelDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesMapper {

    @Mapping(source = "price", target = "price")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    PriceOutputDTO toPricesOutputDto(PricesModelDTO value);

    @InheritInverseConfiguration
    List<PricesModelDTO> toPricesValidateList(List<PricesDTO> values);

}