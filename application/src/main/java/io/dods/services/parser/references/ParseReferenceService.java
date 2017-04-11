package io.dods.services.parser.references;

import io.dods.model.properties.Property;
import io.dods.services.properties.AbstractPropertyService;
import io.dods.services.properties.property.PropertyService;
import io.dods.services.properties.skill.SkillService;
import io.dods.services.properties.combatTechnique.CombatTechniqueService;
import io.dods.services.properties.liturgicalChant.LiturgicalChantService;
import io.dods.services.properties.ritual.RitualService;
import io.dods.services.properties.spell.SpellService;
import io.dods.services.properties.ceremony.CeremonyService;
import io.dods.model.properties.misc.ImprovementChart;
import io.dods.model.properties.misc.ImprovementCharted;
import io.dods.services.parser.model.ParsedValue;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseReferenceService {

    private final PropertyService propertyService;

    private final SkillService skillService;

    private final CombatTechniqueService combatTechniqueService;

    private final LiturgicalChantService liturgicalChantService;

    private final CeremonyService ceremonyService;

    private final RitualService ritualService;

    private final SpellService spellService;

    @Autowired
    public ParseReferenceService(PropertyService propertyService,
                                 SkillService skillService,
                                 CombatTechniqueService combatTechniqueService,
                                 LiturgicalChantService liturgicalChantService,
                                 CeremonyService ceremonyService,
                                 RitualService ritualService,
                                 SpellService spellService) {
        this.propertyService = propertyService;
        this.skillService = skillService;
        this.combatTechniqueService = combatTechniqueService;
        this.liturgicalChantService = liturgicalChantService;
        this.ceremonyService = ceremonyService;
        this.ritualService = ritualService;
        this.spellService = spellService;
    }

    private interface OnSetPriceCallback {
        default int onSetPrice(@NotNull ImprovementCharted referencedValue) {
            return 0;
        }
    }


    public List<ParsedValue> checkForSubcategories(ParsedValue parsedValue) {
        return checkForReferences(parsedValue);
    }

    private List<ParsedValue> checkForReferences(ParsedValue parsedValue) {
        List<ParsedValue> list = new ArrayList<>();

        if (parsedValue.getName().equals("Begabung")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(6, 12, 18, 24);

            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback));
            list.addAll(createLiturgieReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZauberspruchReferences(parsedValue, onSetPriceCallback));
            list.addAll(createRitualReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZeremonieReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Unfähig")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(-1, -2, -3, -4);

            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback));
            list.addAll(createLiturgieReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZauberspruchReferences(parsedValue, onSetPriceCallback));
            list.addAll(createRitualReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZeremonieReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Herausragende Fertigkeit")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(2, 4, 6, 8);

            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback));
            list.addAll(createLiturgieReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZauberspruchReferences(parsedValue, onSetPriceCallback));
            list.addAll(createRitualReferences(parsedValue, onSetPriceCallback));
            list.addAll(createZeremonieReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Waffenbegabung")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(0, 5, 10, 15);

            list.addAll(createKampftechnikReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Herausragende Kampftechnik")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(0, 8, 12, 16);

            list.addAll(createKampftechnikReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Adaption (Zauber)")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(5, 10, 15, 20);

            list.addAll(createZauberspruchReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Lieblingszauber")) {
            OnSetPriceCallback onSetPriceCallback = createOnSetPriceCallback(3, 6, 9, 12);

            list.addAll(createZauberspruchReferences(parsedValue, onSetPriceCallback));

        } else if (parsedValue.getName().equals("Fertigkeitsspezialisierung (Talente) 1")) {
            OnSetPriceCallback onSetPriceCallback1 = createOnSetPriceCallback(1, 2, 3, 4);
            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback1));

        } else if (parsedValue.getName().equals("Fertigkeitsspezialisierung (Talente) 2")) {
            OnSetPriceCallback onSetPriceCallback1 = createOnSetPriceCallback(2, 4, 6, 8);
            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback1));

        } else if (parsedValue.getName().equals("Fertigkeitsspezialisierung (Talente) 3")) {
            OnSetPriceCallback onSetPriceCallback1 = createOnSetPriceCallback(3, 6, 9, 12);
            list.addAll(createFertigkeitReferences(parsedValue, onSetPriceCallback1));

//        } else if (parsedValue.getName().startsWith("Tradition")) {
//            list.addAll(find("Tradition", parsedValue));

        }

        return list;
    }

    private List<ParsedValue> find(String attributeName, ParsedValue parsedValue) {
        List<ParsedValue> list = new ArrayList<>();

        ParsedValue copy = parsedValue.copy();
        copy.setName(attributeName);
        list.add(copy);

        String name = parsedValue.getName().replace(attributeName, "");
        name = name.replaceAll("[\\(\\)]", "").trim();
        parsedValue.setName(name);
        list.add(parsedValue);

        return list;
    }

    private OnSetPriceCallback createOnSetPriceCallback(final int a, final int b, final int c, final int d) {
        return new OnSetPriceCallback() {
            @Override
            public int onSetPrice(@NotNull ImprovementCharted referencedValue) {
                ImprovementChart improvementChart = referencedValue.getImprovementChart();
                if (improvementChart != null) {
                    switch (improvementChart) {
                        case A: return a;
                        case B: return b;
                        case C: return c;
                        case D: return d;
                    }
                }
                return 0;
            }
        };
    }

    private <T extends Property> List<ParsedValue> createReferences(ParsedValue original,
                                                                    AbstractPropertyService<T> referencedService,
                                                                    OnSetPriceCallback onSetPriceCallback) {
        List<ParsedValue> list = new ArrayList<>();

        List<T> all = referencedService.findAll();
        all.stream()
                .filter(Objects::nonNull)
                .forEach(referedAttribute -> {
                    ParsedValue copy = original.copy();
                    copy.setName(referedAttribute.getName());
                    copy.setPublications(null);

                    if (referedAttribute instanceof ImprovementCharted) {
                        copy.setApValue(onSetPriceCallback.onSetPrice((ImprovementCharted) referedAttribute));
                    }

                    list.add(copy);
        });

        return list;
    }

//    Profan stuff

    private List<ParsedValue> createFertigkeitReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, skillService, onSetPriceCallback);
    }

    private List<ParsedValue> createKampftechnikReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, combatTechniqueService, onSetPriceCallback);
    }

//    Karma stuff

    private List<ParsedValue> createLiturgieReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, liturgicalChantService, onSetPriceCallback);
    }

    private List<ParsedValue> createZeremonieReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, ceremonyService, onSetPriceCallback);
    }

//    Magic stuff

    private List<ParsedValue> createZauberspruchReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, spellService, onSetPriceCallback);
    }

    private List<ParsedValue> createRitualReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, ritualService, onSetPriceCallback);
    }

}
