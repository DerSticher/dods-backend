package io.dods.services.parser.model;

import io.dods.model.attribute.Attribut;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class ParsedData<T extends Attribut> {

    private T attribut;

    private List<T> children = new ArrayList<>();

    public ParsedData(T attribut, List<T> children) {
        this.attribut = attribut;
        this.children = children;
    }

    public T getAttribut() {
        return attribut;
    }

    public List<T> getChildren() {
        return children;
    }

    public void addChild(T attribut) {
        children.add(attribut);
    }

    public boolean isEmpty() {
        return attribut == null && children.size() == 0;
    }

}
