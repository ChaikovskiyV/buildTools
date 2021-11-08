package com.vchaikovsky.informationhanding.main;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComposite;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.parser.TextParser;
import com.vchaikovsky.informationhanding.reader.TextRiderFromFile;
import com.vchaikovsky.informationhanding.service.impl.TextEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static String filename = "sources/text.txt";

    public static void main(String[] args) throws HandingException {
        TextEditor editor = new TextEditor();
        String text = TextRiderFromFile
                .getInstance()
                .readText(filename);
        TextParser parser = new TextParser();
        TextComponent component = parser.parse(text);
        TextComposite textComponent = editor.sort((TextComposite) component);
        System.out.print(text + "\n\n");
        System.out.println(editor.findSentenceWithLongestWord((TextComposite) component));
        System.out.println(editor.findConsonantsAndVowelsNumber((TextComposite) component));
        System.out.println(editor.findSameWords((TextComposite) component));
        //System.out.println(textComponent.toString());
        //System.out.println(component.toString().equals(text));
        //System.out.println(component.toString().length() - text.length());
    }
}