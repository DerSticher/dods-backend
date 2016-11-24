package io.dods.model.helden;

import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.Vorteil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testAddFilledEigenschaft() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(10, "HeldTest Attribut1");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        held.addEigenschaft(eigenschaft);

        assertNotNull("Held should have a TestAttribut", held.getEigenschaft(attribut));
    }

    @Test
    public void testEigenschaftActivated() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(10, "HeldTest Attribut2");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        held.addEigenschaft(eigenschaft);

        CharakterEigenschaft charakterEigenschaft = held.getEigenschaft(attribut);
        assertNotNull(charakterEigenschaft); // already tested in separated test. Just tested to remove warnings

        assertTrue(String.format("%s should be activated by default", CharakterEigenschaft.class.getSimpleName()),
                charakterEigenschaft.isActivated());
    }

    @Test
    public void testEigenschaftAp() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new TestableAttribut(1, 16, "HeldTest Attribut3");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        held.addEigenschaft(eigenschaft);
        assertEquals(16, held.getAp());

        CharakterEigenschaft eigenschaft2 = new CharakterEigenschaft();
        eigenschaft2.setAttribut(new TestableAttribut(2, 32, "HeldTest Attribut4"));
        eigenschaft2.setLevel(1);

        held.addEigenschaft(eigenschaft2);
        assertEquals(48, held.getAp());
    }

    @Test
    public void testEigenschaftApAfterDeactivation() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(16, "HeldTest Attribut3");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        held.addEigenschaft(eigenschaft);
        assertEquals(16, held.getAp());

        held.getEigenschaft(attribut).setActivated(false);
        assertEquals(0, held.getAp());
    }

    @Test
    public void testEigenschaftApAfterRemovedValue() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(16, "HeldTest Attribut3");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        held.addEigenschaft(eigenschaft);
        assertEquals(16, held.getAp());

        CharakterEigenschaft eigenschaft2 = new CharakterEigenschaft();
        eigenschaft2.setAttribut(attribut);
        eigenschaft2.setActivated(false);

        held.addEigenschaft(eigenschaft2);
        assertEquals(String.format("after deactivating an %s it should not cost anything",
                CharakterEigenschaft.class.getSimpleName()),
                0, held.getAp());
    }

    @Test
    public void testGetEigenschaften() {
        Held held = new Held();

        CharakterEigenschaft eigenschaft = new CharakterEigenschaft();
        Attribut attribut = new Vorteil(16, "HeldTest Attribut5");
        eigenschaft.setAttribut(attribut);
        eigenschaft.setLevel(1);

        assertEquals(0, held.getActivatedEigenschaften().size());
        held.addEigenschaft(eigenschaft);

        List<CharakterEigenschaft> activatedEigenschaften = held.getActivatedEigenschaften();
        assertEquals(1, activatedEigenschaften.size());
        assertEquals(eigenschaft, activatedEigenschaften.get(0));
    }

    @Test
    public void testSetName() {
        Held held = new Held();

        assertEquals("", held.getName());
        held.setName("Batman");
        assertEquals("Batman", held.getName());
    }
}
