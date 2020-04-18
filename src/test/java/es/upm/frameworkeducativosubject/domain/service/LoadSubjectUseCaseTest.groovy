package es.upm.frameworkeducativosubject.domain.service

import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubject
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepositoryAdapter
import spock.lang.Shared
import spock.lang.Specification

class LoadSubjectUseCaseTest extends Specification {
    @Shared
    es.upm.frameworkeducativosubject.domain.port.secundary.SubjectRepository subjectRepository

    @Shared
    LoadSubject loadSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepositoryAdapter)

        loadSubjectService = new LoadSubjectUseCase(subjectRepository)
    }

    def "load subject"() {
        given:
        Subject subject = Subject.builder()
                .id_subject("2")
                .build()
        when:
        loadSubjectService.loadSubject(subject)
        then:
        noExceptionThrown()
    }

    def "load subject with exception"() {
        given:
        Subject subject = Subject.builder()
                .id_subject("2")
                .build()
        subjectRepository.saveSubject(subject) >> {throw new Exception()}
        when:
        loadSubjectService.loadSubject(subject)
        then:
        thrown(Exception)
    }
}
