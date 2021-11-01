package com.vchaikovsky.xmlparsing.entity;

public abstract class Bank {
    static final int DEFAULT_RATING = 10;
    static final String DEFAULT_ID = "";
    static final String DEFAULT_NAME = "";

    private String id;
    private String name;
    private int rating;
    private Country country;
    private Deposit deposit;

    public Bank() {
        //default meaning
        id = DEFAULT_ID;
        name = DEFAULT_NAME;
        rating = DEFAULT_RATING;
        country = Country.BELARUS;
        deposit = new Deposit();
    }

    public Bank(String id, String name, int rating, Country country, Deposit deposit) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.deposit = deposit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        Bank bank = (Bank) o;
        return rating == bank.rating && id.equals(bank.id) && name.equals(bank.name) &&
                country == bank.country && deposit.equals(bank.deposit);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * id.hashCode();
        result = result * first * name.hashCode();
        result = result * first * rating;
        result = result * first * country.hashCode();
        result = result * first * deposit.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Bank{").append("id='")
                .append(id)
                .append(", name='")
                .append(name)
                .append(", rating=")
                .append(rating)
                .append(", country=")
                .append(country)
                .append(", deposit=")
                .append(deposit)
                .append('}')
                .toString();
    }
}