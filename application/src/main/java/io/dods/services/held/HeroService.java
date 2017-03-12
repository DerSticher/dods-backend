package io.dods.services.held;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.api.hero.model.HeroUpdate;
import io.dods.model.heroes.Hero;
import io.dods.services.properties.property.PropertyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Richard Gottschalk
 */
@Service
public class HeroService {

    @Autowired
    private HeldRepository heldRepository;

    @Autowired
    private PropertyService propertyService;

    public Hero findById(String id) {
        return heldRepository.findById(id);
    }

    public void delete(String id) {
        heldRepository.delete(id);
    }

    public void update(String id, HeroUpdate update) {
        if (update == null) throw new IllegalStateException("update values are missing");

        Hero hero = heldRepository.findById(id);
        if (hero == null) throw new ResourceNotFoundException();

        updateValues(hero, update);
        updateCharakterEigenschaft(hero, update);

        heldRepository.save(hero);
    }

    private void updateValues(@NotNull Hero hero, @NotNull HeroUpdate update) {
        if (!StringUtils.isEmpty(update.getName())) hero.setName(update.getName());
        if (!StringUtils.isEmpty(update.getAge())) hero.setAge(update.getAge());
        if (!StringUtils.isEmpty(update.getProfession())) hero.setProfession(update.getProfession());
        if (!StringUtils.isEmpty(update.getCulture())) hero.setCulture(update.getCulture());
        if (!StringUtils.isEmpty(update.getEyeColor())) hero.setEyeColor(update.getEyeColor());
        if (!StringUtils.isEmpty(update.getPlaceOfBirth())) hero.setPlaceOfBirth(update.getPlaceOfBirth());
        if (!StringUtils.isEmpty(update.getBodyWeight())) hero.setBodyWeight(update.getBodyWeight());
        if (!StringUtils.isEmpty(update.getBodySize())) hero.setBodyHeight(update.getBodySize());
    }

    private void updateCharakterEigenschaft(Hero hero, HeroUpdate update) {
        executeClear(hero, update);
        executeUpdate(hero, update);
    }

    private void executeClear(Hero hero, HeroUpdate update) {
        update.getClearPropertyIds().forEach(hero::removeProperty);
    }

    private void executeUpdate(Hero hero, HeroUpdate update) {
        update.getUpdates()
                .forEach(value -> hero.addProperty(propertyService.findById(value.getPropertyId()), value.getLevel()));
    }

    public Iterable<Hero> findAll() {
        return heldRepository.findAll();
    }

    public Hero save(Hero hero) {
        return heldRepository.save(hero);
    }
}
