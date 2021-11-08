package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.SymbolLeaf;
import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String WORD_DELIMITER = "";
    static final String SYMBOLS_REGEX = "-";

    @Override
    TextComponent parse(String text) {
        List<String> letters = Arrays.asList(text
                .trim()
                .split(WORD_DELIMITER));
        TextComponent wordComposite = new TextComposite(TextComponentType.WORD);
        Pattern symbolPattern = Pattern.compile(SYMBOLS_REGEX);
        letters.forEach(l -> {
            if(!l.isEmpty()) {
                Matcher matcher = symbolPattern.matcher(l);
                TextComponent symbol = matcher.matches() ? new SymbolLeaf(TextComponentType.PUNCTUATION, l.charAt(0)) : new SymbolLeaf(TextComponentType.LETTER, l.charAt(0));
                wordComposite.add(symbol);
            }

        });
        return wordComposite;
    }
}