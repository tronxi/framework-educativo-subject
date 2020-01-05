package es.upm.frameworkeducativosubject.domain.service.impl

import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.domain.port.primary.LoadSubjectService
import es.upm.frameworkeducativosubject.domain.port.secundary.ISubjectRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.SubjectRepository
import spock.lang.Shared
import spock.lang.Specification

class LoadSubjectServiceImplTest extends Specification {
    @Shared
    ISubjectRepository subjectRepository

    @Shared
    LoadSubjectService loadSubjectService

    def setup() {
        subjectRepository = Mock(SubjectRepository)

        loadSubjectService = new LoadSubjectServiceImpl(subjectRepository)
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
