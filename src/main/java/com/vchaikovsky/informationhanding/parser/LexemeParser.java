package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.SymbolLeaf;
import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();

    @Override
    TextComponent parse(String text) {
        TextComponent lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        TextComponent word = new WordParser().passNext(text.substring(0, text.length() - 1));
        lexemeComposite.add(word);

        TextComponent punctuation = new SymbolLeaf(TextComponentType.PUNCTUATION, text.charAt(text.length() - 1));
        lexemeComposite.add(punctuation);

        return lexemeComposite;
    }
}