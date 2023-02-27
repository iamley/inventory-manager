package com.capitole.service.backend.inventory.manager.logic;

import com.capitole.service.backend.inventory.manager.dto.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.dto.PriceValidateRequestDTO;

public interface PriceValidateLogic {

    PriceOutputDTO invoke(PriceValidateRequestDTO request);

}