package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository;
import es.upm.frameworkeducativosubject.infrastructure.event.publisher.DeleteGroupPublisher;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.GroupMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserGroupMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryAdapter implements GroupRepository {

    private final GroupMapper groupMapper;
    private final UserGroupMapper userGroupMapper;
    private final DeleteGroupPublisher deleteGroupPublisher;

    @Override
    public List<Group> getGroupBySubjectId(String subject_id) {
        return groupMapper.getGroupBySubjectId(subject_id).stream()
                .map(this::groupDAOToGroup)
                .collect(Collectors.toList());
    }

    @Override
    public List<Group> getGroupByStudentIdAndSubjectId(String studentId, String subjectId) {
        return groupMapper.getGroupByStudentIdAndSubjectId(studentId, subjectId).stream()
                .map(this::groupDAOToGroup)
                .collect(Collectors.toList());
    }

    @Override
    public Group deleteGroupById(String group_id) {
        deleteGroupEvent(group_id);
        groupMapper.deleteGroupById(group_id);
        return Group.builder().id_subject(group_id).build();
    }

    @Override
    public void deleteGroupsBySubjectId(String subject_id) throws Exception {
        try {
            List<GroupEntity> groupEntities = groupMapper.getGroupBySubjectId(subject_id);
            groupEntities.forEach(this::deleteGroupEvent);
            groupMapper.deleteGroupBySubjectId(subject_id);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public Group saveGroup(String name, String subject_id) {
        groupMapper.loadGroup(name, subject_id);
        return Group.builder()
                .name(name)
                .id_subject(subject_id)
                .build();
    }

    @Override
    public List<String> getIdUser(String group_id) {
        return userGroupMapper.getIdUser(group_id);
    }

    private Group groupDAOToGroup(GroupEntity groupEntity) {
        return Group.builder()
                .id_group(groupEntity.getId_group())
                .id_subject(groupEntity.getId_subject())
                .name(groupEntity.getName())
                .build();
    }

    private void deleteGroupEvent(String groupId) {
        GroupEntity groupEntity = groupMapper.getGroupByGroupId(groupId);
        deleteGroupPublisher.deleteGroupEvent(groupEntity);
    }

    private void deleteGroupEvent(GroupEntity groupEntity) {
        deleteGroupPublisher.deleteGroupEvent(groupEntity);
    }
}
