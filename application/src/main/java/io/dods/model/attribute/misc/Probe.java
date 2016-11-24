package io.dods.model.attribute.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dods.model.attribute.Eigenschaft;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Embeddable
public class Probe {

    @JsonProperty
    @OneToOne
    private Eigenschaft teilprobe1;

    @JsonProperty
    @OneToOne
    private Eigenschaft teilprobe2;

    @JsonProperty
    @OneToOne
    private Eigenschaft teilprobe3;

    public Probe() {
    }

    public Probe(List<Eigenschaft> eigenschaften) {
        for (int i = 0; i < eigenschaften.size(); i++) {
            setTeilprobe(i + 1, eigenschaften.get(i));
        }
    }

    public @NotNull String getBezeichnung() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            if (i > 1) stringBuilder.append('/');
            Eigenschaft eigenschaft = getTeilprobe(i);
            stringBuilder.append(eigenschaft.getName());
        }

        return stringBuilder.toString();
    }

    public void setTeilprobe(int oneBasedIndex, Eigenschaft eigenschaft) {
        switch (oneBasedIndex) {
            case 1:
                teilprobe1 = eigenschaft;
                break;
            case 2:
                teilprobe2 = eigenschaft;
                break;
            case 3:
                teilprobe3 = eigenschaft;
                break;
            default:
                throw new IllegalArgumentException(String.format("index has to be 1, 2 or 3. (Was %d)", oneBasedIndex));
        }
    }

    public Eigenschaft getTeilprobe(int oneBasedIndex) {
        switch (oneBasedIndex) {
            case 1:
                return teilprobe1;
            case 2:
                return teilprobe2;
            case 3:
                return teilprobe3;
        }
        throw new IllegalArgumentException(String.format("index has to be 1, 2 or 3. (Was %d)", oneBasedIndex));
    }
}
