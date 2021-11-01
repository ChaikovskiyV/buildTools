package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.entity.*;
import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;

public class BanksStAXBuilder extends AbstractBanksBuilder{
    static final Logger logger = LogManager.getLogger();
    static final String HYPHEN = "-";
    static final String UNDERLINING = "_";
    private XMLInputFactory inputFactory;
    private Deposit deposit;

    public BanksStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetBanks(String filename) throws BankException {
        XMLStreamReader reader;
        String name;

        try(FileInputStream inputStream = new FileInputStream(filename)) {
             reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName()
                            .toUpperCase()
                            .replaceAll(HYPHEN, UNDERLINING);
                    if(BankXmlTag.valueOf(name) == BankXmlTag.PRIVATE_BANK || BankXmlTag.valueOf(name) == BankXmlTag.STATE_BANK) {
                        Bank bank = buildBank(reader, name);
                        banks.add(bank);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("The file " + filename + " was not found.", e);
            throw new BankException("The file " + filename + " was not found.", e);
        } catch (XMLStreamException e) {
            logger.error("Exception from StAX parser", e);
            throw new BankException("Exception from StAX parser", e);
        }
    }

    private Bank buildBank(XMLStreamReader reader, String name) throws BankException {
        Bank bank = BankXmlTag.valueOf(name) == BankXmlTag.PRIVATE_BANK ? new PrivateBank() : new StateBank();
        String requiredAttr = "id";
        String optionalAttr = "rating";
        int attrsCount = 2;

        if(reader.getAttributeCount() < attrsCount) {
            bank.setId(reader
                    .getAttributeValue(0));
        } else {
            bank.setId(reader
                    .getAttributeValue(null, requiredAttr));
            int rating = Integer.parseInt(reader
                    .getAttributeValue(null, optionalAttr));
            bank.setRating(rating);
        }
        String tagName;
        while (true) {
            try {
                if (!reader.hasNext()) {
                    break;
                }
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT-> {
                        tagName = reader.getLocalName();
                        addParameters(bank, tagName, reader);
                    }
                    case XMLStreamConstants.END_ELEMENT -> {
                        tagName = reader
                                .getLocalName()
                                .replaceAll(HYPHEN, UNDERLINING);
                        if(BankXmlTag.valueOf(tagName.toUpperCase()) == BankXmlTag.STATE_BANK ||
                                BankXmlTag.valueOf(tagName.toUpperCase()) == BankXmlTag.PRIVATE_BANK) {
                            return bank;
                        } else if(BankXmlTag.valueOf(tagName.toUpperCase()) == BankXmlTag.DEPOSIT) {
                            bank.setDeposit(deposit);
                        }
                    }
                }
            } catch (XMLStreamException e) {
                logger.error("Exception from XMLStreamReader", e);
                throw new BankException("Exception from XMLStreamReader", e);
            }
        }
        return bank;
    }

    private void addParameters(Bank bank, String tagName, XMLStreamReader reader) throws BankException {
        String name = tagName
                .toUpperCase()
                .replaceAll(HYPHEN, UNDERLINING);
        String meaning = getXmlText(reader);
        switch (BankXmlTag.valueOf(name)) {
            case NAME -> bank.setName(meaning);
            case COUNTRY -> bank.setCountry(Country.valueOf(meaning
                    .toUpperCase()
                    .replaceAll(HYPHEN, UNDERLINING)));
            case STATE_SHARE -> {
                int stateShare = Integer.parseInt(meaning);
                PrivateBank privateBank = (PrivateBank) bank;
                privateBank.setStateShare(stateShare);
            }
            case BANK_TYPE -> {
                StateBank stateBank = (StateBank) bank;
                stateBank.setBankType(StateBankType.valueOf(meaning
                        .toUpperCase()
                        .replaceAll(HYPHEN, UNDERLINING)));
            }
            case DEPOSIT -> deposit = new Deposit();
            case TYPE -> deposit.setDepositType(DepositType.valueOf(meaning
                    .toUpperCase()
                    .replaceAll(HYPHEN, UNDERLINING)));
            case DEPOSITOR -> deposit.setDepositor(meaning);
            case ACCOUNT_ID -> deposit.setAccountId(meaning);
            case AMOUNT_ON_DEPOSIT -> {
                int amount = Integer.parseInt(meaning);
                deposit.setAmount(amount);
            }
            case PROFITABILITY -> {
                double profitability = Double.parseDouble(meaning);
                deposit.setProfitability(profitability);
            }
            case TIME_CONSTRAINTS -> {
                YearMonth data = YearMonth.parse(meaning);
                deposit.setTimeConstraints(data);
            }
        }
    }

    private String getXmlText(XMLStreamReader reader) throws BankException {
        String str = null;
        try {
            if(reader.hasNext()) {
            reader.next();
            str = reader.getText();
            }
        } catch (XMLStreamException e) {
            logger.error("Exception from XMLStreamReader", e);
            throw new BankException("Exception from XMLStreamReader", e);
        }
        return str;
    }
}