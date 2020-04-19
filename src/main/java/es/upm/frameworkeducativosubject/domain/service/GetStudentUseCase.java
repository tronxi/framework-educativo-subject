package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.GetStudent;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetStudentUseCase implements GetStudent {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<User> getStudent(String idSubject, String idGroup, String header) {
        List<String> idUserList = groupRepository.getIdUser(idGroup);
        return userRepository.getUserListByIdUser(idUserList, header);
    }
}
