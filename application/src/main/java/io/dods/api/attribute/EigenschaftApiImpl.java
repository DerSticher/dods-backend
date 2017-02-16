package io.dods.api.attribute;

import io.dods.services.attribute.eigenschaft.EigenschaftService;
import io.dods.model.attribute.Eigenschaft;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class EigenschaftApiImpl
        extends AbstractAttributeApiImpl<Eigenschaft, EigenschaftService>
        implements EigenschaftApi {}
