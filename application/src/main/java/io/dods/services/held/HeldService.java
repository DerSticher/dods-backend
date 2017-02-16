package io.dods.services.held;

import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.api.held.model.HeldUpdate;
import io.dods.services.attribute.attribute.AttributeService;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.Eigenschaft;
import io.dods.model.attribute.Fertigkeit;
import io.dods.model.attribute.Kampftechnik;
import io.dods.model.helden.CharakterEigenschaft;
import io.dods.model.helden.Held;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Richard Gottschalk
 */
@Service
public class HeldService {

    @Autowired
    private HeldRepository heldRepository;

    @Autowired
    private AttributeService attributeService;

    public Held createNew() {
        Held held = new Held();
        initializeHero(held);
        return heldRepository.save(held);
    }

    private void initializeHero(Held held) {
        Iterable<Attribut> eigenschaften = attributeService.find(Eigenschaft.NAME, null, false);
        eigenschaften.forEach(eigenschaft -> held.addEigenschaft(new CharakterEigenschaft(eigenschaft, eigenschaft.getDefaultLevel())));

        Iterable<Attribut> fertigkeiten = attributeService.find(Fertigkeit.NAME, null, false);
        fertigkeiten.forEach(fertigkeit -> held.addEigenschaft(new CharakterEigenschaft(fertigkeit, fertigkeit.getDefaultLevel())));

        Iterable<Attribut> kampftechniken = attributeService.find(Kampftechnik.NAME, null, false);
        kampftechniken.forEach(kampftechnik -> held.addEigenschaft(new CharakterEigenschaft(kampftechnik, kampftechnik.getDefaultLevel())));
    }

    public Held findById(String id) {
        return heldRepository.findById(id);
    }

    public void delete(String id) {
        heldRepository.delete(id);
    }

    public void update(String id, HeldUpdate update) {
        if (update == null) throw new IllegalStateException("update values are missing");

        Held held = heldRepository.findById(id);
        if (held == null) throw new ResourceNotFoundException();

        updateValues(held, update);
        updateCharakterEigenschaft(held, update);

        heldRepository.save(held);
    }

    private void updateValues(@NotNull Held held, @NotNull HeldUpdate update) {
        if (!StringUtils.isEmpty(update.getName())) held.setName(update.getName());
        if (!StringUtils.isEmpty(update.getAlter())) held.setAlter(update.getAlter());
        if (!StringUtils.isEmpty(update.getProfession())) held.setProfession(update.getProfession());
        if (!StringUtils.isEmpty(update.getKultur())) held.setKultur(update.getKultur());
        if (!StringUtils.isEmpty(update.getAugenfarbe())) held.setAugenfarbe(update.getAugenfarbe());
        if (!StringUtils.isEmpty(update.getGeburtsort())) held.setGeburtsort(update.getGeburtsort());
        if (!StringUtils.isEmpty(update.getGewicht())) held.setGewicht(update.getGewicht());
        if (!StringUtils.isEmpty(update.getGroesse())) held.setGroesse(update.getGroesse());
    }

    private void updateCharakterEigenschaft(Held held, HeldUpdate update) {
        executeClear(held, update);
        executeUpdate(held, update);
    }

    private void executeClear(Held held, HeldUpdate update) {
        update.getClearAttributIds().forEach(held::removeEigenschaft);
    }

    private void executeUpdate(Held held, HeldUpdate update) {
        update.getUpdate()
                .forEach(value -> held.addEigenschaft(attributeService.findById(value.getAttributId()), value.getLevel()));
    }

    public Iterable<Held> findAll() {
        return heldRepository.findAll();
    }
}
