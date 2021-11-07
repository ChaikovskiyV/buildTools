package com.vchaikovsky.informationhanding.entity;

import java.util.List;

public interface TextComponent {
    String toString();
    boolean add(TextComponent component);
    boolean remove(TextComponent component);
    List<TextComponent> getComponents();
    public TextComponentType getComponentType();
}
