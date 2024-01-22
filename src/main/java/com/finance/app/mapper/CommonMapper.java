package com.finance.app.mapper;

import com.finance.app.model.persistence.MortgageRate;
import com.finance.app.model.response.InterestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CommonMapper {
    InterestResponse mapMortgageRateToInterestResponse(MortgageRate mortgageRate);
    List<InterestResponse> mapMortgageRatesToInterestResponses(List<MortgageRate> interestList);
}
