package com.capitole.service.backend.inventory.manager.mapper;

import com.capitole.service.backend.inventory.manager.entities.PricesDTO;
import com.capitole.service.backend.inventory.manager.model.PriceOutputDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesMapper {

    @Mapping(source = "price", target = "price")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    PriceOutputDTO toPricesValidateResponsesssDto(PricesDTO value);
    List<PriceOutputDTO> toPricesValidateResponsessDto(List<PricesDTO> value);

    @InheritInverseConfiguration
    List<PriceOutputDTO> toPricesValidateResponsesDto(List<PricesDTO> values);

    default List<PriceOutputDTO> toPricesValidateResponseDto(List<PricesDTO> values) {

        List<PriceOutputDTO> priceOutputDTOS = new ArrayList<>();
        for(PricesDTO value : values) {

            PriceOutputDTO priceOutputDTO = new PriceOutputDTO();
            priceOutputDTO.setPrice(value.getPrice());
            priceOutputDTO.setBrandId(value.getBrandId());
            priceOutputDTO.setProductId(value.getProductId());
            priceOutputDTO.setStartDate(value.getStartDate());
            priceOutputDTO.setEndDate(value.getEndDate());

            priceOutputDTOS.add(priceOutputDTO);
        }

        return priceOutputDTOS;
    }


}
