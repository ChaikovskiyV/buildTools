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
    static final String WORD_REGEX = "([\\wа-яА-Я]+[-']?[\\wа-яА-Я]+)|[\\wа-яА-Я]+";
    static final String EXPRESSION_REGEX = "[\\W\\d&&[^а-яА-Я]]{3,}";

    @Override
    TextComponent parse(String sentence) {
        List<String> lexemes = Arrays.asList(sentence.split(SENTENCE_DELIMITER_REGEX));
        TextComponent sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Pattern expressPattern = Pattern.compile(EXPRESSION_REGEX);
        lexemes.forEach(l -> {
            Matcher wordMatcher = wordPattern.matcher(l);
            Matcher expressMatcher = expressPattern.matcher(l);

            if(wordMatcher.matches()) {
                setNextParser(new WordParser());
                TextComponent word = nextParser.passNext(l);
                sentenceComposite.add(word);
            } else if(expressMatcher.matches()) {
                ExpressionParser expressionParser = new ExpressionParser();
                TextComponent expression = expressionParser.passNext(l);
                sentenceComposite.add(expression);
            } else if(!l.isEmpty()) {
                setNextParser(new LexemeParser());
                TextComponent lexeme = nextParser.passNext(l);
                sentenceComposite.add(lexeme);
            }
        });
        return sentenceComposite;
    }
}