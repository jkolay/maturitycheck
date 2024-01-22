package com.finance.app.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorModel {
    private String description;
    private String code;
    private ErrorSeverityLevelCodeType severityLevel;
    private UUID traceId;

}
