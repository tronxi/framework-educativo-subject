package es.upm.frameworkeducativosubject.infrastructure.event.mapper;

import es.upm.frameworkeducativosubject.infrastructure.event.model.DeletedUserGroup;
import org.springframework.stereotype.Component;

@Component
public class DeletedUserGroupMapper {

    public DeletedUserGroup toEvent(String studentId, String groupId) {
        return DeletedUserGroup.builder()
                .groupId(groupId)
                .studentId(studentId)
                .build();
    }
}
