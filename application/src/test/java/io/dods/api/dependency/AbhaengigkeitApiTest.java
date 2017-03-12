package io.dods.api.dependency;

import io.dods.api.dependency.model.CreateDependency;
import io.dods.api.dependency.model.CreateCondition;
import io.dods.api.dependency.model.CreateEffect;
import io.dods.api.properties.PropertyApi;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.properties.Advantage;
import io.dods.model.properties.Property;
import io.dods.model.conditions.Condition;
import io.dods.model.rules.Dependency;
import io.dods.services.properties.advantage.AdvantageService;
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
    private DependencyApi api;

    @Autowired
    private PropertyApi propertyApi;

    @Autowired
    private AdvantageService advantageService;

    @Test
    public void getAbhaengigkeitByIdTest() {
        Dependency dependency = api.get(1);
        assertNotNull(dependency);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAbhaengigkeitByIllegalId() {
        api.get(0);
    }

    @Test
    public void findAllTest() {
        List<Dependency> abhangigkeiten = api.find(null);
        assertTrue("There should many childs", abhangigkeiten.size() > 1);
    }

    @Test
    public void findOneTest() {
        List<Property> properties = propertyApi.get("Advantage", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", properties.size() > 0);
        Property reich = properties.get(0);

        List<Dependency> dependencies = api.find(reich.getId());
        assertEquals("There should be only one child", 1, dependencies.size());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void findOneWithIllegalIdTest() {
        api.find(-1L);
    }

    @Test
    public void putTest() {
        List<Property> properties = propertyApi.get("Advantage", "Reich", false);
        Assume.assumeTrue("ignore if Reich was not found! Probably it is not parsed, yet", properties.size() > 0);
        Property reich = properties.get(0);

        List<Dependency> abhangigkeiten = api.find(reich.getId());
        Dependency dependency = abhangigkeiten.get(0);

        assertNotNull("Condition should not be null", dependency.getCondition());
        Condition condition = dependency.getCondition();
        dependency.setCondition(null);
        api.put(dependency.getId(), dependency);

        // check for deleted value
        List<Dependency> valuesWithoutEffekt = api.find(reich.getId());
        Dependency valueWithoutEffekt = valuesWithoutEffekt.get(0);
        assertNull("Condition should be null", valueWithoutEffekt.getCondition());

        // set effekt
        dependency.setCondition(condition);
        api.put(dependency.getId(), dependency);

        // check for inserted value
        List<Dependency> valuesWithEffekt = api.find(reich.getId());
        Dependency valueWithEffekt = valuesWithEffekt.get(0);
        assertNotNull("Condition should now be set again", valueWithEffekt.getCondition());
    }

    @Test
    public void testPostAndDeleteTest() {
        Advantage advantage = advantageService.save(new Advantage());

        CreateCondition createCondition = new CreateCondition();
        createCondition.setLevel(15);
        createCondition.setPropertyId(1);
        createCondition.setType(CreateCondition.Type.MIN);

        CreateEffect createEffect = new CreateEffect();
        createEffect.setPropertyId(advantage.getId());
        createEffect.setAbsoluteLevel(20);

        CreateDependency createDependency = new CreateDependency();
        createDependency.setCreateCondition(createCondition);
        createDependency.setCreateEffect(createEffect);

        Dependency dependency = api.post(createDependency);
        assertNotNull(dependency);
        assertNotNull(dependency.getId());

        assertEquals("there should be exactly one Dependency", 1L, api.find(advantage.getId()).size());

        api.delete(dependency.getId());
        try {
            assertEquals("there should not be an Dependency anymore", 0L, api.find(advantage.getId()).size());
            assertTrue(false); // should not reach this code!
        } catch (ResourceNotFoundException expected) {
            assertTrue(true);
        }
    }

}
