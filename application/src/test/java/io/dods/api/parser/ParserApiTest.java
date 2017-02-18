package io.dods.api.parser;

import io.dods.model.properties.*;
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

    CombatTechnique combatTechnique = new CombatTechnique();
    LiturgicalChant liturgicalChant = new LiturgicalChant();
    Ritual ritual = new Ritual();
    SpecialAbility specialAbility = new SpecialAbility();
    Bless bless = new Bless();
    Advantage advantage = new Advantage();
    Spell spell = new Spell();
    Cantrip cantrip = new Cantrip();
    Ceremony ceremony = new Ceremony();

    @Before
    public void setup() throws Exception {
        ParseListService mock = Mockito.mock(ParseListService.class);

        Mockito.when(mock.parseCombatTechnique(anyString(), anyBoolean()))
                .thenReturn(Collections.singletonList(combatTechnique));
        Mockito.when(mock.parseLiturgicalChant(anyString()))
                .thenReturn(Collections.singletonList(liturgicalChant));
        Mockito.when(mock.parseRitual(anyString()))
                .thenReturn(Collections.singletonList(ritual));
        Mockito.when(mock.parseSpecialAbility(any(), anyString()))
                .thenReturn(Collections.singletonList(specialAbility));
        Mockito.when(mock.parseBless(anyString()))
                .thenReturn(Collections.singletonList(bless));
        Mockito.when(mock.parseAdvantage(anyString()))
                .thenReturn(Collections.singletonList(advantage));
        Mockito.when(mock.parseSpell(anyString()))
                .thenReturn(Collections.singletonList(spell));
        Mockito.when(mock.parseCantrip(anyString()))
                .thenReturn(Collections.singletonList(cantrip));
        Mockito.when(mock.parseCeremony(anyString()))
                .thenReturn(Collections.singletonList(ceremony));

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
        parseElement(combatTechnique, 2, mockedService.parseKampftechnik());
    }

    @Test
    public void testParseLiturgie() throws Exception {
        parseElement(liturgicalChant, 1, mockedService.parseLiturgie());
    }

    @Test
    public void testParseRitual() throws Exception {
        parseElement(ritual, 1, mockedService.parseRitual());
    }

    @Test
    public void testParseSonderfertigkeit() throws Exception {
        parseElement(specialAbility, 21, mockedService.parseSonderfertigkeit());
    }

    @Test
    public void testParseSegen() throws Exception {
        parseElement(bless, 1, mockedService.parseSegen());
    }

    @Test
    public void testParseVorteil() throws Exception {
        parseElement(advantage, 2, mockedService.parseVorteil());
    }

    @Test
    public void testParseZauber() throws Exception {
        parseElement(spell, 1, mockedService.parseZauber());
    }

    @Test
    public void testParseZaubertrick() throws Exception {
        parseElement(cantrip, 1, mockedService.parseZaubertrick());
    }

    @Test
    public void testParseZeremonie() throws Exception {
        parseElement(ceremony, 1, mockedService.parseZeremonie());
    }
}
