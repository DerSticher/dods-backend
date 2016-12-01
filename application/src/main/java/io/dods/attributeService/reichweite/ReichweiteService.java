package io.dods.attributeService.reichweite;

import io.dods.model.attribute.misc.Reichweite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ReichweiteService {

    @Autowired
    private ReichweiteRepository reichweiteRepository;

    @Nullable
    public Reichweite findByName(String name) {
        return reichweiteRepository.findByName(name);
    }

    @NotNull
    public Reichweite findByNameOrCreate(String name) {
        Reichweite reichweite = findByName(name);
        if (reichweite == null) {
            reichweite = new Reichweite(name);
            reichweite = reichweiteRepository.save(reichweite);
        }
        return reichweite;
    }


    public Reichweite findById(int id) {
        return reichweiteRepository.findById(id);
    }
}
