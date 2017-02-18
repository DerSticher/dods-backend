package io.dods.api.properties;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.properties.Ability;
import io.dods.model.properties.Property;
import io.dods.model.properties.Skill;
import io.dods.model.rules.Dependency;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class PropertyApiTest {

    @Autowired
    private PropertyApi propertyApi;

    @Test
    public void getAllEigenschaftenTest() {
        List<Property> properties = propertyApi.get(Ability.NAME, null, false);
        assertEquals(8, properties.size());
    }

    @Test
    public void getAllFertigkeitenTest() {
        List<Property> properties = propertyApi.get(Skill.NAME, null, false);
        assertEquals(59, properties.size());
    }

    @Test
    public void getMutByIdTest() {
        Property attributs = propertyApi.get("1");
        assertEquals("Mut", attributs.getName());
    }

    @Test
    public void getMutByNameTest() {
        List<Property> properties = propertyApi.get(null, "Mut", false);
        assertEquals(1, properties.size());
        assertEquals("Mut", properties.get(0).getName());
    }

    @Test
    public void getSubcategoriesOfEigenschaft() {
        List<Property> subcategories = propertyApi.getSubcategories("1");
        assertEquals(0, subcategories.size());
    }

    @Test
    public void getSubcategoriesOfVorteil() {
        List<Property> properties = propertyApi.get("Vorteil", "Adel", false);
        Assume.assumeTrue("ignore if Adel was not found! Probably it is not parsed, yet", properties.size() > 0);

        Property adel = properties.get(0);

        List<Property> subcategories = propertyApi.getSubcategories(String.valueOf(adel.getId()));
        assertTrue("Adel should have 3 subcategories", subcategories.size() == 3);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAbhaengigkeitOfEigenschaftTest() {
        // "Mut" should not have any sort of Abhaengigkeit!
        List<Property> properties = propertyApi.get("Eigenschaft", "Mut", false);
        Property mut = properties.get(0);

        propertyApi.getAbhangigkeit(mut.getId());
    }

    @Test
    public void getAbhaengigkeitOfVorteilTest() {
        List<Property> properties = propertyApi.get("Vorteil", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", properties.size() > 0);
        Property reich = properties.get(0);

        Dependency dependency = propertyApi.getAbhangigkeit(reich.getId()); // ID 209 is "Reich"
        assertNotNull("Reich should have some sort of Dependency!", dependency);
    }

}
