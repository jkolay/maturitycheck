package com.finance.app.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MortgageCheckResponse {
    @Schema(description = "The monthly cost of the mortgage", example = "1000.00")
    private Double monthlyCost;
    @Schema(description = "The mortgage check ", example = "true")
    private boolean mortgagePossibility;
}
