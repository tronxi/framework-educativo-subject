package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

public interface FindSubject {
    Subject findSubjectById(String id);
    Subject findSubjectByNameYear(String name, String year);
}