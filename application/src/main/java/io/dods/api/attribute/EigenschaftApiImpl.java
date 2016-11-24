package io.dods.api.attribute;

import io.dods.api.model.CreateEigenschaft;
import io.dods.attributeService.eigenschaft.EigenschaftService;
import io.dods.model.attribute.Eigenschaft;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class EigenschaftApiImpl
        extends AbstractAttributeApiImpl<Eigenschaft, CreateEigenschaft, EigenschaftService>
        implements EigenschaftApi {}
