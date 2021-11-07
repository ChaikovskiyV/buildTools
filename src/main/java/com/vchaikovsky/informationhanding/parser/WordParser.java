package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.SymbolLeaf;
import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class WordParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String WORD_DELIMITER = "";

    @Override
    TextComponent parse(String text) {
        List<String> letters = Arrays.asList(text.split(WORD_DELIMITER));
        TextComponent wordComposite = new TextComposite(TextComponentType.WORD);
        letters.forEach(l -> {
            TextComponent letter = new SymbolLeaf(TextComponentType.LETTER, l.charAt(0));
            wordComposite.add(letter);
        });
        return wordComposite;
    }
}
