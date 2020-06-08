package es.upm.frameworkeducativosubject.infrastructure.event.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativosubject.infrastructure.event.mapper.DeletedUserGroupMapper;
import es.upm.frameworkeducativosubject.infrastructure.event.model.DeletedUserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserGroupPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final DeletedUserGroupMapper deletedUserGroupMapper;

    public void deleteUserGroupGroupEvent(String userId, String groupId) {
        try {
            DeletedUserGroup deletedUserGroup = deletedUserGroupMapper.toEvent(userId, groupId);
            String deletedUserGroupString = objectMapper.writeValueAsString(deletedUserGroup);
            rabbitTemplate.convertAndSend("userGroup.deleted", "", deletedUserGroupString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
