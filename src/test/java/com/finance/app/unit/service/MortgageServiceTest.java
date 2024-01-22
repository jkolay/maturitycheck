package com.finance.app.unit.service;

import com.finance.app.config.ConfigurationService;
import com.finance.app.exceptions.MaturityCheckException;
import com.finance.app.mapper.CommonMapper;
import com.finance.app.model.persistence.MortgageRate;
import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.repositories.MortgageRateRepository;
import com.finance.app.service.MortgageCheckServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MortgageServiceTest {
    @Mock
    private  MortgageRateRepository mortgageRateRepository;
    @Mock
    private  CommonMapper commonMapper;

    @Mock
    private  ConfigurationService configurationService;

    @InjectMocks
    private MortgageCheckServiceImpl mortgageCheckService;

    @Test
    public void test_checkMortgage_withFeasibility_true(){
        when(configurationService.getMaxInterest()).thenReturn(10.0);
        MortgageRate mortgageRate= new MortgageRate(1,12.0,12, LocalDateTime.now());
        when(mortgageRateRepository.findFirstByMaturityPeriodOrderByLastUpdateDesc(anyInt())).thenReturn(mortgageRate);
        assertTrue(mortgageCheckService.checkMortgage(new MortgageRequest(10000.0,12,20000.0,21000.0)).isMortgageFeasibility());


}

    @Test
    public void test_checkMortgage_withFeasibility_false(){
        when(configurationService.getMaxInterest()).thenReturn(10.0);
        MortgageRate mortgageRate= new MortgageRate(1,12.0,12, LocalDateTime.now());
       assertThrows(MaturityCheckException.class,()->mortgageCheckService.checkMortgage(new MortgageRequest(10000.0,12,20000.0,1000.0)));


    }

    @Test
    public void test_getInterestList(){
        MortgageRate mortgageRate= new MortgageRate(1,1.0,1,LocalDateTime.now());
        List<MortgageRate> mortgageRateList= new ArrayList<>();
        mortgageRateList.add(mortgageRate);
        InterestResponse interestResponse= new InterestResponse(12.0,12, LocalDateTime.now());
        when(mortgageRateRepository.findByLastUpdateGreaterThanOrderByLastUpdateDesc(any(LocalDateTime.class))).thenReturn(mortgageRateList);
        when(commonMapper.mapMortgageRatesToInterestResponses(any(List.class))).thenReturn(mortgageRateList);
        assertNotNull(mortgageCheckService.getInterestList());

    }
}
