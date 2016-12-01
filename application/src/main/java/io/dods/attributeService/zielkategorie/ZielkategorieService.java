package io.dods.attributeService.zielkategorie;

import io.dods.model.attribute.misc.Zielkategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZielkategorieService {

    @Autowired
    private ZielkategorieRepository zielkategorieRepository;

    public Iterable<Zielkategorie> findAll() {
        return zielkategorieRepository.findAll();
    }

    public Zielkategorie persist(Zielkategorie zielkategorie) {
        return zielkategorieRepository.save(zielkategorie);
    }

    public Zielkategorie findById(long id) {
        return zielkategorieRepository.findById(id);
    }

    public List<Zielkategorie> findByIds(List<Long> ids) {
        ArrayList<Zielkategorie> zielkategorien = new ArrayList<>();
        ids.forEach(id -> zielkategorien.add(zielkategorieRepository.findById(id)));
        return zielkategorien;
    }

    public Zielkategorie findByNameOrCreate(String name) {
        Zielkategorie zielkategorie = zielkategorieRepository.findByName(name);

        if (zielkategorie == null) {
            zielkategorie = new Zielkategorie(name);
            zielkategorie = zielkategorieRepository.save(zielkategorie);
        }

        return zielkategorie;
    }
}
