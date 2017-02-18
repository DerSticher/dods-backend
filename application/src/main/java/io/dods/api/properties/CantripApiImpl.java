package io.dods.api.properties;

import io.dods.model.properties.Cantrip;
import io.dods.services.properties.cantrip.CantripService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class CantripApiImpl
        extends AbstractPropertyApiImpl<Cantrip, CantripService>
        implements CantripApi {}
