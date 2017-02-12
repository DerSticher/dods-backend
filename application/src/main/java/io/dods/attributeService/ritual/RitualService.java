package io.dods.attributeService.ritual;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Ritual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class RitualService extends AbstractAttributService<Ritual> {

    @Autowired
    private RitualRepository ritualRepository;

    @Override
    protected AbstractAttributRepository<Ritual> getRepository() {
        return ritualRepository;
    }

}
