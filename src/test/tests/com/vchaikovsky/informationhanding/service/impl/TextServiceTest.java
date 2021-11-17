package com.vchaikovsky.informationhanding.service.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.parser.TextParser;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TextServiceTest {
    static final Logger logger = LogManager.getLogger();
    @Spy
    private TextReaderFromFile readerMock;
    private TextParser parser;
    private TextComposite textComposite;
    private TextComposite expectedComposite;
    private TextService service;
    private String text;
    private String expendedSentence;
    private List<TextComponent> componentList;
    private List<TextComponent> expectedList;
    private Map<String, Integer> resultMap;
    private Map<String, Integer> expectedMap;

    @BeforeAll
    void setUp() throws HandingException {
        logger.info("The testing is starting...");
        MockitoAnnotations.openMocks(this);
        text = "  I like to eat. You like to eat. They like to eat.\n" +
                "  Tacos, tacos. Burrito, burrito.\n" +
                "  Everybody wanna eat salts and sweets…\n";
        Mockito.doReturn(text)
                .when(readerMock)
                .readText(Mockito.any());
        service = TextService.getInstance();
        parser = new TextParser();

        expectedMap = new HashMap<>();
        expendedSentence = " Everybody wanna eat salts and sweets…";
    }

    @BeforeEach
    void setTextComposite() throws HandingException {
        textComposite = (TextComposite) parser.parse(readerMock.readText(Mockito.any()));
        componentList = textComposite.getComponents();

        expectedComposite = new TextComposite(TextComponentType.TEXT);
        expectedList = expectedComposite.getComponents();
    }

    @AfterEach
    void resetTextComposite() {
        if(!expectedList.isEmpty()) {
            expectedList.clear();
        }
        if(!expectedMap.isEmpty()) {
            expectedMap.clear();
        }
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void sort() {
        Collections.addAll(expectedList, componentList.get(2), componentList.get(1), componentList.get(0));
        TextComposite result = service.sort(textComposite);

        assertEquals(expectedComposite, result);
    }

    @Test
    public void findSentenceWithLongestWord() {
        String sentence = service.findSentenceWithLongestWord(textComposite);

        assertEquals(expendedSentence, sentence);
    }

    @Test
    public void removeSentenceWithNumberWordsLess() {
        int wordNumber = 5;
        Collections.addAll(expectedList, new TextComposite(TextComponentType.PARAGRAPH), new TextComposite(TextComponentType.PARAGRAPH), componentList.get(2));
        service.removeSentenceWithNumberWordsLess(textComposite, wordNumber);

        assertEquals(expectedComposite, textComposite);
    }

    @Test
    public void findSameWords() {
        expectedMap.put("like", 3);
        expectedMap.put("to", 3);
        expectedMap.put("eat", 4);
        expectedMap.put("tacos", 2);
        expectedMap.put("burrito", 2);

        resultMap = service.findSameWords(textComposite);

        assertEquals(expectedMap, resultMap);
    }

    @Test
    public void findConsonantsAndVowelsNumber() {
        String vowels = "vowels";
        String consonants = "consonants";
        int vowelsNumber = 44;
        int consonantsNumber = 46;
        expectedMap.put(vowels, vowelsNumber);
        expectedMap.put(consonants, consonantsNumber);

        resultMap = service.findConsonantsAndVowelsNumber(textComposite);

        assertEquals(expectedMap, resultMap);
    }
}