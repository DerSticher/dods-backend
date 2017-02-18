package io.dods.api.properties;

import io.dods.model.properties.Bless;
import io.dods.services.properties.bless.BlessService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class BlessApiImpl
        extends AbstractPropertyApiImpl<Bless, BlessService>
        implements BlessApi {}
