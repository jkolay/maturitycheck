package com.finance.app.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
@ConfigurationProperties
public class ConfigurationService {
    @Value("${api-max.interest.rate:}")
    private Double maxInterest;

}
