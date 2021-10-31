package com.vchaikovsky.xmlparsing.entity;

public class StateBank extends Bank{
    private StateBankType bankType;

    public StateBank() {
        //default meaning
        bankType = StateBankType.CENTRAL;
    }

    public StateBank(String id, String name, int rating, Country country, Deposit deposit, StateBankType bankType) {
        super(id, name, rating, country, deposit);
        this.bankType = bankType;
    }

    public StateBankType getBankType() {
        return bankType;
    }

    public void setBankType(StateBankType bankType) {
        this.bankType = bankType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() == this.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        StateBank stateBank = (StateBank) o;
        return bankType == stateBank.bankType;
    }

    @Override
    public int hashCode() {
        int first = 34;
        int result = super.hashCode() * first * bankType.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("StateBank{")
                .append("id=")
                .append(super.getId())
                .append(", name=")
                .append(super.getName())
                .append(", rating=")
                .append(super.getRating())
                .append(", country=")
                .append(super.getCountry())
                .append(", deposit=")
                .append(super.getDeposit())
                .append(", bankType=")
                .append(bankType)
                .append('}')
                .toString();
    }
}

