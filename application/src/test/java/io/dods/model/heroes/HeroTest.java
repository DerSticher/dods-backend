package io.dods.model.heroes;

import io.dods.model.properties.Property;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Richard Gottschalk
 */
public class HeroTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyEigenschaft() {
        Hero hero = new Hero();

        HeroProperty eigenschaft = new HeroProperty();
        hero.addProperty(eigenschaft);

        assertFalse(String.format("expected an IllegalArgumentException for adding a %s without %s",
                HeroProperty.class.getSimpleName(),
                Property.class.getSimpleName()),
                true);
    }
}
