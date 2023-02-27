package com.capitole.service.backend.inventory.manager.dto;

import org.springframework.boot.logging.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggerDataDTO {

    @NonNull
    private LogLevel level;
    private String message;
    private String path;
    private String code;
    private String codeMessage;
    private Long executeTime;
    private String headers;
    private String requestId;

}
