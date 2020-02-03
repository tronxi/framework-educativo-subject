package es.upm.frameworkeducativosubject.domain.useCase;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService;
import es.upm.frameworkeducativosubject.domain.port.secundary.TeacherRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceUseCase implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final UserRepository userRepository;

    @Override
    public void setTeacher(String idSubject, String ident, String header) throws Exception {
        try {
            User user = userRepository.getUserByIdent(ident, header);
            if (user.getRoles().contains("TEACHER")) {
                teacherRepository.setTeacher(idSubject, user.getId_user());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void deleteTeacher(String idSubject, String ident) throws Exception{
        try {
            teacherRepository.deleteTeacher(idSubject, ident);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public List<User> getTeacher(String idSubject, String ident, String header) throws Exception {
        try {
            return teacherRepository.getTeachers(idSubject, ident);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
