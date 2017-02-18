package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.ImprovementChart;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseImprovementChartService {

    private static final Pattern PATTERN = Pattern.compile("Steigerungsfaktor ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([A-E]+)");

    public ImprovementChart parseImprovementChart(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return ImprovementChart.findOrThrow(matcher.group(1));
        }
        return null;
    }

}
