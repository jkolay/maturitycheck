package com.finance.app.service;

import com.finance.app.config.ConfigurationService;
import com.finance.app.exceptions.MaturityCheckException;
import com.finance.app.mapper.CommonMapper;
import com.finance.app.model.persistence.MortgageRate;
import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.model.response.MortgageCheckResponse;
import com.finance.app.repositories.MortgageRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.finance.app.util.EMICalculationUtils.emiCalculator;

@Service
@Slf4j(topic = "MortgageCheckServiceImpl")
public class MortgageCheckServiceImpl implements MortgageCheckService{

    private final MortgageRateRepository mortgageRateRepository;
    private final CommonMapper commonMapper;

    private final ConfigurationService configurationService;

    public MortgageCheckServiceImpl(MortgageRateRepository mortgageRateRepository, CommonMapper commonMapper, ConfigurationService configurationService) {
        this.mortgageRateRepository = mortgageRateRepository;
        this.commonMapper = commonMapper;
        this.configurationService = configurationService;
    }

    @Override
    public MortgageCheckResponse checkMortgage(MortgageRequest mortgageRequest) {
        MortgageCheckResponse mortgageCheckResponse= new MortgageCheckResponse(0.0,false);
        Double maxInterestRate=configurationService.getMaxInterest();
        if((mortgageRequest.getLoanValue()<=mortgageRequest.getIncome()*4) && (mortgageRequest.getHomaValue()>=mortgageRequest.getLoanValue())){
            MortgageRate mortgageRate= mortgageRateRepository.findFirstByMaturityPeriodOrderByLastUpdateDesc(mortgageRequest.getMaturityPeriod());
            if(null==mortgageRate){
               mortgageRate= mortgageRateRepository.findFirstByMaturityPeriodGreaterThanOrderByLastUpdateDesc(mortgageRequest.getMaturityPeriod());

            }
            //if maturity period value is higher than the maximum existing maturity period of db then the maximum interest rate will be attained from properties file.
            Double interestRate = (mortgageRate!=null)?mortgageRate.getInterestRate():maxInterestRate;
            log.info("Interest rate is "+interestRate);
            double mortgage = emiCalculator(mortgageRequest.getLoanValue(), interestRate, mortgageRequest.getMaturityPeriod());

            mortgageCheckResponse.setMortgagePossibility(true);
            mortgageCheckResponse.setMonthlyCost(mortgage);
            return mortgageCheckResponse;

        }
        else{
            throw new MaturityCheckException("The mortgage cannot be obtained due to unmet conditions.");
        }
    }

    @Override
    public List<InterestResponse> getInterestList() {
        LocalDateTime today= LocalDateTime.now();
        LocalDateTime firstDayOfMonth= today.withDayOfMonth(1);
        List<MortgageRate> mortgageRateList= mortgageRateRepository.findByLastUpdateGreaterThanOrderByLastUpdateDesc(firstDayOfMonth.minusDays(1));
        return commonMapper.mapMortgageRatesToInterestResponses(mortgageRateList);
    }



}
