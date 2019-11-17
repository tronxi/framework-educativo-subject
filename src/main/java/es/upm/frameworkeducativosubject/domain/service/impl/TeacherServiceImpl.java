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
    public HttpStatus setTeacher(String idSubject, String ident) {
        try {
            //teacherRepository.setTeacher(idSubject, ident);
            System.out.println("paso");
            User user = userRepository.getUserByIdent(ident);
            System.out.println("USUARIO " + user);
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
