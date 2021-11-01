package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.entity.*;
import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BankHandler extends DefaultHandler {
    static final Logger logger = LogManager.getLogger();
    static final String REQUIRED_ATTR = "id";
    static final String OPTIONAL_ATTR = "rating";
    static final String HYPHEN = "-";
    static final String UNDERLINING = "_";
    private Set<Bank> banks;
    private Bank currentBank;
    private Deposit deposit;
    private BankXmlTag currentTag;
    private EnumSet<BankXmlTag> bankTags;

    public BankHandler() {
        banks = new HashSet<>();
        bankTags = EnumSet.range(BankXmlTag.DEPOSIT, BankXmlTag.TIME_CONSTRAINTS);
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    @Override
    public void startDocument() {
        logger.info("Parsing started.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String stateBank = BankXmlTag.STATE_BANK
                .getValue();
        String privateBank = BankXmlTag.PRIVATE_BANK
                .getValue();
      if(stateBank.equals(qName) || privateBank.equals(qName)) {
          currentBank = stateBank.equals(qName) ? new StateBank() : new PrivateBank();
          addAttribute(currentBank, attributes);
      } else {
          String tagName = qName
                  .toUpperCase()
                  .replaceAll(HYPHEN, UNDERLINING);
          BankXmlTag tag = BankXmlTag.valueOf(tagName.toUpperCase());
          if(bankTags.contains(tag)) {
              currentTag = tag;
          }
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String stateBank = BankXmlTag.STATE_BANK
                .getValue();
        String privateBank = BankXmlTag.PRIVATE_BANK
                .getValue();
        String depositTag = BankXmlTag.DEPOSIT
                .getValue();
        if(stateBank.equals(qName) || privateBank.equals(qName)) {
            banks.add(currentBank);
        } else if(depositTag.equals(qName)) {
            currentBank.setDeposit(deposit);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length)
                .trim();
        String forEnum = str
                .toUpperCase()
                .replaceAll(HYPHEN,UNDERLINING);

        if(currentTag != null) {
            switch (currentTag) {
                case NAME -> currentBank.setName(str);
                case COUNTRY -> currentBank.setCountry(Country.valueOf(forEnum));
                case DEPOSIT -> deposit = new Deposit();
                case TYPE -> deposit.setDepositType(DepositType.valueOf(forEnum));
                case DEPOSITOR -> deposit.setDepositor(str);
                case ACCOUNT_ID -> deposit.setAccountId(str);
                case AMOUNT_ON_DEPOSIT -> {
                    int amount = Integer.parseInt(str);
                    deposit.setAmount(amount);
                }
                case PROFITABILITY -> deposit.setProfitability(Double.parseDouble(str));
                case TIME_CONSTRAINTS -> {
                    String timeStr = str.replace(UNDERLINING, HYPHEN);
                    YearMonth yearMonth = YearMonth.parse(timeStr);
                    deposit.setTimeConstraints(yearMonth);
                }
                case BANK_TYPE -> {
                    StateBank stateBank = (StateBank)currentBank;
                    stateBank.setBankType(StateBankType.valueOf(forEnum));
                }
                case STATE_SHARE -> {
                    PrivateBank privateBank = (PrivateBank) currentBank;
                    int stateShare = Integer.parseInt(str);
                    privateBank.setStateShare(stateShare);
                }
                default -> {
                    try {
                        throw new BankException("Unknown teg: " + str);
                    } catch (BankException e) {
                        logger.error("Unknown teg: " + str, e);
                    }
                }
            }
        }
        currentTag = null;
    }

    @Override
    public void endDocument() {
        logger.info("Parsing finished.");
    }

    private void addAttribute(Bank bank, Attributes attrs) {
        for (int i = 0; i < attrs.getLength(); i++) {
            String attr = attrs.getValue(i);
            switch (attrs.getQName(i)) {
                case REQUIRED_ATTR-> bank.setId(attr);
                case OPTIONAL_ATTR-> {
                    int rating = Integer.parseInt(attr);
                    bank.setRating(rating);
                }
                default -> logger.warn("Unknown attribute: " + attr);
            }
        }
    }
}