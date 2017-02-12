package io.dods.parser.references;

import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.attributeService.fertigkeit.FertigkeitService;
import io.dods.attributeService.kampftechnik.KampftechnikService;
import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.attributeService.ritual.RitualService;
import io.dods.attributeService.zauber.ZauberService;
import io.dods.attributeService.zeremonie.ZeremonieService;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.UsesKostentabelle;
import io.dods.parser.model.ParsedValue;
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

    private final AttributeService attributeService;

    private final FertigkeitService fertigkeitService;

    private final KampftechnikService kampftechnikService;

    private final LiturgieService liturgieService;

    private final ZeremonieService zeremonieService;

    private final RitualService ritualService;

    private final ZauberService zauberService;

    @Autowired
    public ParseReferenceService(AttributeService attributeService,
                                 FertigkeitService fertigkeitService,
                                 KampftechnikService kampftechnikService,
                                 LiturgieService liturgieService,
                                 ZeremonieService zeremonieService,
                                 RitualService ritualService,
                                 ZauberService zauberService) {
        this.attributeService = attributeService;
        this.fertigkeitService = fertigkeitService;
        this.kampftechnikService = kampftechnikService;
        this.liturgieService = liturgieService;
        this.zeremonieService = zeremonieService;
        this.ritualService = ritualService;
        this.zauberService = zauberService;
    }

    private interface OnSetPriceCallback {
        default int onSetPrice(@NotNull UsesKostentabelle referencedValue) {
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
            public int onSetPrice(@NotNull UsesKostentabelle referencedValue) {
                Kostentabelle kostentabelle = referencedValue.getKostentabelle();
                if (kostentabelle != null) {
                    switch (kostentabelle) {
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

    private <T extends Attribut> List<ParsedValue> createReferences(ParsedValue original,
                                                                AbstractAttributService<T> referencedService,
                                                                OnSetPriceCallback onSetPriceCallback) {
        List<ParsedValue> list = new ArrayList<>();

        List<T> all = referencedService.findAll();
        all.stream()
                .filter(Objects::nonNull)
                .forEach(referedAttribute -> {
                    ParsedValue copy = original.copy();
                    copy.setName(referedAttribute.getName());
                    copy.setPublikations(null);

                    if (referedAttribute instanceof UsesKostentabelle) {
                        copy.setApWert(onSetPriceCallback.onSetPrice((UsesKostentabelle) referedAttribute));
                    }

                    list.add(copy);
        });

        return list;
    }

//    Profan stuff

    private List<ParsedValue> createFertigkeitReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, fertigkeitService, onSetPriceCallback);
    }

    private List<ParsedValue> createKampftechnikReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, kampftechnikService, onSetPriceCallback);
    }

//    Karma stuff

    private List<ParsedValue> createLiturgieReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, liturgieService, onSetPriceCallback);
    }

    private List<ParsedValue> createZeremonieReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, zeremonieService, onSetPriceCallback);
    }

//    Magic stuff

    private List<ParsedValue> createZauberspruchReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, zauberService, onSetPriceCallback);
    }

    private List<ParsedValue> createRitualReferences(ParsedValue parsedValue, OnSetPriceCallback onSetPriceCallback) {
        return createReferences(parsedValue, ritualService, onSetPriceCallback);
    }

}
