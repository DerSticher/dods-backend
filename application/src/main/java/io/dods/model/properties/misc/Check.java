package io.dods.model.properties.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.properties.Attribute;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Embeddable
public class Check {

    @JsonProperty("teilprobe1")
    @OneToOne
    private Attribute check1;

    @JsonProperty("teilprobe2")
    @OneToOne
    private Attribute check2;

    @JsonProperty("teilprobe3")
    @OneToOne
    private Attribute check3;

    public Check() {
    }

    public Check(List<Attribute> eigenschaften) {
        for (int i = 0; i < eigenschaften.size(); i++) {
            setCheck(i + 1, eigenschaften.get(i));
        }
    }

    public @NotNull String getBezeichnung() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            if (i > 1) stringBuilder.append('/');
            Attribute attribute = getCheck(i);
            stringBuilder.append(attribute.getName());
        }

        return stringBuilder.toString();
    }

    public void setCheck(int oneBasedIndex, Attribute attribute) {
        switch (oneBasedIndex) {
            case 1:
                check1 = attribute;
                break;
            case 2:
                check2 = attribute;
                break;
            case 3:
                check3 = attribute;
                break;
            default:
                throw new IllegalArgumentException(String.format("index has to be 1, 2 or 3. (Was %d)", oneBasedIndex));
        }
    }

    public Attribute getCheck(int oneBasedIndex) {
        switch (oneBasedIndex) {
            case 1:
                return check1;
            case 2:
                return check2;
            case 3:
                return check3;
        }
        throw new IllegalArgumentException(String.format("index has to be 1, 2 or 3. (Was %d)", oneBasedIndex));
    }
}
