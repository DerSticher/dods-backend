package io.dods.model.publikation;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
public class WerkTest {

    @Test
    public void testConstructor() {
        Werk werk = new Werk("TestWerk");

        assertTrue("TestWerk".equals(werk.getName()));
    }

    @Test
    public void testId() throws Exception {
        Werk werk = new Werk();

        assertNull(werk.getId());
        werk.setId(1L);
        assertTrue(1L == werk.getId());
    }

    @Test
    public void testDetails() throws Exception {
        Werk werk = new Werk();

        assertNull(werk.getName());
        werk.setName("TestWerk");

        assertTrue("TestWerk".equals(werk.getName()));
    }

}
