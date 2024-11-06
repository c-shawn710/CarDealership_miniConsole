package com.pluralsight;

public class SalesContract extends Contract {
    public static final double SALES_TAX = 0.05;
    public static final int RECORDING_FEE = 100;
    public static final int PRICE_MARGIN = 10000;
    private boolean finance;

    public SalesContract(String date, String name, String email, Vehicle vehicleSold, boolean finance) {
        super(date, name, email, vehicleSold);
        this.finance = finance;
    }

    public int getProcessingFee() {
        final int BELOW_MARGIN_FEE = 495;
        final int ABOVE_MARGIN_FEE = 295;
        if (getVehicleSold().getPrice() < PRICE_MARGIN) {
            return BELOW_MARGIN_FEE;
        } else {
            return ABOVE_MARGIN_FEE;
        }
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getMonthlyPayment() {
        //total price / months
        if (isFinance()) {
            return getVehicleSold().getPrice() / getMonths();
        } else {
            return 0;
        }
    }

    @Override
    public String toCsv() {
        return "SALE|" + getDate() + "|" + getName() + "|" + getEmail() + "|" + getVehicleSold().toString() + "|" + (SALES_TAX * getVehicleSold().getPrice()) + "|" + RECORDING_FEE + "|" + getProcessingFee() + "|" + getTotalPrice() + "|" + (isFinance() ? "YES|" : "NO|") + getMonthlyPayment();
    }

    @Override
    public double getTotalPrice() {
        //car + interest
        final double ABOVE_MARGIN_RATE = 1.0425;
        final double BELOW_MARGIN_RATE = 1.0525;
        double interestRate = (getVehicleSold().getPrice() >= PRICE_MARGIN) ? ABOVE_MARGIN_RATE : BELOW_MARGIN_RATE;
        double totalInterest = getVehicleSold().getPrice() * interestRate * getMonths() / 12;
        return (totalInterest + getVehicleSold().getPrice() + RECORDING_FEE + getProcessingFee()) *  (1 + SALES_TAX);
    }

    public int getMonths() {
        final int BELOW_MARGIN_MONTHS = 24;
        final int ABOVE_MARGIN_MONTHS = 48;
        return (getVehicleSold().getPrice() >= PRICE_MARGIN) ? ABOVE_MARGIN_MONTHS : BELOW_MARGIN_MONTHS;
    }
}
