package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Aspect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assume;
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
public class ParseAspectServiceTest extends AbstractParseServiceTest {

    enum Type {ALLGEMEIN}

    @Autowired
    private ParseAspectService service;

    @Parameterized.Parameter(0)
    public Type type;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {Type.ALLGEMEIN, ParserSources.getHtml("Aspekt", "allgemein")}
        });
    }

    @Test
    public void testParse() {
        Assume.assumeTrue(type == Type.ALLGEMEIN);

        for (String line : lines) {
            Document document = Jsoup.parse(line);
            List<Aspect> aspectList = service.parseAspect(document);

            assertEquals("line: " + line, 1, aspectList.size());

            Aspect aspect = aspectList.get(0);
            assertTrue("line: " + line, "allgemein".equals(aspect.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertEquals("This list should be empty", 0, service.parseAspect(document).size());
    }

}
