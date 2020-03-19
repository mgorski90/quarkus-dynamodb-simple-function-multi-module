package pl.mgorski;

import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Singleton
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public List<MessageDTO> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::toDTO)
                .collect(toList());
    }

    public MessageDTO send(MessageDTO messageDTO) {
        Message message = messageMapper.fromDTO(messageDTO);
        String uniqueID = UUID.randomUUID().toString();
        Message persistedMessage = Message.builder()
                .id(uniqueID)
                .build();
        messageRepository.put(persistedMessage);
        return messageMapper.toDTO(persistedMessage);
    }

}
