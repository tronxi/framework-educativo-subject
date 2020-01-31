package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

public interface UpdateSubject {
    void updateSubject(Subject subject) throws Exception;
}