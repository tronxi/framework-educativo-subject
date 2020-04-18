package es.upm.frameworkeducativosubject.domain.service

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubject
import es.upm.frameworkeducativosubject.infrastructure.repository.GroupRepositoryAdapter
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepositoryAdapter
import spock.lang.Shared
import spock.lang.Specification

class UpdateSubjectUseCaseTest extends Specification {
    @Shared
    es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository subjectRepository

    @Shared
    es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository groupRepository

    @Shared
    UpdateSubject updateSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepositoryAdapter)
        groupRepository = Stub(GroupRepositoryAdapter)

        updateSubjectService = new UpdateSubjectUseCase(subjectRepository, groupRepository)
    }

    def "update subject" () {
        given:
        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder().name("grupo").build())
        Subject subject = Subject.builder()
                            .name("poo")
                            .groups(groups)
                            .build()
        groupRepository.getGroupBySubjectId(_ as String) >> groups
        when:
        updateSubjectService.updateSubject(subject)
        then:
        noExceptionThrown()
    }

    def "update subject with exception" () {
        given:
        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder().name("grupo").build())
        Subject subject = Subject.builder()
                .name("poo")
                .groups(groups)
                .build()
        subjectRepository.updateSubject(subject) >> {throw new Exception()}
        when:
        updateSubjectService.updateSubject(subject)
        then:
        thrown(Exception)
    }
}
