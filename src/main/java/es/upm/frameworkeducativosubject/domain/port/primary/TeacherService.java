package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.User;

import java.util.List;

public interface TeacherService {
    void setTeacher(String idSubject, String ident, String header) throws Exception;
    void deleteTeacher(String idSubject, String ident) throws Exception;
    List<User> getTeacher(String idSubject, String header) throws Exception;
}
