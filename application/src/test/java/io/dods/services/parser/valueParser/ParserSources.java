package io.dods.services.parser.valueParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
class ParserSources {

    public static List<String> getHtml(String name, String value) {
        List<String> samples = new ArrayList<>();

        samples.add("<html><body>" + name + ":" + value + "</body></html>");
        samples.add("<html><body>" + name + ": " + value + "</body></html>");
        samples.add("<html><body><strong>" + name + "</strong>: " + value + "</body></html>");
        samples.add("<html><body><strong>" + name + ":</strong> " + value + "</body></html>");
        samples.add("<html><body><strong>" + name + ": </strong>" + value + "</body></html>");

        return samples;
    }
}
