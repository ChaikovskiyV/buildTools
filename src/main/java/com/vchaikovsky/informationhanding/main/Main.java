package com.vchaikovsky.informationhanding.main;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.parser.TextParser;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import com.vchaikovsky.informationhanding.service.impl.TextEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static String filename = "sources/testtext.txt";

    public static void main(String[] args) throws HandingException {
        TextEditor editor = TextEditor.getInstance();
        String text = TextReaderFromFile.getInstance().readText(filename);
        TextParser parser = new TextParser();
        TextComponent component = parser.parse(text);

        logger.info(text);

        TextComposite textComponent = editor.sort((TextComposite) component);
        logger.info(textComponent.toString());

        String sentenceWithLongestWord = editor.findSentenceWithLongestWord((TextComposite) component);
        logger.info(sentenceWithLongestWord);

        Map<String, Integer> lettersNumber = editor.findConsonantsAndVowelsNumber((TextComposite) component);
        logger.info(lettersNumber);

        Map<String, Integer> sameWords = editor.findSameWords((TextComposite) component);
        logger.info(sameWords);

        editor.removeSentenceWithNumberWordsLess((TextComposite) component, 5);
        logger.info(component);
    }
}