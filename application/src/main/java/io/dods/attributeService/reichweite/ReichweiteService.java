package io.dods.attributeService.reichweite;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Reichweite;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ReichweiteService implements NamedDodsDatabaseService<Long, Reichweite, ReichweiteRepository> {

    @Autowired
    private ReichweiteRepository reichweiteRepository;

    @Override
    public ReichweiteRepository getRepository() {
        return reichweiteRepository;
    }

    @NotNull
    public Reichweite findByNameOrCreate(String name) {
        Reichweite reichweite = findFirstByName(name);
        if (reichweite == null) {
            reichweite = new Reichweite(name);
            reichweite = reichweiteRepository.save(reichweite);
        }
        return reichweite;
    }
}
