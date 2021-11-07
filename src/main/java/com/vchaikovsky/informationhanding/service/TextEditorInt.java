package com.vchaikovsky.informationhanding.service;

import com.vchaikovsky.informationhanding.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextEditorInt {
    TextComposite sort(TextComposite textComposite);
    String findSentenceWithLongestWord(TextComposite textComposite);
    TextComposite removeSentenceWithNumberWordsLess(TextComposite textComposite, int wordsNumbers);
    List<String> findSameWords(TextComposite textComposite);
    Map<String, Integer> findConsonantsAndVowelsNumber(TextComposite textComposite);
}
