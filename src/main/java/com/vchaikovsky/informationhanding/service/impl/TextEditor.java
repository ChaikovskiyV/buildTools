package com.vchaikovsky.informationhanding.service.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import com.vchaikovsky.informationhanding.service.TextEditorInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor implements TextEditorInt {
    static final Logger logger = LogManager.getLogger();
    static final String VOWELS = "[aeyuioуеыаоэёяию]";

    @Override
    public TextComposite sort(TextComposite textComposite) {
        List<TextComponent> components = textComposite
                .getComponents()
                .stream()
                .sorted((p1, p2) -> p1.getComponents().size() - p2.getComponents().size())
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
        var ref = new Object() {
            String sentence;
            int lengthWord = 0;
        };
        sentences.forEach(s -> {
            s.getComponents().forEach(c -> {
                int cLength = c.getComponents().size();
                if(c.getComponentType() == TextComponentType.LEXEME &&  cLength - 1 > ref.lengthWord) {
                    ref.lengthWord = cLength -1;
                    ref.sentence = s.toString();
                } else if(c.getComponentType() == TextComponentType.WORD && cLength > ref.lengthWord) {
                    ref.lengthWord = cLength;
                    ref.sentence = s.toString();
                }
            });
        });
        System.out.println(ref.lengthWord);

        return ref.sentence;
    }

    @Override
    public TextComposite removeSentenceWithNumberWordsLess(TextComposite textComposite, int wordsNumbers) {
        textComposite.getComponents().forEach(p ->
                p.getComponents().removeIf(s ->
                        s.getComponents().size() < wordsNumbers));
        return textComposite;
    }

    @Override
    public Map<String, Integer> findSameWords(TextComposite textComposite) {
        Map<String, Integer> repeatedWords = new HashMap<>();

        findAllByType(textComposite, TextComponentType.WORD)
                .stream()
                .map(c -> c.toString().toLowerCase())
                .peek(s -> {
                    if(!repeatedWords.containsKey(s)) {
                        int startValue = 1;
                        repeatedWords.put(s, startValue);
                    } else {
                        int currentValue = repeatedWords.get(s);
                        repeatedWords.put(s, ++currentValue);
                    }
                });

        repeatedWords.keySet().forEach(k -> {
            if (repeatedWords.get(k) < 2) {
                repeatedWords.remove(k);
            }
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
            Matcher matcher = vowelPattern.matcher(String.valueOf(l));
            int currentNumber = lettersNumber.get(l);

            if(matcher.matches()) {
                lettersNumber.put(vowelsKey, ++currentNumber);
            } else {
                lettersNumber.put(consonantsKey, ++currentNumber);
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
            if(c.getComponentType() == componentType) {
                components.add(c);
            } else {
                findByType(components, c, componentType);
            }
        });
    }
}