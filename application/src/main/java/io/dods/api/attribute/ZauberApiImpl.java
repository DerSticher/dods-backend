package io.dods.api.attribute;

import io.dods.attributeService.zauber.ZauberService;
import io.dods.model.attribute.Zauber;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class ZauberApiImpl
        extends AbstractAttributeApiImpl<Zauber, ZauberService>
        implements ZauberApi {}
