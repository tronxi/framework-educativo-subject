package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.User;

import java.util.List;

public interface TeacherRepository {

    void setTeacher(String idSubject, String idTeacher) throws Exception;
    void deleteTeacher(String idSubject, String idTeacher) throws Exception;
    List<User> getTeachers(String idSubject, String header) throws Exception;
}
