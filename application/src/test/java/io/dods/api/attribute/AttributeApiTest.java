package io.dods.api.attribute;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.Eigenschaft;
import io.dods.model.attribute.Fertigkeit;
import io.dods.model.regeln.Abhangigkeit;
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
public class AttributeApiTest {

    @Autowired
    private AttributeApi attributeApi;

    @Test
    public void getAllEigenschaftenTest() {
        List<Attribut> attributs = attributeApi.get(Eigenschaft.NAME, null, false);
        assertEquals(8, attributs.size());
    }

    @Test
    public void getAllFertigkeitenTest() {
        List<Attribut> attributs = attributeApi.get(Fertigkeit.NAME, null, false);
        assertEquals(59, attributs.size());
    }

    @Test
    public void getMutByIdTest() {
        Attribut attributs = attributeApi.get("1");
        assertEquals("Mut", attributs.getName());
    }

    @Test
    public void getMutByNameTest() {
        List<Attribut> attributs = attributeApi.get(null, "Mut", false);
        assertEquals(1, attributs.size());
        assertEquals("Mut", attributs.get(0).getName());
    }

    @Test
    public void getSubcategoriesOfEigenschaft() {
        List<Attribut> subcategories = attributeApi.getSubcategories("1");
        assertEquals(0, subcategories.size());
    }

    @Test
    public void getSubcategoriesOfVorteil() {
        List<Attribut> attributs = attributeApi.get("Vorteil", "Adel", false);
        Assume.assumeTrue("ignore if Adel was not found! Probably it is not parsed, yet", attributs.size() > 0);

        Attribut adel = attributs.get(0);

        List<Attribut> subcategories = attributeApi.getSubcategories(String.valueOf(adel.getId()));
        assertTrue("Adel should have 3 subcategories", subcategories.size() == 3);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAbhaengigkeitOfEigenschaftTest() {
        // "Mut" should not have any sort of Abhaengigkeit!
        List<Attribut> attributs = attributeApi.get("Eigenschaft", "Mut", false);
        Attribut mut = attributs.get(0);

        attributeApi.getAbhangigkeit(mut.getId());
    }

    @Test
    public void getAbhaengigkeitOfVorteilTest() {
        List<Attribut> attributs = attributeApi.get("Vorteil", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", attributs.size() > 0);
        Attribut reich = attributs.get(0);

        Abhangigkeit abhangigkeit = attributeApi.getAbhangigkeit(reich.getId()); // ID 209 is "Reich"
        assertNotNull("Reich should have some sort of Abhangigkeit!", abhangigkeit);
    }

}
