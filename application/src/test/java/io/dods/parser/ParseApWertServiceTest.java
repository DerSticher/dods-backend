package io.dods.parser;

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

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseApWertServiceTest extends AbstractParseServiceTest {

    enum Type {POSITIVE, NEGATIVE}

    @Autowired
    private ParseApWertService service;

    @Parameterized.Parameter(0)
    public Type type;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {Type.POSITIVE, ParserSources.getHtml("AP-Wert", "5 Abenteuerpunkte")},
                {Type.NEGATIVE, ParserSources.getHtml("AP-Wert", "-5 Abenteuerpunkte")}
        });
    }

    @Test
    public void testParsePositive() {
        Assume.assumeTrue(type == Type.POSITIVE);

        for (String line : lines) {
            Document document = Jsoup.parse(line);
            int i = service.parseApWert(document);
            assertEquals("line: " + line, 5, i);
        }
    }

    @Test
    public void testParseNegative() {
        Assume.assumeTrue(type == Type.NEGATIVE);

        for (String line : lines) {
            Document document = Jsoup.parse(line);
            int i = service.parseApWert(document);
            assertEquals("line: " + line, -5, i);
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertEquals(0, service.parseApWert(document));
    }

}
