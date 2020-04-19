package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.port.secundary.StudentRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepository  {

    private final UserGroupMapper userGroupMapper;

    @Override
    public void saveStudent(String idGroup, String idStudent) {
        userGroupMapper.saveStudent(idGroup, idStudent);
    }
}
