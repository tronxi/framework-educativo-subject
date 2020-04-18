package es.upm.frameworkeducativosubject.domain.service;

import es.upm.frameworkeducativosubject.domain.port.primary.DeleteUser;
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUser {

    private final UserRepository userRepository;

    @Override
    public void deleteUserByIdent(String ident, List<String> roles) {
        if (roles.contains("STUDENT")) {
            //TODO delete in user_group
        }
        if (roles.contains("TEACHER")) {
            userRepository.deleteTeacher(ident);
        }
    }
}
