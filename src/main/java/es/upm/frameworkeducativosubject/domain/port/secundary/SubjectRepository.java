package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.Subject;

import java.util.List;

public interface SubjectRepository {
    Subject getSubjectById(String id);
    Subject getSubjectByNameYear(String name, String year);
    List<Subject> getSubjectByStudentId(String studentId);
    List<Subject> getSubjectByTeacherId(String teacherId);
    void saveSubject(Subject subject) throws Exception;
    void updateSubject(Subject subject) throws Exception;
    void deleteSubjectById(String id);

}
