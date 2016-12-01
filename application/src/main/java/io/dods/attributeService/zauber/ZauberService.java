package io.dods.attributeService.zauber;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Zauber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZauberService extends AbstractAttributService<Zauber> {

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

}
