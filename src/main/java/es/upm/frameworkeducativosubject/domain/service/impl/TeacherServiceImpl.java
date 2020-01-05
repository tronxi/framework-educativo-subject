package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ITeacherRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private ITeacherRepository teacherRepository;

    private IUserRepository userRepository;

    @Autowired
    public TeacherServiceImpl(ITeacherRepository teacherRepository,
                              IUserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;

    }

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
}
