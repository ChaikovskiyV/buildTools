package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.entity.*;
import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BanksSAXBuilderTest {
    static final Logger logger = LogManager.getLogger();
    private BanksSAXBuilder sAXBuilder;
    private String fileName;
    private String wrongFileName;
    private Set<Bank> expectedSet;
    private Set<Bank> result;
    private Bank bank;
    private String id;
    private String name;
    private int rating;
    private Country country;
    private Deposit deposit;
    private DepositType depositType;
    private String depositor;
    private String accountId;
    private int amount;
    private double profitability;
    private StateBankType bankType;
    private YearMonth time;
    private int stateShare;

    @BeforeAll
    void setUp() throws BankException {
        logger.info("The testing is starting...");
        sAXBuilder = new BanksSAXBuilder();
        fileName = "sources/bankstest.xml";
        wrongFileName = "sources/sources/bankstest.xml";
        expectedSet = new HashSet<>();
        id = "b1";
        name = "HonestBank";
        rating = 5;
        country = Country.BELARUS;
        depositType = DepositType.FIXED;
        depositor = "Ivanov";
        accountId = "BYHB15261296";
        amount = 0;
        profitability = 8.8;
        bankType = StateBankType.COMMERCIAL;
        time = YearMonth.of(2021, 12);
        stateShare = 30;
        deposit = new Deposit(depositType, depositor, accountId, amount, profitability, time);
        bank = new StateBank(id, name, rating, country, deposit, bankType);
        expectedSet.add(bank);

        id = "b2";
        rating = 8;
        depositType = DepositType.SAVING;
        amount = 150;
        profitability = 16;
        time = YearMonth.of(2022, 8);
        deposit = new Deposit(depositType, depositor, accountId, amount, profitability, time);
        bank = new PrivateBank(id, name, rating, country, deposit, stateShare);
        expectedSet.add(bank);

        id = "b3";
        rating = 7;
        depositType = DepositType.ACCUMULATION;
        amount = 2000;
        profitability = 10;
        time = YearMonth.of(2023, 12);
        bankType = StateBankType.CENTRAL;
        deposit = new Deposit(depositType, depositor, accountId, amount, profitability, time);
        bank = new StateBank(id, name, rating, country, deposit, bankType);
        expectedSet.add(bank);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void buildSetBanks() throws BankException {
        sAXBuilder.buildSetBanks(fileName);
        result = sAXBuilder.getBanks();

        assertEquals(expectedSet, result);
    }

    @Test
    public void whetherThrowExceptionIfFileNotFound() {
        assertThrows(BankException.class, () -> sAXBuilder.buildSetBanks(wrongFileName));
    }
}