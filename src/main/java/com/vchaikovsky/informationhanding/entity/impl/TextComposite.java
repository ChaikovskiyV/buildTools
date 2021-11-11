package com.vchaikovsky.informationhanding.entity.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> components = new ArrayList<>();
    private TextComponentType componentType;

    public TextComposite(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public TextComponentType getComponentType() {
        return componentType;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextComposite)) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return components.equals(that.components) && (componentType != null ? componentType == that.componentType : that.componentType == null);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * components.hashCode();
        result = result * first * componentType.hashCode();

        return result;
    }

    @Override
    public String toString() {
        components.removeIf(c -> c == null);
        StringBuilder strBuilder = new StringBuilder(componentType.getBefore());
        components.forEach(t -> strBuilder
                .append(t.toString())
                .append(componentType.getAfter()));

        return strBuilder.toString();
    }
}