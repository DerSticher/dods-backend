package io.dods.api.attribute;

import io.dods.attributeService.vorteil.VorteilService;
import io.dods.model.attribute.Vorteil;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class VorteilApiImpl
        extends AbstractAttributeApiImpl<Vorteil, VorteilService>
        implements VorteilApi {}
