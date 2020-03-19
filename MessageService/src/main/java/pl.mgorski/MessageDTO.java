package pl.mgorski;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@RegisterForReflection
@Data
@JsonDeserialize(builder = MessageDTO.MessageBuilder.class)
@Builder(builderClassName = "MessageBuilder", toBuilder = true)
public class MessageDTO {

    private String id;
    private String content;

    @JsonPOJOBuilder(withPrefix = "")
    @RegisterForReflection
    public static class MessageBuilder {
        // Lombok will add constructor, setters, build method
    }

}
