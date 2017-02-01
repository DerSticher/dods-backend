package io.dods.abhaengigkeitService;

import io.dods.api.abhangigkeit.model.CreateAbhangigkeit;
import io.dods.api.abhangigkeit.model.CreateBedingung;
import io.dods.api.abhangigkeit.model.CreateEffekt;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.attribute.Attribut;
import io.dods.model.bedingungen.*;
import io.dods.model.regeln.Abhangigkeit;
import io.dods.model.regeln.Effekt;
import io.dods.regelwerkService.RegelwerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class AbhangigkeitService implements DodsDatabaseService<Long, Abhangigkeit, AbhangigkeitRepository> {

    @Autowired
    private AbhangigkeitRepository abhangigkeitRepository;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private RegelwerkService regelwerkService;

    public Abhangigkeit persist(CreateAbhangigkeit createAbhangigkeit) {
        Abhangigkeit abhangigkeit = parse(createAbhangigkeit);
        return save(abhangigkeit);
    }

    public AbhangigkeitRepository getRepository() {
        return abhangigkeitRepository;
    }

    public Abhangigkeit findByEffektAttribut(Attribut attribut) {
        return findByEffektAttribut(attribut.getId());
    }

    public Abhangigkeit findByEffektAttribut(long attributId) {
        Abhangigkeit abhangigkeit = abhangigkeitRepository.findByEffektAttributId(attributId);
        if (abhangigkeit == null) throw new ResourceNotFoundException();
        return abhangigkeit;
    }

    private Abhangigkeit parse(CreateAbhangigkeit createAbhangigkeit) {
        Abhangigkeit abhangigkeit = new Abhangigkeit();

        abhangigkeit.setRegelwerk(regelwerkService.findById(createAbhangigkeit.getRegelwerkId()));
        abhangigkeit.setBedingung(parse(createAbhangigkeit.getCreateBedingung()));
        abhangigkeit.setEffekt(parse(createAbhangigkeit.getCreateEffekt()));

        return abhangigkeit;
    }

    private Effekt parse(CreateEffekt createEffekt) {
        Effekt effekt = new Effekt();

        effekt.setAttribut(attributeService.findById(createEffekt.getAttributId()));
        effekt.setLevel(createEffekt.getLevel());

        return effekt;
    }

    private Bedingung parse(CreateBedingung createBedingung) {
        Bedingung bedingung;

        switch (createBedingung.getType()) {
            case AND:
                bedingung = new AndBedingung(parse(createBedingung.getSublist()));
                break;
            case OR:
                bedingung = new OrBedingung(parse(createBedingung.getSublist()));
                break;
            case MIN:
                bedingung = new MinBedingung(attributeService.findById(createBedingung.getAttributId()), createBedingung.getLevel());
                break;
            case MAX:
                bedingung = new MaxBedingung(attributeService.findById(createBedingung.getAttributId()), createBedingung.getLevel());
                break;
            case NOT:
                bedingung = new NotBedingung(parse(createBedingung.getSubelement()));
                break;
            case HAS:
                bedingung = new VorhandenBedingung(attributeService.findById(createBedingung.getAttributId()));
                break;
            default:
                throw new IllegalArgumentException(String.format("bedingung.type %s not identified as valid type",
                        createBedingung.getType()));
        }

        return bedingung;
    }

    private List<Bedingung> parse(List<CreateBedingung> list) {
        List<Bedingung> bedingungen = new ArrayList<>();

        list.forEach(x -> bedingungen.add(parse(x)));

        return bedingungen;
    }
}
