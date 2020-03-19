package pl.mgorski;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Singleton
@RequiredArgsConstructor
public class MessageRepository {

    private final DynamoDbClient client;

    public List<Message> findAll() {
        return client.scanPaginator(buildScanRequest())
                .items()
                .stream()
                .map(Message::from)
                .collect(toList());
    }

    private ScanRequest buildScanRequest() {
        return ScanRequest
                .builder()
                .tableName(Message.TABLE_NAME)
                .attributesToGet(Message.COLUMN_ID, Message.COLUMN_CONTENT)
                .build();
    }
    public void put(Message message) {
        client.putItem(putRequest(message));
    }

    private PutItemRequest putRequest(Message message) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(Message.COLUMN_ID, AttributeValue.builder().s(message.getId()).build());
        item.put(Message.COLUMN_CONTENT, AttributeValue.builder().s(message.getContent()).build());
        return PutItemRequest.builder()
                .tableName(Message.TABLE_NAME)
                .item(item)
                .build();
    }

}
