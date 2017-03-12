package io.dods.services.dependency;

import io.dods.model.heroes.Hero;
import io.dods.model.rules.Dependency;
import io.dods.model.rules.DependencyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class DependencyCheckService {

    @Autowired
    private DependencyService dependencyService;

    public List<DependencyResult> checkForStatus(Hero hero, DependencyResult.Status status) {
        List<Dependency> all = dependencyService.findAll();

        return all.stream()
                .map(dependency -> checkDependency(dependency, hero))
                .filter(dependencyResult -> dependencyResult.getStatus() == status)
                .collect(Collectors.toList());
    }

    public DependencyResult checkDependency(Dependency dependency, Hero hero) {
        if (dependency == null) throw new IllegalArgumentException("dependency cannot be null");
        if (hero == null) throw new IllegalArgumentException("heroes cannot be null");

        boolean conditionFulfilled = dependency.isConditionFulfilled(hero);
        boolean isObligatoryDependency = dependency.isObligatory();
        boolean effectFulfilledByLevel = dependency.isEffectFulfilledByExperienceLevel(hero);
        boolean heroIgnoresExperience = hero.ignoreExperience();
        boolean effectFulfilled = effectFulfilledByLevel || heroIgnoresExperience;

        if (isObligatoryDependency) {
            if (conditionFulfilled && effectFulfilled) return new DependencyResult(DependencyResult.Status.FULFILLED);
            return new DependencyResult(DependencyResult.Status.OBLIGATORY_NOT_FULFILLED);
        }

        int heroEffectLevel = dependency.getHeroEffectLevel(hero);
        int maxEffectLevel = dependency.getMaxEffectLevel(hero);
        boolean effectLevelInRange = heroEffectLevel <= maxEffectLevel;

        if (conditionFulfilled && effectFulfilled) {
            if (effectLevelInRange) return new DependencyResult(DependencyResult.Status.FULFILLED);

            return new DependencyResult(DependencyResult.Status.LEVEL_EXCEEDED,
                    new DependencyResult.Change(dependency.getEffect().getProperty(), heroEffectLevel, maxEffectLevel));

        } else if (conditionFulfilled && maxEffectLevel < heroEffectLevel) {
            return new DependencyResult(DependencyResult.Status.LEVEL_EXCEEDED,
                    new DependencyResult.Change(dependency.getEffect().getProperty(), heroEffectLevel, maxEffectLevel));

        } else if (conditionFulfilled) {
            return new DependencyResult(DependencyResult.Status.REQUIREMENTS_MET);

        } else if (effectFulfilled) {
            return new DependencyResult(DependencyResult.Status.REQUIREMENTS_NOT_MET);

        }

        return new DependencyResult(DependencyResult.Status.NOTHING_FULFILLED);
    }

}
