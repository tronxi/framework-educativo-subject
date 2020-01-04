package es.upm.frameworkeducativosubject.domain.service.impl


import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubjectService
import es.upm.frameworkeducativosubject.domain.port.secundary.IGroupRepository
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.GroupRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepository
import spock.lang.Shared
import spock.lang.Specification

class DeleteSubjectServiceImplTest extends Specification {
    @Shared ISubjectRepository subjectRepository
    @Shared IGroupRepository groupRepository

    @Shared DeleteSubjectService deleteSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepository)
        groupRepository = Mock(GroupRepository)

        deleteSubjectService = new DeleteSubjectServiceImpl(subjectRepository,
                                                            groupRepository)
    }

    def "delete subject" () {
        given:
            String id = "2"
        when:
            deleteSubjectService.deleteSubject(id)
        then:
            noExceptionThrown()
    }

    def "delete subject with exception" () {
        given:
        String id = "2"
        when:
        deleteSubjectService.deleteSubject(id)
        then:
        subjectRepository.deleteSubjectById(id) >> {throw new Exception()}
        thrown(Exception)
    }
}
