package com.vchaikovsky.informationhanding.service.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import com.vchaikovsky.informationhanding.service.TextEditorInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TextEditor implements TextEditorInt {
    static final Logger logger = LogManager.getLogger();

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
        List<TextComponent> sentences = findByType(textComposite, TextComponentType.SENTENCE);
        var ref = new Object() {
            String sentence;
            int lengthWord = 0;
        };
        sentences.forEach(s -> {
            s.getComponents().forEach(c -> {
                int cLength = c.getComponents().size();
                if(c.getComponentType() == TextComponentType.LEXEME &&  cLength- 1 > ref.lengthWord) {
                    ref.lengthWord = cLength -1;
                    ref.sentence = s.toString();
                } else if(c.getComponentType() == TextComponentType.WORD && cLength > ref.lengthWord) {
                    ref.sentence = s.toString();
                }
            });
        });

        return ref.sentence;
    }

    @Override
    public TextComposite removeSentenceWithNumberWordsLess(TextComposite textComposite, int wordsNumbers) {
        return null;
    }

    @Override
    public List<String> findSameWords(TextComposite textComposite) {
        return null;
    }

    @Override
    public Map<String, Integer> findConsonantsAndVowelsNumber(TextComposite textComposite) {
        return null;
    }

    private List<TextComponent> findByType(TextComponent component, TextComponentType type) {
        List<TextComponent> components = new ArrayList<>();
        component.getComponents().forEach(c -> {
            List<TextComponent> childComponents =component.getComponents()
                    .stream()
                    .filter(c1 -> c1.getComponentType() == type)
                    .toList();
            Collections.copy(components, childComponents);
        });

        return components;
    }
}