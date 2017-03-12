package io.dods.services.dependency;

import io.dods.api.dependency.model.CreateCondition;
import io.dods.api.dependency.model.CreateDependency;
import io.dods.api.dependency.model.CreateEffect;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.conditions.*;
import io.dods.model.conditions.level.MinLevelCondition;
import io.dods.model.conditions.level.check.FixedLevelCheck;
import io.dods.model.conditions.lists.AndCondition;
import io.dods.model.conditions.lists.OrCondition;
import io.dods.model.properties.Property;
import io.dods.model.rules.Dependency;
import io.dods.model.rules.Effect;
import io.dods.services.properties.property.PropertyService;
import io.dods.services.rules.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class DependencyService implements DodsDatabaseService<Long, Dependency, DependencyRepository> {

    @Autowired
    private DependencyRepository dependencyRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RuleService ruleService;

    public Dependency persist(CreateDependency createDependency) {
        Dependency dependency = parse(createDependency);
        return save(dependency);
    }

    public DependencyRepository getRepository() {
        return dependencyRepository;
    }

    public Dependency findByEffectProperty(Property property) {
        return findByEffectProperty(property.getId());
    }

    public Dependency findByEffectProperty(long attributId) {
        Dependency dependency = dependencyRepository.findFirstByEffectPropertyId(attributId);
        if (dependency == null) throw new ResourceNotFoundException();
        return dependency;
    }

    public List<Dependency> findAllObligatory() {
        return dependencyRepository.findByEffectPropertyIsNull();
    }

    private Dependency parse(CreateDependency createDependency) {
        Dependency dependency = new Dependency();

        dependency.setRule(ruleService.findById(createDependency.getRegelwerkId()));
        dependency.setCondition(parse(createDependency.getCreateCondition()));
        dependency.setEffect(parse(createDependency.getCreateEffect()));

        return dependency;
    }

    private Effect parse(CreateEffect createEffect) {
        Effect effect = new Effect();

        effect.setProperty(propertyService.findById(createEffect.getPropertyId()));

        return effect;
    }

    private Condition parse(CreateCondition createCondition) {
        Condition condition;

        switch (createCondition.getType()) {
            case AND:
                condition = new AndCondition(parse(createCondition.getSublist()));
                break;
            case OR:
                condition = new OrCondition(parse(createCondition.getSublist()));
                break;
            case MIN:
                condition = new MinLevelCondition(propertyService.findById(createCondition.getPropertyId()),
                        new FixedLevelCheck(createCondition.getLevel()), 0);
                break;
            case NOT:
                condition = new NotCondition(parse(createCondition.getSubelement()));
                break;
            case HAS:
                condition = new HasPropertyCondition(propertyService.findById(createCondition.getPropertyId()));
                break;
            default:
                throw new IllegalArgumentException(String.format("condition.type %s not identified as valid type",
                        createCondition.getType()));
        }

        return condition;
    }

    private List<Condition> parse(List<CreateCondition> list) {
        List<Condition> bedingungen = new ArrayList<>();

        list.forEach(x -> bedingungen.add(parse(x)));

        return bedingungen;
    }
}
