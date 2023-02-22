package com.capitole.service.backend.inventory.manager.mapper;

import com.capitole.service.backend.inventory.manager.entities.PricesDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesMapper {

    @Mapping(source = "date", target = "cutOffDate")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    PricesDTO toPricesDto(PriceValidateRequestDTO value);

    @Mapping(source = "price", target = "price")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    PriceValidateResponseDTO toPricesValidateResponseDto(PricesDTO value);


}
