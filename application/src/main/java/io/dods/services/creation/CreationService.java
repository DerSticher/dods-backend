package io.dods.services.creation;

import io.dods.model.creation.Experience;
import io.dods.model.heroes.Hero;
import io.dods.model.heroes.HeroProperty;
import io.dods.model.properties.Attribute;
import io.dods.model.properties.CombatTechnique;
import io.dods.model.properties.Property;
import io.dods.model.properties.Skill;
import io.dods.services.held.HeroService;
import io.dods.services.properties.property.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CreationService {

    @Autowired
    private HeroService heroService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ExperienceRepository experienceRepository;

    public Hero createNew() {
        Hero hero = new Hero();
        initializeHero(hero);
        return heroService.save(hero);
    }

    private void initializeHero(Hero hero) {
        hero.setStartingExperience(experienceRepository.findById(3L));

        Iterable<Property> abilities = propertyService.find(Attribute.NAME, null, false);
        abilities.forEach(eigenschaft -> hero.addProperty(new HeroProperty(eigenschaft, eigenschaft.getDefaultLevel())));

        Iterable<Property> skills = propertyService.find(Skill.NAME, null, false);
        skills.forEach(fertigkeit -> hero.addProperty(new HeroProperty(fertigkeit, fertigkeit.getDefaultLevel())));

        Iterable<Property> combatAbilities = propertyService.find(CombatTechnique.NAME, null, false);
        combatAbilities.forEach(combatAbility -> hero.addProperty(new HeroProperty(combatAbility, combatAbility.getDefaultLevel())));
    }

    public Experience getExperienceById(long id) {
        return experienceRepository.findById(id);
    }

}
