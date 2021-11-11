package com.vchaikovsky.informationhanding.entity.impl;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    static final Logger logger = LogManager.getLogger();
    private TextComponentType componentType;
    private char symbol;

    public SymbolLeaf(TextComponentType componentType, char symbol) {
        this.componentType = componentType;
        this.symbol = symbol;
    }

    public TextComponentType getComponentType() {
        return componentType;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.error(component + " can't be added due to " + this + " doesn't contain any components");
        throw new UnsupportedOperationException(component + " can't be added due to " + this + " doesn't contain any components");
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.error(component + " can't be removed due to " + this + " doesn't contain any components");
        throw new UnsupportedOperationException(component + " can't be removed due to " + this + " doesn't contain any components");
    }

    @Override
    public List<TextComponent> getComponents() {
        logger.error(this + " doesn't contain any components");
        throw new UnsupportedOperationException(this + " doesn't contain any components");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SymbolLeaf)) {
            return false;
        }
        SymbolLeaf that = (SymbolLeaf) o;
        return symbol == that.symbol && (componentType != null ? componentType == that.componentType : that.componentType == null);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * Character.hashCode(symbol);
        result = result * first + (componentType != null ? componentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}