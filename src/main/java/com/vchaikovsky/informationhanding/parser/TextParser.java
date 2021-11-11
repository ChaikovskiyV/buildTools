package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class TextParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String TEXT_DELIMITER = "[\t\n]";

    public TextParser() {
        setNextParser(new ParagraphParser());
    }

    @Override
    public TextComponent parse(String text) {
        List<String> paragraphs = Arrays.asList(text.split(TEXT_DELIMITER));
        TextComponent textComposite = new TextComposite(TextComponentType.TEXT);
        paragraphs.forEach(p -> {
            TextComponent paragraph = nextParser.passNext(p);
            textComposite.add(paragraph);
        });
        return textComposite;
    }
}