package io.dods.model.helden;

import io.dods.model.attribute.Attribut;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Richard Gottschalk
 */
public class HeldTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyEigenschaft() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        held.addEigenschaft(eigenschaft);

        assertFalse(String.format("expected an IllegalArgumentException for adding a %s without %s",
                CharakterEigenschaft.class.getSimpleName(),
                Attribut.class.getSimpleName()),
                true);
    }
}
