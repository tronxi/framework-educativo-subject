package es.upm.frameworkeducativosubject.domain.port.secundary;

public interface TeacherRepository {

    void setTeacher(String idSubject, String idTeacher) throws Exception;
    void deleteTeacher(String idSubject, String idTeacher) throws Exception;
}
