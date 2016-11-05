package io.dods.model.attribute.misc;

import io.dods.model.BaseNamedValue;
import io.dods.model.Language;
import org.springframework.data.util.Pair;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Entity
public class Wirkungsdauer extends BaseNamedValue {
}
