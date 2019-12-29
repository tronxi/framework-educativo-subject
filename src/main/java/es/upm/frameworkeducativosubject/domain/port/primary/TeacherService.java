package es.upm.frameworkeducativosubject.domain.port.primary;

public interface TeacherService {
    void setTeacher(String idSubject, String ident, String header) throws Exception;
    void deleteTeacher(String idSubject, String ident) throws Exception;
}
