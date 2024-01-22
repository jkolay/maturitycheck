package com.finance.app.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InterestResponse {
    @Schema(description = "The interest rate in percentage", example = "10")
    private Double interestRate;
    @Schema(description = "The maturity period in years", example = "10")
    private Integer maturityPeriod;
    @Schema(description = "The lase updated time stamp ", example = "2024-01-22T13:42:00.813Z")
    private LocalDateTime lastUpdate;
}
