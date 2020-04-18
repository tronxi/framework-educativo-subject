package es.upm.frameworkeducativosubject.domain.service


import es.upm.frameworkeducativosubject.domain.port.primary.DeleteSubject
import es.upm.frameworkeducativosubject.infrastructure.repository.GroupRepositoryAdapter
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepositoryAdapter
import spock.lang.Shared
import spock.lang.Specification

class DeleteSubjectUseCaseTest extends Specification {
    @Shared es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository subjectRepository
    @Shared es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository groupRepository

    @Shared DeleteSubject deleteSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepositoryAdapter)
        groupRepository = Mock(GroupRepositoryAdapter)

        deleteSubjectService = new DeleteSubjectUseCase(subjectRepository,
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
