package es.upm.frameworkeducativosubject.domain.service

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.infrastructure.repository.GroupRepositoryAdapter
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepositoryAdapter
import spock.lang.Shared
import spock.lang.Specification

class FindSubjectUseCaseTest extends Specification {
    @Shared es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository subjectRepository
    @Shared es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository groupRepository

    @Shared FindSubjectUseCase findSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepositoryAdapter)
        groupRepository = Mock(GroupRepositoryAdapter)

        findSubjectService = new FindSubjectUseCase(subjectRepository,
                groupRepository)
    }

    def "find subject by id" () {
        given:
        String id = "1"
        Group group = Group.builder().id_subject(id).build()
        List<Group> groups = new ArrayList<>()
        groups.add(group)
        Subject subject = Subject.builder()
                .id_subject(id)
                .build()
        subjectRepository.getSubjectById(_ as String) >> Subject.builder()
                .id_subject(id)
                .build()
        groupRepository.getGroupBySubjectId(_ as String) >> groups
        when:
        Subject res =  findSubjectService.findSubjectById(id)
        then:
        subject.setGroups(groups)
        res == subject
    }

    def "find subject by name and year" () {
        given:
        String id = "1"
        String name = "poo"
        String year = "2019"
        Group group = Group.builder().id_subject(id).build()
        List<Group> groups = new ArrayList<>()
        groups.add(group)
        Subject subject = Subject.builder()
                .id_subject(id)
                .name(name)
                .year(year)
                .build()
        subjectRepository.getSubjectByNameYear(_ as String, _ as String) >> Subject.builder()
                .id_subject(id)
                .year(year)
                .name(name)
                .build()
        groupRepository.getGroupBySubjectId(_ as String) >> groups
        when:
        Subject res =  findSubjectService.findSubjectByNameYear(name, year)
        then:
        subject.setGroups(groups)
        res == subject

    }
}
