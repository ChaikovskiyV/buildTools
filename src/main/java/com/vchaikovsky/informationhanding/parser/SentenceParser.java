package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String SENTENCE_DELIMITER_REGEX = "\s+";
    static final String WORD_REGEX = "(\\w+[-']?\\w+)|\\w+";
    private WordParser wordParser = new WordParser();

    public SentenceParser() {
        setNextParser(new LexemeParser());
    }

    @Override
    TextComponent parse(String sentence) {
        List<String> lexemes = Arrays.asList(sentence.split(SENTENCE_DELIMITER_REGEX));
        TextComponent sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        lexemes.forEach(l -> {
            Matcher matcher = wordPattern.matcher(l);
            if(matcher.matches()) {
                TextComponent word = wordParser.passNext(l);
                sentenceComposite.add(word);
            } else if(!l.isEmpty()) {
                TextComponent lexeme = nextParser.passNext(l);
                sentenceComposite.add(lexeme);
            }
        });
        return sentenceComposite;
    }
}