package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.Group;

import java.util.List;

public interface IGroupRepository {
    List<Group> getGroupBySubjectId(String subject_id);
    Group deleteGroupById(String group_id);
    void deleteGroupsBySubjectId(String subject_id) throws Exception;
    Group saveGroup(String name, String subject_id);

}
