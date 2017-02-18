package io.dods.api.properties;

import io.dods.api.exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class AbilityApiImplTest {

    @Autowired
    private AbilityApiImpl api;

    @Test(expected = ResourceNotFoundException.class)
    public void testGetIllegalValue() {
        api.get(0);
    }

    @Test
    public void testGetSingle() {
        assertEquals("Mut", api.get(1).getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetDifferentType() {
        api.get(9); //9 is "Fliegen"
    }

}
