package es.upm.frameworkeducativosubject.infrastructure.repository

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.GroupMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserGroupMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.model.GroupEntity
import org.apache.ibatis.exceptions.PersistenceException
import spock.lang.Shared
import spock.lang.Specification

class GroupRepositoryAdapterTest extends Specification {
    @Shared
    GroupMapper groupMapper

    @Shared
    UserGroupMapper userGroupMapper


    @Shared
    GroupRepositoryAdapter groupRepository

    def setup() {
        groupMapper = Mock(GroupMapper)
        userGroupMapper = Mock(UserGroupMapper)
        groupRepository = new GroupRepositoryAdapter(groupMapper, userGroupMapper)
    }

    def "get group by subject id" () {
        given:
        String subject_id  = "1"

        GroupEntity groupDAO = GroupEntity.builder().id_subject(subject_id).build()
        List<GroupEntity> groupDAOS = new ArrayList<>()
        groupDAOS.add(groupDAO)

        Group group = Group.builder().id_subject(subject_id).build()
        List<Group> groups = new ArrayList<>()
        groups.add(group)

        groupMapper.getGroupBySubjectId(subject_id) >> groupDAOS
        when:
        List<Group> res = groupRepository.getGroupBySubjectId(subject_id)
        then:
        groups == res
    }

    def "delete group by id" () {
        given:
        String group_id = "1"
        Group group = Group.builder()
                        .id_subject(group_id)
                        .build()
        when:
        Group res = groupRepository.deleteGroupById(group_id)
        then:
        res == group
    }

    def "delete group by subject id" () {
        given:
        String subject_id = "1"
        when:
        groupRepository.deleteGroupsBySubjectId(subject_id)
        then:
        noExceptionThrown()
    }

    def "delete group by subject id with exception" () {
        given:
        String subject_id = "1"
        groupMapper.deleteGroupBySubjectId(subject_id) >> {throw new PersistenceException()}
        when:
        groupRepository.deleteGroupsBySubjectId(subject_id)
        then:
        thrown(Exception)
    }

    def "save group" () {
        given:
        String name = "name"
        String subject_id = "1"
        Group group = Group.builder()
                        .name(name)
                        .id_subject(subject_id)
                        .build()
        when:
        Group res = groupRepository.saveGroup(name, subject_id)
        then:
        group == res
    }
}
