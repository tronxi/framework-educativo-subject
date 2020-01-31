package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

public interface SubjectRepository {
    Subject getSubjectById(String id);
    Subject getSubjectByNameYear(String name, String year);
    void saveSubject(Subject subject) throws Exception;
    void updateSubject(Subject subject) throws Exception;
    void deleteSubjectById(String id);
}
