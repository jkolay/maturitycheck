package com.finance.app.integration.controller;

import com.finance.app.config.ConfigurationService;
import com.finance.app.mapper.CommonMapper;
import com.finance.app.model.persistence.MortgageRate;
import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.repositories.MortgageRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MortgageCheckControllerIntegrationTest extends AbstractControllerIntegrationTest{
    @Autowired
    private  MortgageRateRepository mortgageRateRepository;
    @Autowired
    private  CommonMapper commonMapper;

    @Autowired
    private  ConfigurationService configurationService;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    public void before() {

        mortgageRateRepository.deleteAll();
    }


    @Test
    public void test_checkMortgage_successfully() throws Exception {
        MortgageRequest request= new MortgageRequest(10000.0,12,12000.0,12000.0);


        MvcResult result = performPost("/api/mortgage-check", request)
                .andExpect(status().isOk())
                .andReturn();
        Boolean check= readByJsonPath(result, "$.mortgageFeasibility");

        assertTrue(check);

    }
    @Test
    public void test_list_interests_successfully() throws Exception {

        MortgageRate mortgageRate= new MortgageRate(1,10.0,12, LocalDateTime.now());
        mortgageRateRepository.save(mortgageRate);

        MvcResult result = performGet("/api/interest-rate")
                .andExpect(status().isOk())
                .andReturn();

        List<InterestResponse> interestResponses = getListFromMvcResult(result, InterestResponse.class);
        assertEquals(10.0,interestResponses.get(0).getInterestRate());

    }

}
