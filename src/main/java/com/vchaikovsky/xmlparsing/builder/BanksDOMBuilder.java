package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.entity.*;
import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class BanksDOMBuilder extends AbstractBanksBuilder{
    static final Logger logger = LogManager.getLogger();
    static final String stateBankTag = BankXmlTag
            .STATE_BANK
            .getValue();
    static final String privateBankTag = BankXmlTag
            .PRIVATE_BANK
            .getValue();
    private DocumentBuilder documentBuilder;

    public BanksDOMBuilder() throws BankException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Exception from parser configuration", e);
            throw new BankException("Exception from parser configuration", e);
        }
    }

    @Override
    public void buildSetBanks(String filename) throws BankException {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element element = document.getDocumentElement();
            NodeList stateBanksList = element.getElementsByTagName(stateBankTag);
            NodeList privateBanksList = element.getElementsByTagName(privateBankTag);

            int number = 0;
            while (number < stateBanksList.getLength() || number < privateBanksList.getLength()) {
                if(number < stateBanksList.getLength()) {
                    Element bankElement = (Element) stateBanksList.item(number);
                    Bank bank = buildBank(bankElement);
                    banks.add(bank);
                }
                if(number < privateBanksList.getLength()) {
                    Element bankElement = (Element) privateBanksList.item(number);
                    Bank bank = buildBank(bankElement);
                    banks.add(bank);
                }
                number++;
            }
        } catch (IOException e) {
            logger.error("The file " + filename + " was not found.", e);
            throw new BankException("The file " + filename + " was not found.", e);
        } catch (SAXException e) {
            logger.error("Exception from DOM parser.", e);
            throw new BankException("Exception from DOM parser.", e);
        }
    }

    private Bank buildBank(Element bankElement) {
        Bank bank = null;
        String elementTag = bankElement.getTagName();

        if(privateBankTag.equals(elementTag) || stateBankTag.equals(elementTag)) {
            String requiredAttr = "id";
            String optionalAttr = "rating";
            bank = privateBankTag.equals(elementTag) ? new PrivateBank() : new StateBank();
            bank.setId(bankElement.getAttribute(requiredAttr));

            if(bankElement.hasAttribute(optionalAttr)) {
                int rating = Integer.parseInt(bankElement
                        .getAttribute(optionalAttr));
                bank.setRating(rating);
            }
            bank.setName(getElementTextContent(bankElement, BankXmlTag
                    .NAME
                    .getValue()));
            String countryName = getElementTextContent(bankElement, BankXmlTag
                    .COUNTRY
                    .getValue())
                    .toUpperCase();
            bank.setCountry(Country.valueOf(countryName));

            if(privateBankTag.equals(elementTag)) {
                PrivateBank privateBank = (PrivateBank) bank;
                int stateShare = Integer.parseInt(getElementTextContent(bankElement, BankXmlTag
                        .STATE_SHARE
                        .getValue()));
                privateBank.setStateShare(stateShare);
            } else {
                StateBank stateBank = (StateBank) bank;
                String bankType = getElementTextContent(bankElement, BankXmlTag
                        .BANK_TYPE
                        .getValue());
                stateBank.setBankType(StateBankType
                        .valueOf(bankType
                                .toUpperCase()));
            }
            Deposit deposit = new Deposit();
            String depositType = getElementTextContent(bankElement, BankXmlTag
                    .TYPE
                    .getValue());
            deposit.setDepositType(DepositType
                    .valueOf(depositType
                            .toUpperCase()));
            deposit.setAccountId(getElementTextContent(bankElement, BankXmlTag
                    .ACCOUNT_ID
                    .getValue()));
            deposit.setDepositor(getElementTextContent(bankElement, BankXmlTag
                    .DEPOSITOR
                    .getValue()));
            int amount = Integer.parseInt(getElementTextContent(bankElement, BankXmlTag
                    .AMOUNT_ON_DEPOSIT
                    .getValue()));
            deposit.setAmount(amount);
            double profitability = Double.parseDouble(getElementTextContent(bankElement, BankXmlTag
                    .PROFITABILITY
                    .getValue()));
            deposit.setProfitability(profitability);
            YearMonth timeConstraints = YearMonth.parse(getElementTextContent(bankElement, BankXmlTag
                    .TIME_CONSTRAINTS
                    .getValue()));
            deposit.setTimeConstraints(timeConstraints);
            bank.setDeposit(deposit);
        }
        return bank;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String textContent = node.getTextContent();

        return textContent;
    }
}