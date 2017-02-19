package io.dods.services.properties.check;

import io.dods.model.properties.misc.Check;
import io.dods.services.properties.ability.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class CheckService {

    @Autowired
    private AttributeService attributeService;

    public Check create(int checkId1, int checkId2, int checkId3) {
        Check check = new Check();

        check.setCheck(1, attributeService.findById(checkId1));
        check.setCheck(2, attributeService.findById(checkId2));
        check.setCheck(3, attributeService.findById(checkId3));

        return check;
    }
}
