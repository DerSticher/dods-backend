package io.dods.attributeService.zauber;

import io.dods.api.model.CreateZauber;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Zauber;
import io.dods.model.attribute.misc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZauberService extends AbstractAttributService<Zauber, CreateZauber> {

    @Autowired
    private ZauberRepository zauberRepository;

    @Autowired
    private ProbeService probeService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Autowired
    private DauerService dauerService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Autowired
    private ReichweiteService reichweiteService;

    @Override
    protected AbstractAttributRepository<Zauber> getRepository() {
        return zauberRepository;
    }

    @Override
    protected Zauber parse(CreateZauber create) {
        List<Zielkategorie> zielkategorieList = create.getZielkategorienId().stream()
                .map(id -> zielkategorieService.findById(id))
                .collect(Collectors.toList());

        int aspKosten = create.getNutzkosten();
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());
        Probe probe = probeService.create(create);
        Reichweite reichweite = reichweiteService.findById(create.getReichweiteId());
        String wirkung = create.getWirkung();
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        Dauer dauer = dauerService.findById(create.getZauberDauerId());

        return new Zauber(aspKosten, kostentabelle, probe, reichweite, wirkung, wirkungsdauer, dauer, zielkategorieList);
    }
}
