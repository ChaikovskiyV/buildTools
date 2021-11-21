package com.vchaikovsky.informationhanding.entity;

public enum TextComponentType {
    TEXT("", "\n"),
    PARAGRAPH("\s\s", ""),
    SENTENCE("", ""),
    LEXEME("", ""),
    WORD("\s", ""),
    EXPRESSION("\s", ""),
    DIGIT("", ""),
    LETTER("", ""),
    PUNCTUATION("", "");

    private final String before;
    private final String after;

    TextComponentType(String before, String after) {
        this.before = before;
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }
}