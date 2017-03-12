package io.dods.model.properties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * German: Kultur
 *
 * @author Richard Gottschalk
 */
@Entity
@DiscriminatorValue(Culture.NAME)
public class Culture extends Property {

    public static final String NAME = "Kultur";

}
