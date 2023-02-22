package com.capitole.service.backend.inventory.manager.utils;

import com.capitole.service.backend.inventory.manager.enums.Status;
import com.capitole.service.backend.inventory.manager.model.StatusDataDTO;

public interface CapitoleStatusResponseUtil {

    StatusDataDTO getStatusResponse(final Status status);
    StatusDataDTO getStatusResponse(final Status error, final Exception e);
    StatusDataDTO getStatusResponse(final Throwable throwable);
    StatusDataDTO getStatusResponse(final Status error, final Throwable throwable);

}