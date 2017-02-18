package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.ImprovementChart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseImprovementChartServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseImprovementChartService service;

    @Parameterized.Parameter(value = 0)
    public String line;

    @Parameterized.Parameters
    public static String[] parameters() {
        List<String> html = ParserSources.getHtml("Steigerungsfaktor", "C");
        return html.toArray(new String[html.size()]);
    }

    @Test
    public void testParse() {
        Document document = Jsoup.parse(line);
        ImprovementChart improvementChart = service.parseImprovementChart(document);
        assertNotNull("line: " + line, improvementChart);
        assertTrue("line: " + line, "C".equals(improvementChart.name()));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseImprovementChart(document));
    }

}
