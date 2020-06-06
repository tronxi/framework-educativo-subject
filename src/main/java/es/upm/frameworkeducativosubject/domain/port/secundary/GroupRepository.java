package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.Group;

import java.util.List;

public interface GroupRepository {
    List<Group> getGroupBySubjectId(String subject_id);
    List<Group> getGroupByStudentIdAndSubjectId(String studentId, String subjectId);
    Group deleteGroupById(String group_id);
    void deleteGroupsBySubjectId(String subject_id) throws Exception;
    Group saveGroup(String name, String subject_id);
    List<String> getIdUser(String group_id);

}
