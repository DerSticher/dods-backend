package io.dods.api.properties;

import io.dods.model.properties.Ceremony;
import io.dods.services.properties.ceremony.CeremonyService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class CeremonyApiImpl
        extends AbstractPropertyApiImpl<Ceremony, CeremonyService>
        implements CeremonyApi {}
