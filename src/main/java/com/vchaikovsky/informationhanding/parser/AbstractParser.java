package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;

public abstract class AbstractParser {
    protected AbstractParser nextParser;

    protected void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    protected TextComponent passNext(String text) {
        TextComponent component = parse(text);
        if(nextParser != null) {
            nextParser.passNext(text);
        }
        return component;
    }

    abstract TextComponent parse(String text);
}
