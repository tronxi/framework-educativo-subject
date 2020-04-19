package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadStudentGroup;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.StudentRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoadStudentGroupUseCase implements LoadStudentGroup {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public void loadStudentGroup(String idSubject, String idGroup, List<String> idStudents, String header) {
        try {
            List<User> students = userRepository.getUserListByIdUser(idStudents, header)
                    .stream()
                    .filter(user -> user.getRoles().contains("STUDENT"))
                    .collect(Collectors.toList());
            List<String> groups = groupRepository.getGroupBySubjectId(idSubject)
                    .stream()
                    .map(Group::getId_group)
                    .collect(Collectors.toList());
            if (groups.contains(idGroup)) {
                students.forEach(st -> studentRepository.saveStudent(idGroup, st.getId_user()));
            } else {
                throw new Exception("invalid group");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
