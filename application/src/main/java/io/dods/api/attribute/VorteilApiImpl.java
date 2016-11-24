package io.dods.api.attribute;

import io.dods.api.model.CreateVorteil;
import io.dods.attributeService.vorteil.VorteilService;
import io.dods.model.attribute.Vorteil;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class VorteilApiImpl
        extends AbstractAttributeApiImpl<Vorteil, CreateVorteil, VorteilService>
        implements VorteilApi {}
