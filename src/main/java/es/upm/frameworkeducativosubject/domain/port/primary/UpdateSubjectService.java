package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

public interface UpdateSubjectService {
    void updateSubject(Subject subject) throws Exception;
}