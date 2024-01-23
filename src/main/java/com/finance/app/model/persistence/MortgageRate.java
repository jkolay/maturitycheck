package com.finance.app.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name = "MORTAGE_RATE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MortgageRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Integer id;

    @Column(nullable = false, name = "interest_rate")
    private double interestRate;


    @Column(nullable = false, name = "maturity_period")
    private int maturityPeriod;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name="last_update")
    private LocalDateTime lastUpdate;
}
