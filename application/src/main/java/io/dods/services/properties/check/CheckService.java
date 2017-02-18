package io.dods.services.properties.check;

import io.dods.model.properties.misc.CheckImpl;
import io.dods.services.properties.ability.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CheckService {

    @Autowired
    private AbilityService abilityService;

    public CheckImpl create(Check originalCheck) {
        CheckImpl check = new CheckImpl();

        check.setCheck(1, abilityService.findById(originalCheck.getCheck1Id()));
        check.setCheck(2, abilityService.findById(originalCheck.getCheck2Id()));
        check.setCheck(3, abilityService.findById(originalCheck.getCheck3Id()));

        return check;
    }
}
