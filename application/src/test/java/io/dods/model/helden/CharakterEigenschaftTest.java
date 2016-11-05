package io.dods.model.helden;

import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.Vorteil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Gottschalk
 */
public class CharakterEigenschaftTest {

    @Test
    public void testEigenschaftActivatedByDefault() {
        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(5, "CharakterEigenschaftTest Attribut1");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(0);

        assertTrue(String.format("%s should be activated by default", CharakterEigenschaft.class.getSimpleName()),
                eigenschaft.isActivated());
    }

}
