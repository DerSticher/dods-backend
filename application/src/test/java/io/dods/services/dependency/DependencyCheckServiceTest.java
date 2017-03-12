package io.dods.services.dependency;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.heroes.Hero;
import io.dods.model.properties.Property;
import io.dods.model.rules.Dependency;
import io.dods.model.rules.DependencyResult;
import io.dods.services.creation.CreationService;
import io.dods.services.properties.property.PropertyService;
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
public class DependencyCheckServiceTest {

    @Autowired
    private CreationService creationService;

    @Autowired
    private DependencyService dependencyService;

    @Autowired
    private DependencyCheckService dependencyCheckService;

    @Autowired
    private PropertyService propertyService;

    private Hero hero;

    @Test
    public void testSkillOnBaseLevel() {
        hero = creationService.createNew();

        Property fliegen = propertyService.findFirstByName("Fliegen");
        Property mut = propertyService.findFirstByName("Mut");
        Dependency fliegenDependency = dependencyService.findByEffectProperty(fliegen);
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        assertEquals("Dependency for FLIEGEN should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());
    }

    @Test
    public void testSkillMaxDistanceToCheck() {
        hero = creationService.createNew();

        Property fliegen = propertyService.findFirstByName("Fliegen");
        Property mut = propertyService.findFirstByName("Mut");
        Dependency fliegenDependency = dependencyService.findByEffectProperty(fliegen);
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        hero.addProperty(fliegen, 10);

        assertEquals("Dependency for FLIEGEN should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());

        hero.addProperty(fliegen, 11);
        assertEquals("Dependency for FLIEGEN should NOT be fulfilled",
                DependencyResult.Status.LEVEL_EXCEEDED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());
    }

    @Test
    public void testSkillMaxDistanceToCheckWithImprovedCheck() {
        hero = creationService.createNew();

        Property fliegen = propertyService.findFirstByName("Fliegen");
        Property mut = propertyService.findFirstByName("Mut");
        Dependency fliegenDependency = dependencyService.findByEffectProperty(fliegen);
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        hero.addProperty(mut, 10);
        hero.addProperty(fliegen, 12);

        assertEquals("Dependency for FLIEGEN should NOT be fulfilled since it is exceeding the experience limitation",
                DependencyResult.Status.LEVEL_EXCEEDED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());

        hero.setIgnoreExperience(true);

        assertEquals("Dependency for FLIEGEN should be fulfilled since the experience limitation is deactivated",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());

        hero.addProperty(fliegen, 13);
        assertEquals("Dependency for FLIEGEN should NOT be fulfilled anymore",
                DependencyResult.Status.LEVEL_EXCEEDED, dependencyCheckService.checkDependency(fliegenDependency, hero).getStatus());
    }

    @Test
    public void testAttributeOnBaseLevel() {
        hero = creationService.createNew();

        Property mut = propertyService.findFirstByName("Mut");
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        assertEquals("Dependency for MUT should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(mutDependency, hero).getStatus());
    }

    @Test
    public void testAttributeMaxLevel() {
        hero = creationService.createNew();

        Property mut = propertyService.findFirstByName("Mut");
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        hero.addProperty(mut, 14);

        assertEquals("Dependency for MUT should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(mutDependency, hero).getStatus());

        hero.addProperty(mut, 15);

        assertEquals("Dependency for MUT should NOT be fulfilled since it exceeds the maximum level restriction",
                DependencyResult.Status.LEVEL_EXCEEDED, dependencyCheckService.checkDependency(mutDependency, hero).getStatus());
    }

    @Test
    public void testAttributeWithImprovedLevel() {
        hero = creationService.createNew();

        Property mut = propertyService.findFirstByName("Mut");
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        hero.addProperty(mut, 15);
        hero.setStartingExperience(creationService.getExperienceById(4));

        assertEquals("Dependency for MUT should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(mutDependency, hero).getStatus());
    }

    @Test
    public void testAttributeWithIgnoredLevel() {
        hero = creationService.createNew();

        Property mut = propertyService.findFirstByName("Mut");
        Dependency mutDependency = dependencyService.findByEffectProperty(mut);

        hero.addProperty(mut, 15);
        hero.setIgnoreExperience(true);

        assertEquals("Dependency for MUT should be fulfilled",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(mutDependency, hero).getStatus());
    }

    @Test
    public void testAttributeMaxTotal() {
        hero = creationService.createNew();

        Property mu = propertyService.findFirstByName("Mut");
        Property kl = propertyService.findFirstByName("Klugheit");
        Property in = propertyService.findFirstByName("Intuition");
        Property ch = propertyService.findFirstByName("Charisma");
        Property ff = propertyService.findFirstByName("Fingerfertigkeit");
        Property ge = propertyService.findFirstByName("Gewandtheit");
        Property ko = propertyService.findFirstByName("Konstitution");
        Property kk = propertyService.findFirstByName("Körperkraft");

        hero.addProperty(mu, 14);
        hero.addProperty(kl, 14);
        hero.addProperty(in, 14);
        hero.addProperty(ch, 14);
        hero.addProperty(ff, 14);
        hero.addProperty(ge, 14);
        hero.addProperty(ko, 14);
        hero.addProperty(kk, 14);
        // total = 8 * 14 = 112 -> exceeding level limitation!

        List<Dependency> obligatoryList = dependencyService.findAllObligatory();
        Dependency experienceDependency = obligatoryList.get(0);

        assertNotNull(experienceDependency);

        assertEquals("Dependency should not be fulfilled since it is exceeding the attribute total experience condition",
                DependencyResult.Status.OBLIGATORY_NOT_FULFILLED, dependencyCheckService.checkDependency(experienceDependency, hero).getStatus());

        hero.setIgnoreExperience(true);

        assertEquals("Dependency should now be fulfilled since the limitation check is deactivated",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(experienceDependency, hero).getStatus());

        hero.setIgnoreExperience(false);

        hero.addProperty(mu, 11);
        hero.addProperty(kl, 11);
        hero.addProperty(in, 11);
        hero.addProperty(ch, 11);
        // total = 4 * 11 + 4 * 14= 100 -> valid

        assertEquals("Dependency should be fulfilled since the total sum is <= the experience limitation",
                DependencyResult.Status.FULFILLED, dependencyCheckService.checkDependency(experienceDependency, hero).getStatus());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testAdvantage() {
        hero = creationService.createNew();

        Property advantage = propertyService.findFirstByName("Hass auf");
        dependencyService.findByEffectProperty(advantage);
    }
}
