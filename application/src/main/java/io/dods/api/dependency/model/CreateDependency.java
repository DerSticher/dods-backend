package io.dods.api.dependency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Richard Gottschalk
 */
public class CreateDependency {

    @JsonProperty("bedingung")
    private CreateCondition createCondition;

    @JsonProperty("effekt")
    private CreateEffect createEffect;

    @JsonProperty("regelwerkId")
    private int regelwerkId;

    public CreateCondition getCreateCondition() {
        return createCondition;
    }

    public void setCreateCondition(CreateCondition createCondition) {
        this.createCondition = createCondition;
    }

    public CreateEffect getCreateEffect() {
        return createEffect;
    }

    public void setCreateEffect(CreateEffect createEffect) {
        this.createEffect = createEffect;
    }

    public int getRegelwerkId() {
        return regelwerkId;
    }

    public void setRegelwerkId(int regelwerkId) {
        this.regelwerkId = regelwerkId;
    }
}
