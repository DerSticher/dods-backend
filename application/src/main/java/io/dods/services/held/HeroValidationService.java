package io.dods.services.held;

import io.dods.model.heroes.Hero;
import io.dods.model.rules.DependencyResult;
import io.dods.services.dependency.DependencyCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class HeroValidationService {

    @Autowired
    private DependencyCheckService dependencyCheckService;

    public boolean isValid(Hero hero) {
        return checkForUnsatisfiedDependencies(hero).size() == 0;
    }

    private List<DependencyResult> checkForUnsatisfiedDependencies(Hero hero) {
        return dependencyCheckService.checkForStatus(hero, DependencyResult.Status.REQUIREMENTS_NOT_MET);
    }

    private List<DependencyResult> checkForPossibleEffects(Hero hero) {
        return dependencyCheckService.checkForStatus(hero, DependencyResult.Status.REQUIREMENTS_MET);
    }

    private List<DependencyResult> checkForFulfilledDependencies(Hero hero) {
        return dependencyCheckService.checkForStatus(hero, DependencyResult.Status.FULFILLED);
    }

}
