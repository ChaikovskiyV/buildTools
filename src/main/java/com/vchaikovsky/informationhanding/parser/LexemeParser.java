package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.SymbolLeaf;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String PUNCTUATION_REGEX = "\\W";

    @Override
    TextComponent parse(String text) {
        TextComponent lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        TextComponent word;
        TextComponent punctuation;

        Pattern pattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher firstSymbolMatcher = pattern.matcher(String.valueOf(text.charAt(0)));
        Matcher lastSymbolMatcher = pattern.matcher(String.valueOf(text.charAt(text.length() - 1)));

        if(firstSymbolMatcher.matches() && lastSymbolMatcher.matches() && text.length() > 1) {
            punctuation = new SymbolLeaf(TextComponentType.PUNCTUATION, text.charAt(0));
            lexemeComposite.add(punctuation);
            word = new WordParser().passNext(text.substring(1, text.length() - 1));
            lexemeComposite.add(word);
            punctuation = new SymbolLeaf(TextComponentType.PUNCTUATION, text.charAt(text.length() - 1));
            lexemeComposite.add(punctuation);
        } else if(firstSymbolMatcher.matches()) {
            punctuation = new SymbolLeaf(TextComponentType.PUNCTUATION, text.charAt(0));
            lexemeComposite.add(punctuation);
            word = new WordParser().passNext(text.substring(1));
            lexemeComposite.add(word);
        } else {
            word = new WordParser().passNext(text.substring(0, text.length() - 1));
            lexemeComposite.add(word);
            punctuation = new SymbolLeaf(TextComponentType.PUNCTUATION, text.charAt(text.length() - 1));
            lexemeComposite.add(punctuation);
        }
        return lexemeComposite;
    }
}