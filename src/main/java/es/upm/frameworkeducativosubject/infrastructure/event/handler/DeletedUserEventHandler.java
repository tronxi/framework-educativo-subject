package es.upm.frameworkeducativosubject.infrastructure.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteUser;
import es.upm.frameworkeducativosubject.infrastructure.event.model.DeletedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "user.deleted.subject")
public class DeletedUserEventHandler {

    private final ObjectMapper objectMapper;
    private final DeleteUser deleteUser;

    @RabbitHandler
    public void deletedUser(String message) {
        try {
            DeletedUser user = objectMapper.readValue(message, DeletedUser.class);
            deleteUser.deleteUserById(user.getId_user(), user.getRoles());
        } catch (IOException e) {
            throw new RuntimeException("Error parsing object");
        }
    }
}
