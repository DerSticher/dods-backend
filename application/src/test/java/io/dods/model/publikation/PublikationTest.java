package io.dods.model.publikation;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
public class PublikationTest {

    @Test
    public void testConstructor() {
        Werk werk = new Werk("TestWerk");

        Publikation publikation = new Publikation(werk, 1337);

        assertTrue("TestWerk".equals(publikation.getWerk().getName()));
        assertEquals(1337, publikation.getPage());
    }

    @Test
    public void testId() throws Exception {
        Publikation publikation = new Publikation();

        assertNull(publikation.getId());
        publikation.setId(1L);
        assertTrue(1L == publikation.getId());
    }

    @Test
    public void testWerk() throws Exception {
        Publikation publikation = new Publikation();

        assertNull(publikation.getWerk());
        Werk werk = new Werk();
        werk.setName("TestWerk");

        publikation.setWerk(werk);

        assertTrue(werk == publikation.getWerk());
    }

    @Test
    public void testDetails() throws Exception {
        Publikation publikation = new Publikation();

        assertNull(publikation.getWerk());
        publikation.setPage(137);

        assertEquals(137, publikation.getPage());
    }


}
