package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Cost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseCostServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseCostService service;

    @Parameterized.Parameter(0)
    public String expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"4 AsP + 2 pro minute", ParserSources.getHtml("AsP-Kosten", "4 AsP + 2 pro minute")},
                {"8 KaP", ParserSources.getHtml("KaP-Kosten", "8 KaP")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            Cost cost = service.parseCost(document);
            assertNotNull("line: " + line, cost);
            assertTrue("line: " + line, expected.equals(cost.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseCost(document));
    }

}
