package io.dods.attributeService.zaubertrick;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Zaubertrick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZaubertrickService extends AbstractAttributService<Zaubertrick> {

    @Autowired
    private ZaubertrickRepository zauberRepository;

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
    protected AbstractAttributRepository<Zaubertrick> getRepository() {
        return zauberRepository;
    }

}
