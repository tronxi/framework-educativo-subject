package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteStudent;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeleteStudentUseCase implements DeleteStudent {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Override
    public void deleteStudent(String idSubject, String idGroup, String idStudent) {
        try {
            List<String> groups = groupRepository.getGroupBySubjectId(idSubject)
                    .stream()
                    .map(Group::getId_group)
                    .collect(Collectors.toList());
            if (groups.contains(idGroup)) {
                studentRepository.deleteStudent(idGroup, idStudent);
            } else {
                throw new Exception("invalid group");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
