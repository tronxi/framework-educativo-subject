package es.upm.frameworkeducativosubject.domain.port.secundary;

public interface StudentRepository {
    void saveStudent(String idGroup, String idStudent);
}
