package es.upm.frameworkeducativosubject.infrastructure.repository;

import es.upm.frameworkeducativosubject.domain.model.Group;
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository;
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.GroupMapper;
import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GroupRepository implements IGroupRepository {

    private GroupMapper groupMapper;

    @Autowired
    public GroupRepository(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<Group> getGroupBySubjectId(String subject_id) {
        return groupMapper.getGroupBySubjectId(subject_id).stream()
                .map(this::groupDAOToGroup)
                .collect(Collectors.toList());
    }

    @Override
    public Group deleteGroupById(String group_id) {
        groupMapper.deleteGroupById(group_id);
        return Group.builder().id_subject(group_id).build();
    }

    @Override
    public void deleteGroupsBySubjectId(String subject_id) throws Exception {
        try {
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

    private Group groupDAOToGroup(GroupDAO groupDAO) {
        return Group.builder()
                .id_group(groupDAO.getId_group())
                .id_subject(groupDAO.getId_subject())
                .name(groupDAO.getName())
                .build();
    }
}
