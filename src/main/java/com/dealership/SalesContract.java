package com.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesContract extends Contract {
    private BigDecimal salesTaxAmount;
    private BigDecimal recordingFee;
    private BigDecimal processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;

        BigDecimal price = vehicleSold.getPrice();
        this.salesTaxAmount = price.multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP);
        this.recordingFee = new BigDecimal("100.00");

        if (price.compareTo(new BigDecimal("10000.00")) < 0) {
            this.processingFee = new BigDecimal("295.00");
        } else {
            this.processingFee = new BigDecimal("495.00");
        }
    }

    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
    @Override
    public BigDecimal getTotalPrice() {
        return getVehicleSold().getPrice()
                .add(salesTaxAmount)
                .add(recordingFee)
                .add(processingFee)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!isFinanced) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        double totalPrice = getTotalPrice().doubleValue();
        double annualRate;
        int months;

        if (getVehicleSold().getPrice().compareTo(new BigDecimal("10000.00")) >= 0) {
            annualRate = 0.0425;
            months = 48;
        } else {
            annualRate = 0.0525;
            months = 24;
        }

        double monthlyRate = annualRate / 12.0;
        double payment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

        return BigDecimal.valueOf(payment).setScale(2, RoundingMode.HALF_UP);
    }
}