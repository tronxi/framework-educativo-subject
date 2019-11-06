package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

public interface ISubjectRepository {
    Subject getSubjectById(String id);
    void saveSubject(Subject subject) throws Exception;
    void updateSubject(Subject subject) throws Exception;
    void deleteSubjectById(String id);
}
