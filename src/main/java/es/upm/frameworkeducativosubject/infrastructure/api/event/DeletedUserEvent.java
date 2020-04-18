package es.upm.frameworkeducativosubject.infrastructure.api.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteUser;
import es.upm.frameworkeducativosubject.infrastructure.api.event.model.DeletedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "user.deleted.subject")
public class DeletedUserEvent {

    private final ObjectMapper objectMapper;
    private final DeleteUser deleteUser;

    @RabbitHandler
    public void deletedUser(String message) {
        try {
            DeletedUser user = objectMapper.readValue(message, DeletedUser.class);
            deleteUser.deleteUserByIdent(user.getId_user(), user.getRoles());
        } catch (IOException e) {
            throw new RuntimeException("Error parsing object");
        }
    }
}
