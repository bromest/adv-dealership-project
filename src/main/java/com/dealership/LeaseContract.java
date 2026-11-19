package com.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        BigDecimal price = vehicleSold.getPrice();
        this.expectedEndingValue = price.multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_UP);
        this.leaseFee = price.multiply(new BigDecimal("0.07")).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }
    @Override
    public BigDecimal getTotalPrice() {
        return getVehicleSold().getPrice()
                .subtract(expectedEndingValue)
                .add(leaseFee)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        double totalPrice = getTotalPrice().doubleValue();
        double monthlyRate = 0.04 / 12.0;
        int months = 36;

        double payment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        return BigDecimal.valueOf(payment).setScale(2, RoundingMode.HALF_UP);
    }
}
