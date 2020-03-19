package pl.mgorski;

import javax.inject.Singleton;

@Singleton
public class MessageMapper {

    public Message fromDTO(MessageDTO message) {
        // TODO: use MapStruct in real application or more sophisticated build logic
        return Message
                .builder()
                .id(message.getId())
                .content(message.getContent())
                .build();
    }

    public MessageDTO toDTO(Message message) {
        // TODO: use MapStruct in real application or more sophisticated build logic
        return MessageDTO.builder()
                .id(message.getId())
                .content(message.getContent())
                .build();
    }

}
