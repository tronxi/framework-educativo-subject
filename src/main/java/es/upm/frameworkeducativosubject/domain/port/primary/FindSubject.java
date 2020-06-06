package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

import java.util.List;

public interface FindSubject {
    Subject findSubjectById(String id);
    Subject findSubjectByNameYear(String name, String year);
    List<Subject> findSubjectByStudentId (String studentId);
}