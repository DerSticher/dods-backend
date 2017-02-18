package io.dods.services.dependency;

import io.dods.api.dependency.model.CreateDependency;
import io.dods.api.dependency.model.CreateCondition;
import io.dods.api.dependency.model.CreateEffect;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.model.rules.Dependency;
import io.dods.model.rules.Effect;
import io.dods.services.properties.property.PropertyService;
import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.properties.Property;
import io.dods.model.conditions.*;
import io.dods.services.regelwerk.RegelwerkService;
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
    private RegelwerkService regelwerkService;

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
        Dependency dependency = dependencyRepository.findByEffectPropertyId(attributId);
        if (dependency == null) throw new ResourceNotFoundException();
        return dependency;
    }

    private Dependency parse(CreateDependency createDependency) {
        Dependency dependency = new Dependency();

        dependency.setRegelwerk(regelwerkService.findById(createDependency.getRegelwerkId()));
        dependency.setCondition(parse(createDependency.getCreateCondition()));
        dependency.setEffect(parse(createDependency.getCreateEffect()));

        return dependency;
    }

    private Effect parse(CreateEffect createEffect) {
        Effect effect = new Effect();

        effect.setProperty(propertyService.findById(createEffect.getPropertyId()));
        effect.setLevel(createEffect.getLevel());

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
                condition = new MinCondition(propertyService.findById(createCondition.getPropertyId()), createCondition.getLevel());
                break;
            case MAX:
                condition = new MaxCondition(propertyService.findById(createCondition.getPropertyId()), createCondition.getLevel());
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
