package com.finance.app.controller;

import com.finance.app.model.request.MortgageRequest;
import com.finance.app.model.response.InterestResponse;
import com.finance.app.model.response.MortgageCheckResponse;
import com.finance.app.service.MortgageCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j(topic = "MortgageCheckController")
public class MortgageCheckController {
    private final MortgageCheckService mortgageCheckService;


    public MortgageCheckController(MortgageCheckService mortgageCheckService) {
        this.mortgageCheckService = mortgageCheckService;
    }

    /**
     *
     * @param mortgageRequest
     * @return
     */
    @Operation(description = "This api checks if mortgage can be taken and it returns the monthly cost if check is successful")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mortgage can be taken successfully"),
            @ApiResponse(responseCode = "404", description = "Mortgage can not be taken successfully")
    })
    @RequestMapping(method = RequestMethod.POST,path = "/mortgage-check")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MortgageCheckResponse> checkMortgage(@RequestBody MortgageRequest mortgageRequest) {
        log.info("checking Mortgage");
        return ResponseEntity.status(HttpStatus.OK).body(mortgageCheckService.checkMortgage(mortgageRequest));
    }

    /**
     *
     * @return
     */
    @Operation(description = "Retrieve current interest rates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interest rates retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Interest rates  can not be retrieved successfully")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/interest-rate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InterestResponse>> getInterestList() {
        log.info("Getting the ingredients");
        return ResponseEntity.status(HttpStatus.OK).body(mortgageCheckService.getInterestList());
    }
}
