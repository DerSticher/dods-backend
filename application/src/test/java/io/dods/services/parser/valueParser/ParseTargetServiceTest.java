package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Target;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseTargetServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseTargetService service;

    @Parameterized.Parameter(0)
    public List<String> expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList("Feen"), ParserSources.getHtml("Zielkategorie", "Feen")},
                {Arrays.asList("Chimären", "Feen"), ParserSources.getHtml("Zielkategorie", "Chimären und Feen")},
                {Arrays.asList("Chimären", "Feen"), ParserSources.getHtml("Zielkategorie", "Chimären oder Feen")},
                {Arrays.asList("Golems", "Pflanzen", "Profane Objekte"), ParserSources.getHtml("Zielkategorie", "Golems, Pflanzen oder Profane Objekte")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            List<Target> targetList = service.parseTargets(document);

            assertEquals("line: " + line, expected.size(), targetList.size());

            for (int i = 0; i < targetList.size(); i++) {
                Target actualResult = targetList.get(i);
                String expectedValue = expected.get(i);

                assertTrue("Expected \"" + expectedValue + "\" but was: " + actualResult.getName(), expectedValue.equals(actualResult.getName()));
            }
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertEquals("This list should be empty", 0, service.parseTargets(document).size());
    }

}
