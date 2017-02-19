package io.dods.services.parser.valueParser;

import io.dods.model.properties.Attribute;
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
public class ParsePrimaryAttributesServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParsePrimaryAttributesService service;

    @Parameterized.Parameter(0)
    public List<String> expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList("Mut"), ParserSources.getHtml("Leiteigenschaft", "Mut")},
                {Arrays.asList("Körperkraft"), ParserSources.getHtml("Leiteigenschaft", "Körperkraft")},
                {Arrays.asList("Fingerfertigkeit"), ParserSources.getHtml("Leiteigenschaft", "Fingerfertigkeit")},
                {Arrays.asList("Gewandtheit", "Körperkraft"), ParserSources.getHtml("Leiteigenschaft", "Gewandtheit/Körperkraft")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);

            List<Attribute> primaryAttributes = service.parsePrimaryAttributes(document);
            assertNotNull("line: " + line, primaryAttributes);
            assertEquals("line: " + line, expected.size(), primaryAttributes.size());


            for (int i = 0; i < primaryAttributes.size(); i++) {
                Attribute actualResult = primaryAttributes.get(i);
                String expectedValue = expected.get(i);

                assertTrue("Expected \"" + expectedValue + "\" but was: " + actualResult.getName(), expectedValue.equals(actualResult.getName()));
            }
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parsePrimaryAttributes(document));
    }

}
