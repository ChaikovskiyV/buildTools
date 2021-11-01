package com.vchaikovsky.xmlparsing.validator;

import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class BankValidator {
    static final Logger logger = LogManager.getLogger();
    static final String SCHEMA_FILE_NAME = "sources/schema/schema.xsd";

    private BankValidator() {
    }

    public static boolean validateXMLFile(String fileName) throws BankException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = Path
                .of(SCHEMA_FILE_NAME)
                .toFile();
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
        } catch (SAXException e) {
            logger.warn("The file " + fileName + " is not valid.", e);
            return false;
        } catch (IOException e) {
            logger.error("The file " + fileName + " was not found.", e);
            throw new BankException("The file " + fileName + " was not found.", e);
        }
        return true;
    }
}