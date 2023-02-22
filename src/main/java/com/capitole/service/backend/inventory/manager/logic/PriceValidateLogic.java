package com.capitole.service.backend.inventory.manager.logic;

import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateResponseDTO;

public interface PriceValidateLogic {

    PriceValidateResponseDTO invoke(PriceValidateRequestDTO request);

}