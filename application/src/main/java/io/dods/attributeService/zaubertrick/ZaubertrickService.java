package io.dods.attributeService.zaubertrick;

import io.dods.api.model.CreateZaubertrick;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Zaubertrick;
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
public class ZaubertrickService extends AbstractAttributService<Zaubertrick, CreateZaubertrick> {

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

    @Override
    protected AbstractAttributRepository<Zaubertrick> getRepository() {
        return zauberRepository;
    }

    @Override
    protected Zaubertrick parse(CreateZaubertrick create) {
        List<Zielkategorie> zielkategorieList = create.getZielkategorienId().stream()
                .map(id -> zielkategorieService.findById(id))
                .collect(Collectors.toList());

        int reichweite = create.getReichweiteInSchritt();
        String wirkung = create.getWirkung();
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        String name = create.getName();

        return new Zaubertrick(reichweite, zielkategorieList, wirkungsdauer, wirkung, name);
    }
}
