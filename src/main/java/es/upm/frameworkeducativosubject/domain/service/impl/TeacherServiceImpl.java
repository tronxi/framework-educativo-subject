package es.upm.frameworkeducativosubject.domain.service.impl;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService;
import es.upm.frameworkeducativosubject.domain.port.secundary.ITeacherRepository;
import es.upm.frameworkeducativosubject.domain.port.secundary.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public HttpStatus setTeacher(String idSubject, String ident, String header) {
        try {
            User user = userRepository.getUserByIdent(ident, header);
            if (user.getRoles().contains("TEACHER")) {
                teacherRepository.setTeacher(idSubject, user.getId_user());
            } else {
                return  HttpStatus.BAD_REQUEST;
            }
            return HttpStatus.OK;
        } catch (Exception e) {
            return  HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public HttpStatus deleteTeacher(String idSubject, String ident) {
        try {
            teacherRepository.deleteTeacher(idSubject, ident);
            return HttpStatus.OK;
        } catch (Exception e) {
            return  HttpStatus.BAD_REQUEST;
        }
    }
}
