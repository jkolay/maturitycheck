package com.finance.app.util;

public class EMICalculationUtils {
    public static Double emiCalculator(Double p, Double r, Integer t)
    {
        //calculation of emi
        Double emi;
        r = r / (12 * 100); // one month interest
        t = t * 12; // one month period
        emi = (p * r * (Double) Math.pow(1 + r, t)) / (Double)(Math.pow(1 + r, t) - 1);


       //rounding off emi with 2 decimal places
        long factor = (long) Math.pow(10, 2);
        emi = emi * factor;
        long tmp = Math.round(emi);
        return (double) tmp / factor;
    }
}
