package com.finance.app.model.request;

import com.finance.app.config.APPValidationConfig;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @NotNull(message = APPValidationConfig.INVALID_INCOME_AMOUNT)
    @Positive(message = APPValidationConfig.INVALID_INCOME_AMOUNT)
    @Schema(description = "The annual income", example = "60000")
    private Double income;

    @NotNull(message = APPValidationConfig.INVALID_NUMBER_OF_MATURITY)
    @Positive(message = APPValidationConfig.INVALID_NUMBER_OF_MATURITY)
    @Schema(description = "The maturity period of the loan/mortgage. please enter the value in years", example = "10")
    private Integer maturityPeriod;

    @NotNull(message = APPValidationConfig.INVALID_LOAN_AMOUNT)
    @Positive(message = APPValidationConfig.INVALID_LOAN_AMOUNT)
    @Schema(description = "The loan amount or mortgage amount", example = "200000")
    private Double loanValue;

    @NotNull(message = APPValidationConfig.INVALID_HOME_VALUE)
    @Positive(message = APPValidationConfig.INVALID_HOME_VALUE)
    @Schema(description = "The home value for which loan needs to be taken", example = "210000")
    private Double homaValue;

}
