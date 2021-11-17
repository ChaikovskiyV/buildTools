package com.vchaikovsky.informationhanding.service.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import com.vchaikovsky.informationhanding.service.TextServiceInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextService implements TextServiceInt {
    static final Logger logger = LogManager.getLogger();
    static final String VOWELS = "[aeyuioуеыаоэёяию]";
    private static String sentence = "";
    private static int lengthWord = 0;
    private static TextService instance;

    public static TextService getInstance() {
        if(instance == null) {
            instance = new TextService();
        }
        return instance;
    }

    @Override
    public TextComposite sort(TextComposite textComposite) {
        List<TextComponent> components = textComposite
                .getComponents()
                .stream()
                .sorted(Comparator.comparingInt(p -> p.getComponents().size()))
                .toList();
        for (int i = 0; i < components.size(); i++) {
            textComposite
                    .getComponents()
                    .set(i, components.get(i));
        }
        return textComposite;
    }

    @Override
    public String findSentenceWithLongestWord(TextComposite textComposite) {
        List<TextComponent> sentences = findAllByType(textComposite, TextComponentType.SENTENCE);
        sentences.forEach(s ->
                s.getComponents().forEach(c -> {
                int cLength = c.getComponents().size();
                if(c.getComponentType() == TextComponentType.LEXEME ) {
                        TextComponent word = c.getComponents()
                                .stream()
                                .filter(c1 -> c.getComponentType() == TextComponentType.WORD)
                                .findFirst()
                                .orElse(new TextComposite(TextComponentType.WORD));
                        int length = word.getComponents().size();
                        if(length > lengthWord) {
                            lengthWord = length;
                            sentence = s.toString();
                        }
                } else if(c.getComponentType() == TextComponentType.WORD && cLength > lengthWord) {
                    lengthWord = cLength;
                    sentence = s.toString();
                }
            }));

        return sentence;
    }

    @Override
    public void removeSentenceWithNumberWordsLess(TextComposite textComposite, int wordsNumber) {
        textComposite.getComponents().forEach(p ->
                p.getComponents().removeIf(s ->
                        s.getComponents().size() < wordsNumber));
    }

    @Override
    public Map<String, Integer> findSameWords(TextComposite textComposite) {
        Map<String, Integer> repeatedWords = new ConcurrentHashMap<>();
        List<TextComponent> allWords = findAllByType(textComposite, TextComponentType.WORD);

        allWords.stream()
                .map(c -> c.toString().toLowerCase().trim())
                .forEach(s -> {
                    int step = 1;
                    repeatedWords.merge(s, step, Integer::sum);
                });

        Set<String> keys = repeatedWords.keySet();
        keys.forEach(k -> {
            int minValue = 1;
            repeatedWords.remove(k, minValue);
            });

        return repeatedWords;
    }

    @Override
    public Map<String, Integer> findConsonantsAndVowelsNumber(TextComposite textComposite) {
        String vowelsKey = "vowels";
        String consonantsKey = "consonants";
        int startValue = 0;

        Map<String, Integer> lettersNumber = new HashMap<>();
        lettersNumber.put(vowelsKey, startValue);
        lettersNumber.put(consonantsKey, startValue);

        List<TextComponent> letters = findAllByType(textComposite, TextComponentType.LETTER);
        Pattern vowelPattern = Pattern.compile(VOWELS);
        letters.forEach(l -> {
            Matcher matcher = vowelPattern.matcher(String.valueOf(l).toLowerCase());
            int currentValue;
            if(matcher.matches()) {
                currentValue = lettersNumber.get(vowelsKey);
                lettersNumber.put(vowelsKey, ++currentValue);
            } else {
                currentValue = lettersNumber.get(consonantsKey);
                lettersNumber.put(consonantsKey, ++currentValue);
            }
        });

        return lettersNumber;
    }

    private List<TextComponent> findAllByType(TextComponent component, TextComponentType type) {
        List<TextComponent> components = new ArrayList<>();
        component.getComponents().forEach(c -> findByType(components, c, type));

        return components;
    }

    private void findByType(List<TextComponent> components, TextComponent component, TextComponentType componentType) {
        component.getComponents().forEach(c -> {
            TextComponentType type = c.getComponentType();
            if(type == componentType) {
                components.add(c);
            } else if(type != TextComponentType.LETTER && type != TextComponentType.PUNCTUATION){
                findByType(components, c, componentType);
            }
        });
    }
}