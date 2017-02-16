package io.dods.api.parser;

import io.dods.model.attribute.*;
import io.dods.services.parser.ParseListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParserApiTest {

    ParserApi mockedService;

    Kampftechnik kampftechnik = new Kampftechnik();
    Liturgie liturgie = new Liturgie();
    Ritual ritual = new Ritual();
    Sonderfertigkeit sonderfertigkeit = new Sonderfertigkeit();
    Segen segen = new Segen();
    Vorteil vorteil = new Vorteil();
    Zauber zauber = new Zauber();
    Zaubertrick zaubertrick = new Zaubertrick();
    Zeremonie zeremonie = new Zeremonie();

    @Before
    public void setup() throws Exception {
        ParseListService mock = Mockito.mock(ParseListService.class);

        Mockito.when(mock.parseKampftechnik(anyString(), anyBoolean()))
                .thenReturn(Collections.singletonList(kampftechnik));
        Mockito.when(mock.parseLiturgie(anyString()))
                .thenReturn(Collections.singletonList(liturgie));
        Mockito.when(mock.parseRitual(anyString()))
                .thenReturn(Collections.singletonList(ritual));
        Mockito.when(mock.parseSonderfertigkeit(any(), anyString()))
                .thenReturn(Collections.singletonList(sonderfertigkeit));
        Mockito.when(mock.parseSegen(anyString()))
                .thenReturn(Collections.singletonList(segen));
        Mockito.when(mock.parseVorteil(anyString()))
                .thenReturn(Collections.singletonList(vorteil));
        Mockito.when(mock.parseZauber(anyString()))
                .thenReturn(Collections.singletonList(zauber));
        Mockito.when(mock.parseZaubertrick(anyString()))
                .thenReturn(Collections.singletonList(zaubertrick));
        Mockito.when(mock.parseZeremonie(anyString()))
                .thenReturn(Collections.singletonList(zeremonie));

        this.mockedService = new ParserApi(mock, null);
    }

    private <T> void parseElement(T expectedObject, int expectedCount, List<T> result) {
        assertEquals("expected " + expectedCount + " result but got " + result.size() + ": " + String.valueOf(result),
                expectedCount, result.size());

        T element = result.get(0);
        assertTrue(expectedObject == element);
    }

    @Test
    public void testParseAll() throws Exception {
        mockedService.parseAll();
    }

    @Test
    public void testParseKampftechnik() throws Exception {
        parseElement(kampftechnik, 2, mockedService.parseKampftechnik());
    }

    @Test
    public void testParseLiturgie() throws Exception {
        parseElement(liturgie, 1, mockedService.parseLiturgie());
    }

    @Test
    public void testParseRitual() throws Exception {
        parseElement(ritual, 1, mockedService.parseRitual());
    }

    @Test
    public void testParseSonderfertigkeit() throws Exception {
        parseElement(sonderfertigkeit, 21, mockedService.parseSonderfertigkeit());
    }

    @Test
    public void testParseSegen() throws Exception {
        parseElement(segen, 1, mockedService.parseSegen());
    }

    @Test
    public void testParseVorteil() throws Exception {
        parseElement(vorteil, 2, mockedService.parseVorteil());
    }

    @Test
    public void testParseZauber() throws Exception {
        parseElement(zauber, 1, mockedService.parseZauber());
    }

    @Test
    public void testParseZaubertrick() throws Exception {
        parseElement(zaubertrick, 1, mockedService.parseZaubertrick());
    }

    @Test
    public void testParseZeremonie() throws Exception {
        parseElement(zeremonie, 1, mockedService.parseZeremonie());
    }
}
