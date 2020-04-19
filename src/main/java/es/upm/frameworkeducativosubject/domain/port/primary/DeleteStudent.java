package es.upm.frameworkeducativosubject.domain.port.primary;

public interface DeleteStudent {
    void deleteStudent(String idSubject, String idGroup, String idStudent);
}
