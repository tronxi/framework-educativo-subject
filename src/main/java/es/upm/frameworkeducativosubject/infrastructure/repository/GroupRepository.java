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

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> getGroupBySubjectId(String subject_id) {
        return groupMapper.getGroupBySubjectId(subject_id).stream()
                .map(this::groupDAOToGroup)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGroupById(String group_id) throws Exception {
        try {
            groupMapper.deleteGroupById(group_id);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    @Override
    public void deleteGroupsBySubjectId(String subject_id) throws Exception {
        try {
            groupMapper.deleteGroupBySubjectId(subject_id);
        } catch (PersistenceException e) {
            throw new Exception("ex");
        }
    }

    private Group groupDAOToGroup(GroupDAO groupDAO) {
        return Group.builder()
                .id_group(groupDAO.getId_group())
                .id_subject(groupDAO.getId_group())
                .name(groupDAO.getName())
                .build();
    }
}
