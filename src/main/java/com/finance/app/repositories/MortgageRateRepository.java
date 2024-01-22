package com.finance.app.repositories;

import com.finance.app.model.persistence.MortgageRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MortgageRateRepository  extends JpaRepository<MortgageRate, Integer> {

    MortgageRate findFirstByMaturityPeriodOrderByLastUpdateDesc(Integer maturityPeriod);

    MortgageRate findFirstByMaturityPeriodGreaterThanOrderByLastUpdateDesc(Integer maturityPeriod);

    List<MortgageRate> findByLastUpdateGreaterThanOrderByLastUpdateDesc(LocalDateTime firstDayOfMonth);
}
