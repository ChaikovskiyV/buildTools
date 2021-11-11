package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ParagraphParser extends AbstractParser {
    static final Logger logger = LogManager.getLogger();
    static final String PARAGRAPH_DELIMITER = "(?<=[.?!…])\s";

    public ParagraphParser() {
        setNextParser(new SentenceParser());
    }

    @Override
    TextComponent parse(String paragraph) {
        List<String> sentences = Arrays.asList(paragraph.split(PARAGRAPH_DELIMITER));
        TextComponent paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
        sentences.forEach(s -> {
            TextComponent sentence = nextParser.passNext(s);
            paragraphComposite.add(sentence);
        });
        return paragraphComposite;
    }
}