package io.dods.api.abhangigkeit;

import io.dods.api.abhangigkeit.model.CreateAbhangigkeit;
import io.dods.api.abhangigkeit.model.CreateBedingung;
import io.dods.api.abhangigkeit.model.CreateEffekt;
import io.dods.api.attribute.AttributeApi;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.attribute.Attribut;
import io.dods.model.bedingungen.Bedingung;
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
public class AbhaengigkeitApiTest {

    @Autowired
    private AbhangigkeitApi api;

    @Autowired
    private AttributeApi attributeApi;

    @Test
    public void getAbhaengigkeitByIdTest() {
        Abhangigkeit abhangigkeit = api.get(1);
        assertNotNull(abhangigkeit);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAbhaengigkeitByIllegalId() {
        api.get(0);
    }

    @Test
    public void findAllTest() {
        List<Abhangigkeit> abhangigkeiten = api.find(null);
        assertTrue("There should many childs", abhangigkeiten.size() > 1);
    }

    @Test
    public void findOneTest() {
        List<Attribut> attributs = attributeApi.get("Vorteil", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", attributs.size() > 0);
        Attribut reich = attributs.get(0);

        List<Abhangigkeit> abhangigkeits = api.find(reich.getId());
        assertEquals("There should be only one child", 1, abhangigkeits.size());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void findOneWithIllegalIdTest() {
        api.find(1L);// 1 is "Mut"
    }

    @Test
    public void putTest() {
        List<Attribut> attributs = attributeApi.get("Vorteil", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", attributs.size() > 0);
        Attribut reich = attributs.get(0);

        List<Abhangigkeit> abhangigkeiten = api.find(reich.getId());
        Abhangigkeit abhangigkeit = abhangigkeiten.get(0);

        assertNotNull("Bedingung should not be null", abhangigkeit.getBedingung());
        Bedingung bedingung = abhangigkeit.getBedingung();
        abhangigkeit.setBedingung(null);
        api.put(abhangigkeit.getId(), abhangigkeit);

        // check for deleted value
        List<Abhangigkeit> valuesWithoutEffekt = api.find(reich.getId());
        Abhangigkeit valueWithoutEffekt = valuesWithoutEffekt.get(0);
        assertNull("Bedingung should be null", valueWithoutEffekt.getBedingung());

        // set effekt
        abhangigkeit.setBedingung(bedingung);
        api.put(abhangigkeit.getId(), abhangigkeit);

        // check for inserted value
        List<Abhangigkeit> valuesWithEffekt = api.find(reich.getId());
        Abhangigkeit valueWithEffekt = valuesWithEffekt.get(0);
        assertNotNull("Bedingung should now be set again", valueWithEffekt.getBedingung());
    }

    @Test
    public void testPostAndDeleteTest() {
        CreateBedingung createBedingung = new CreateBedingung();
        createBedingung.setLevel(15);
        createBedingung.setAttributId(1);
        createBedingung.setType(CreateBedingung.Type.MIN);

        CreateEffekt createEffekt = new CreateEffekt();
        createEffekt.setAttributId(9);
        createEffekt.setLevel(20);

        CreateAbhangigkeit createAbhangigkeit = new CreateAbhangigkeit();
        createAbhangigkeit.setCreateBedingung(createBedingung);
        createAbhangigkeit.setCreateEffekt(createEffekt);

        Abhangigkeit abhangigkeit = api.post(createAbhangigkeit);
        assertNotNull(abhangigkeit);
        assertNotNull(abhangigkeit.getId());

        assertEquals("there should be exactly one Abhangigkeit", 1L, api.find(9L).size());

        api.delete(abhangigkeit.getId());
        try {
            assertEquals("there should not be an Abhangigkeit anymore", 0L, api.find(9L).size());
            assertTrue(false); // should not reach this code!
        } catch (ResourceNotFoundException expected) {
            assertTrue(true);
        }
    }

}
