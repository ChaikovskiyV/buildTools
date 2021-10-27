package com.vchaikovsky.xmlxsdparsing.entity;

import java.time.YearMonth;

public class Deposit {
    public static final int DEFAULT_AMOUNT = 0;
    public static final double DEFAULT_PROFITABILITY = 0;
    public static final String DEFAULT_DEPOSITOR = "Anonym";
    private static final String DEFAULT_COUNT_ID = "BYBB12651396";

    private DepositType depositType;
    private String depositor;
    private String countId;
    private int amount;
    private double profitability;
    private YearMonth timeConstraints;

    public Deposit() {
        //default meaning
        depositType = DepositType.FIXED;
        depositor = DEFAULT_DEPOSITOR;
        countId = DEFAULT_COUNT_ID;
        amount = DEFAULT_AMOUNT;
        profitability = DEFAULT_PROFITABILITY;
        timeConstraints = YearMonth.now()
                .plusMonths(1);
    }

    public Deposit(DepositType depositType, String depositor, String countId, int amount, double profitability, YearMonth timeConstraints) {
        this.depositType = depositType;
        this.depositor = depositor;
        this.countId = countId;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public YearMonth getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(YearMonth timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return amount == deposit.amount && Double.compare(deposit.profitability, profitability) == 0 &&
                depositType == deposit.depositType && depositor.equals(deposit.depositor) &&
                countId.equals(deposit.countId) && timeConstraints.equals(deposit.timeConstraints);
    }

    @Override
    public int hashCode() {
        int first = 34;
        int result = 1;

        result = result * first * depositType.hashCode();
        result = result * first * depositor.hashCode();
        result = result * first * countId.hashCode();
        result = result * first * amount;
        result = (int) (result * first * profitability);
        result = result * first * timeConstraints.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Deposit{").append("depositType=")
                .append(depositType)
                .append(", depositor='")
                .append(depositor)
                .append(", countId='")
                .append(countId)
                .append(", amount=")
                .append(amount)
                .append(", profitability=")
                .append(profitability)
                .append(", timeConstraints=")
                .append(timeConstraints)
                .append('}')
                .toString();
    }
}