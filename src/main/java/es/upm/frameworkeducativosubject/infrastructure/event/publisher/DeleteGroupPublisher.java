package es.upm.frameworkeducativosubject.infrastructure.event.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteGroupPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void deleteGroupEvent(GroupEntity group) {
        try {
            String deletedGroupString = objectMapper.writeValueAsString(group);
            rabbitTemplate.convertAndSend("group.deleted", "", deletedGroupString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
