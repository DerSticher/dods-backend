package io.dods.model.rules.effects;

import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import io.dods.services.creation.CreationService;
import io.dods.services.held.HeroValidationService;
import io.dods.services.properties.property.PropertyService;
import io.dods.services.properties.species.SpeciesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class EffectTest {

    @Autowired
    private CreationService creationService;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private HeroValidationService heroValidationService;

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testHuman() {
        Hero hero = creationService.createNew();
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(speciesService.findByName("Mensch")));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Körperkraft"), 14));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Körperkraft"), 15));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Mut"), 15));
        assertFalse(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Mut"), 14));
        assertTrue(heroValidationService.isValid(hero));
    }

    @Test
    public void testDwarf() {
        Hero hero = creationService.createNew();

        hero.addProperty(new HeroProperty(speciesService.findByName("Zwerg")));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Konstitution"), 14));
        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Körperkraft"), 14));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Konstitution"), 15));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Körperkraft"), 15));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Gewandtheit"), 12));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Gewandtheit"), 13));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Charisma"), 12));
        assertTrue(heroValidationService.isValid(hero));

        hero.addProperty(new HeroProperty(propertyService.findFirstByName("Charisma"), 13));
        assertFalse(heroValidationService.isValid(hero));
    }

}
