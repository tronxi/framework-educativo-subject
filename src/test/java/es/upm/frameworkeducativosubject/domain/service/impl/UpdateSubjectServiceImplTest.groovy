package es.upm.frameworkeducativosubject.domain.service.impl

import es.upm.frameworkeducativosubject.domain.model.Group
import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.port.primary.UpdateSubjectService
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.GroupRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepository
import spock.lang.Shared
import spock.lang.Specification

class UpdateSubjectServiceImplTest extends Specification {
    @Shared
    ISubjectRepository subjectRepository

    @Shared
    IGroupRepository groupRepository

    @Shared
    UpdateSubjectService updateSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepository)
        groupRepository = Stub(GroupRepository)

        updateSubjectService = new UpdateSubjectServiceImpl(subjectRepository, groupRepository)
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
