package pl.mgorski;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class Message {

    public final static String TABLE_NAME = "Messages";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_CONTENT = "content";

    private String id;
    private String content;

    public static Message from(Map<String, AttributeValue> attributeMap) {
        Message message = Message.builder().build();
        if (MapUtils.isNotEmpty(attributeMap)) {
            return message.toBuilder()
                    .id(attributeMap.get(COLUMN_ID).s())
                    .content(attributeMap.get(COLUMN_CONTENT).s())
                    .build();
        }
        return message;
    }

}
