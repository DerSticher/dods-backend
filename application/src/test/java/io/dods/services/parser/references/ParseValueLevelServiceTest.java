package io.dods.services.parser.references;

import io.dods.services.parser.model.ParsedValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseValueLevelServiceTest {

    @Autowired
    ParseValueLevelService service;

    @Test
    public void testWithoutLevel() throws Exception {
        ParsedValue original = new ParsedValue();
        original.setName("My Value");

        List<ParsedValue> values = service.checkForLevels(original);
        assertEquals("This method should not return data for not existing levels!", 0, values.size());
    }

    @Test
    public void testLevel3() throws Exception {
        ParsedValue original = new ParsedValue();
        original.setName("My Value I-III");

        List<ParsedValue> values = service.checkForLevels(original);
        assertEquals("this method should not return the original ParsedValue instance!", 3, values.size());

//        ParsedValue parsedValue = values.get(0);
//        assertTrue("Name should be \"My Value\" but was " + parsedValue.getName(), "My Value".equals(parsedValue.getName()));

        ParsedValue level1 = values.get(0);
        assertTrue("Name should be \"I\"", "I".equals(level1.getName()));

        ParsedValue level2 = values.get(1);
        assertTrue("Name should be \"II\"", "II".equals(level2.getName()));

        ParsedValue level3 = values.get(2);
        assertTrue("Name should be \"III\"", "III".equals(level3.getName()));
    }

    @Test
    public void testDecodeSingle() throws Exception {
        assertEquals(ParseValueLevelService.decodeSingle('I'), 1);
        assertEquals(ParseValueLevelService.decodeSingle('V'), 5);
        assertEquals(ParseValueLevelService.decodeSingle('X'), 10);
        assertEquals(ParseValueLevelService.decodeSingle('L'), 50);
        assertEquals(ParseValueLevelService.decodeSingle('C'), 100);
        assertEquals(ParseValueLevelService.decodeSingle('D'), 500);
        assertEquals(ParseValueLevelService.decodeSingle('M'), 1000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDecodeSingleInvalidValue() throws Exception {
        ParseValueLevelService.decodeSingle('-');
    }

    @Test
    public void testDecode() throws Exception {
        assertEquals(ParseValueLevelService.decode("I"), 1);
        assertEquals(ParseValueLevelService.decode("IV"), 4);
        assertEquals(ParseValueLevelService.decode("V"), 5);
        assertEquals(ParseValueLevelService.decode("VI"), 6);
        assertEquals(ParseValueLevelService.decode("VIII"), 8);
        assertEquals(ParseValueLevelService.decode("IX"), 9);
        assertEquals(ParseValueLevelService.decode("XI"), 11);
        assertEquals(ParseValueLevelService.decode("X"), 10);
        assertEquals(ParseValueLevelService.decode("L"), 50);
        assertEquals(ParseValueLevelService.decode("LXX"), 70);
        assertEquals(ParseValueLevelService.decode("XC"), 90);
        assertEquals(ParseValueLevelService.decode("C"), 100);
        assertEquals(ParseValueLevelService.decode("CXV"), 115);
        assertEquals(ParseValueLevelService.decode("D"), 500);
        assertEquals(ParseValueLevelService.decode("M"), 1000);
    }
}
