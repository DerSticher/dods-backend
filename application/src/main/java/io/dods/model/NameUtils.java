package io.dods.model;

/**
 * @author Richard Gottschalk
 */
public class NameUtils {

    public static String createIdentifier(String name) {
        name = name.toLowerCase();
        name = name.replaceAll("[^a-z1-9 ]", "");
        return name.replaceAll("[ ]", "-");
    }

}
