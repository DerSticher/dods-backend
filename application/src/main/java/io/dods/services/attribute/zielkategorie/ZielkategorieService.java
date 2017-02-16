package io.dods.services.attribute.zielkategorie;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.attribute.misc.Zielkategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZielkategorieService implements NamedDodsDatabaseService<Long, Zielkategorie, ZielkategorieRepository> {

    @Autowired
    private ZielkategorieRepository zielkategorieRepository;

    @Override
    public ZielkategorieRepository getRepository() {
        return zielkategorieRepository;
    }

    @Override
    public Zielkategorie findByNameOrCreate(String name) {
        Zielkategorie zielkategorie = findFirstByName(name);

        if (zielkategorie == null) {
            zielkategorie = new Zielkategorie(name);
            zielkategorie = zielkategorieRepository.save(zielkategorie);
        }

        return zielkategorie;
    }
}
