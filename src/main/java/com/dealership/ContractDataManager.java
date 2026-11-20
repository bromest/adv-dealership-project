package com.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String FILE_PATH = "contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            Vehicle v = contract.getVehicleSold();

            String baseInfo = String.format("%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice()
            );

            if (contract instanceof SalesContract sale) {
                String financedStr = sale.isFinanced() ? "YES" : "NO";
                String line = String.format("SALE|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        baseInfo,
                        sale.getSalesTaxAmount(),
                        sale.getRecordingFee(),
                        sale.getProcessingFee(),
                        sale.getTotalPrice(),
                        financedStr,
                        sale.getMonthlyPayment()
                );
                writer.write(line);
                writer.newLine();
            } else if (contract instanceof LeaseContract lease) {
                String line = String.format("LEASE|%s|%.2f|%.2f|%.2f|%.2f",
                        baseInfo,
                        lease.getExpectedEndingValue(),
                        lease.getLeaseFee(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment()
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
