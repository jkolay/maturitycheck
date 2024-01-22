package com.finance.app.unit.controller;

import com.finance.app.controller.MortgageCheckController;
import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.model.response.MortgageCheckResponse;
import com.finance.app.service.MortgageCheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MortgageCheckControllerTest {
    @Mock
    private MortgageCheckService mortgageCheckService;

    @InjectMocks
    private MortgageCheckController mortgageCheckController;

    @Test
    public void test_checkMortgage(){
    when(mortgageCheckService.checkMortgage(any(MortgageRequest.class))).thenReturn(new MortgageCheckResponse());
    MortgageRequest request= new MortgageRequest(10000.0,12,12000.0,12000.0);
    assertNotNull(mortgageCheckController.checkMortgage(request).getBody());
    }


    @Test
    public void test_retrieve_intyerest_Lists(){
        InterestResponse interestResponse= new InterestResponse(12.0,12, LocalDateTime.now());
        List<InterestResponse> interestResponses= new ArrayList<>();
        interestResponses.add(interestResponse);
        when(mortgageCheckService.getInterestList()).thenReturn(interestResponses);
        assertEquals(interestResponses,mortgageCheckController.getInterestList().getBody());
    }
}
