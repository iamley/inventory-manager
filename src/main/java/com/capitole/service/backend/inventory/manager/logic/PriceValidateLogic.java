package com.capitole.service.backend.inventory.manager.logic;

import com.capitole.service.backend.inventory.manager.model.PriceOutputDTO;
import com.capitole.service.backend.inventory.manager.model.PriceValidateRequestDTO;

public interface PriceValidateLogic {

    PriceOutputDTO invoke(PriceValidateRequestDTO request);

}