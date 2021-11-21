package com.vchaikovsky.informationhanding.main;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.parser.TextParser;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import com.vchaikovsky.informationhanding.service.impl.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static String filename = "expressiontext.txt";//sources/sourcetext


    public static void main(String[] args) throws HandingException {
        TextService service = TextService.getInstance();
        String text = TextReaderFromFile.getInstance().readText(filename);
        TextParser parser = new TextParser();
        TextComponent component = parser.parse(text);

        logger.info(text);

        TextComposite textComponent = service.sort((TextComposite) component);
        logger.info(textComponent);

        String sentenceWithLongestWord = service.findSentenceWithLongestWord((TextComposite) component);
        logger.info(sentenceWithLongestWord);

        Map<String, Integer> lettersNumber = service.findConsonantsAndVowelsNumber((TextComposite) component);
        logger.info(lettersNumber);

        Map<String, Integer> sameWords = service.findSameWords((TextComposite) component);
        logger.info(sameWords);

        service.removeSentenceWithNumberWordsLess((TextComposite) component, 5);
        logger.info(component);
    }
}

