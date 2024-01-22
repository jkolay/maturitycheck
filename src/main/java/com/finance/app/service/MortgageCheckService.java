package com.finance.app.service;

import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.model.response.MortgageCheckResponse;

import java.util.List;

public interface MortgageCheckService {
    public MortgageCheckResponse checkMortgage(MortgageRequest mortgageRequest) ;

    public List<InterestResponse> getInterestList();
}
