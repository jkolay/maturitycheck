package com.finance.app.model.request;

import com.finance.app.config.APIValidationConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MortgageRequest {
    @NotNull(message = APIValidationConfig.INVALID_INCOME_AMOUNT)
    @Positive(message = APIValidationConfig.INVALID_INCOME_AMOUNT)
    @Schema(description = "The annual income", example = "60000")
    private double income;

    @NotNull(message = APIValidationConfig.INVALID_NUMBER_OF_MATURITY)
    @Positive(message = APIValidationConfig.INVALID_NUMBER_OF_MATURITY)
    @Schema(description = "The maturity period of the loan/mortgage. please enter the value in years", example = "10")
    private int maturityPeriod;

    @NotNull(message = APIValidationConfig.INVALID_LOAN_AMOUNT)
    @Positive(message = APIValidationConfig.INVALID_LOAN_AMOUNT)
    @Schema(description = "The loan amount or mortgage amount", example = "200000")
    private double loanValue;

    @NotNull(message = APIValidationConfig.INVALID_HOME_VALUE)
    @Positive(message = APIValidationConfig.INVALID_HOME_VALUE)
    @Schema(description = "The home value for which loan needs to be taken", example = "210000")
    private double homaValue;

}
