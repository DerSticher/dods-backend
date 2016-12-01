package io.dods.attributeService.segen;

import io.dods.api.model.CreateSegen;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.aspekte.AspektService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Segen;
import io.dods.model.attribute.misc.Aspekt;
import io.dods.model.attribute.misc.Reichweite;
import io.dods.model.attribute.misc.Wirkungsdauer;
import io.dods.model.attribute.misc.Zielkategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class SegenService extends AbstractAttributService<Segen, CreateSegen> {

    @Autowired
    private SegenRepository segenRepository;

    @Autowired
    private AspektService aspektService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Autowired
    private ReichweiteService reichweiteService;

    @Override
    protected AbstractAttributRepository<Segen> getRepository() {
        return segenRepository;
    }

    @Override
    protected Segen parse(CreateSegen create) {
        List<Aspekt> aspektList = create.getAspektIds().stream()
                .map(aspektId -> aspektService.findById(aspektId))
                .collect(Collectors.toList());

        List<Zielkategorie> zielkategorieList = create.getZielkategorienIds().stream()
                .map(id -> zielkategorieService.findById(id))
                .collect(Collectors.toList());

        int kapKosten = create.getNutzkosten();

        String wirkung = create.getWirkung();
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        Reichweite reichweite = reichweiteService.findById(create.getReichweiteId());

        return new Segen(aspektList, kapKosten, reichweite, wirkung, wirkungsdauer, zielkategorieList);
    }
}
