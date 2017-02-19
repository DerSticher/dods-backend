package io.dods;

import io.dods.model.properties.Attribute;
import io.dods.services.properties.ability.AttributeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.util.AssertionErrors.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class EncodingTest {

    @Autowired
    private AttributeService attributeService;

    @Test
    public void testEncoding() {
        Attribute koerperkraft = attributeService.findById(8L);
        assertEquals("Körperkraft should have 11 characters! -> probably encoding problems",
                11, koerperkraft.getName().length());
        assertTrue("Names do not match", "Körperkraft".equals(koerperkraft.getName()));
    }

}
