package com.pluralsight;

public class LeaseContract extends Contract {
    private final double EXPECTED_END_VALUE = getVehicleSold().getPrice() * 0.5;
    private final double LEASE_FEE = getVehicleSold().getPrice() * 0.07;
    public static final double INTEREST_RATE = 0.04;
    public static final int LEASE_TERMS_MONTH = 36;

    public LeaseContract(String date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        return (getMonthlyPayment() * LEASE_TERMS_MONTH) + LEASE_FEE;
    }

    @Override
    public double getMonthlyPayment() {
        // Calculate monthly payment based on lease rules
        double monthlyIntRate = INTEREST_RATE / 12;
        return ((EXPECTED_END_VALUE * monthlyIntRate) / 1 - Math.pow(1 + monthlyIntRate, -LEASE_TERMS_MONTH));
    }

    @Override
    public String toCsv() {
        return "LEASE|" + getDate() + "|" + getName() + "|" + getEmail() + "|" + getVehicleSold().toString() + "|" + EXPECTED_END_VALUE + "|" + LEASE_FEE + "|" + getTotalPrice() +"|" + getMonthlyPayment();
    }
}
