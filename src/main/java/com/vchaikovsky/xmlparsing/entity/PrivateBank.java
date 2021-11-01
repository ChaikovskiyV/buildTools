package com.vchaikovsky.xmlparsing.entity;

public class PrivateBank extends Bank {
    static final int DEFAULT_STATE_SHARE = 1;

    private int stateShare;

    public PrivateBank() {
        //default meaning
        this.stateShare = DEFAULT_STATE_SHARE;
    }

    public PrivateBank(String id, String name, int rating, Country country, Deposit deposit, int stateShare) {
        super(id, name, rating, country, deposit);
        this.stateShare = stateShare;
    }

    public int getStateShare() {
        return stateShare;
    }

    public void setStateShare(int stateShare) {
        this.stateShare = stateShare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PrivateBank that = (PrivateBank) o;
        return  stateShare == that.stateShare;
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = super.hashCode() * first * stateShare;

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("PrivateBank{")
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
                .append(", stateShare=")
                .append(stateShare)
                .append('}')
                .toString();
    }
}