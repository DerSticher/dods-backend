package io.dods.api.attribute;

import io.dods.api.model.CreateZauber;
import io.dods.attributeService.zauber.ZauberService;
import io.dods.model.attribute.Zauber;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class ZauberApiImpl
        extends AbstractAttributeApiImpl<Zauber, CreateZauber, ZauberService>
        implements ZauberApi {}
