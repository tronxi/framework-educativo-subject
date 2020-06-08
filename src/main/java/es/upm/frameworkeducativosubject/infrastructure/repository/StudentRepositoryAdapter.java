package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.port.secundary.StudentRepository;
import es.upm.frameworkeducativosubject.infrastructure.event.publisher.DeleteUserGroupPublisher;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepository  {

    private final UserGroupMapper userGroupMapper;
    private final DeleteUserGroupPublisher deleteUserGroupPublisher;

    @Override
    public void saveStudent(String idGroup, String idStudent) {
        deleteUserGroupPublisher.deleteUserGroupGroupEvent(idStudent, idGroup);
        userGroupMapper.saveStudent(idGroup, idStudent);
    }

    @Override
    public void deleteStudent(String idGroup, String idStudent) {
        userGroupMapper.deleteStudent(idGroup, idStudent);
    }
}
