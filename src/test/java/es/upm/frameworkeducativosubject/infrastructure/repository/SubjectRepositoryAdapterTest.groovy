package es.upm.frameworkeducativosubject.infrastructure.repository

import es.upm.frameworkeducativosubject.domain.model.Subject
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.model.SubjectEntity
import org.apache.ibatis.exceptions.PersistenceException
import spock.lang.Shared
import spock.lang.Specification

class SubjectRepositoryAdapterTest extends Specification {

    @Shared
    SubjectMapper subjectMapper

    @Shared
    SubjectRepositoryAdapter subjectRepository

    def setup() {
        subjectMapper = Mock(SubjectMapper)
        subjectRepository = new SubjectRepositoryAdapter(subjectMapper)
    }

    def "get subject by id" () {
        given:
        String id = "1"
        Subject subject = Subject.builder()
                            .id_subject(id)
                            .build()
        SubjectEntity subjectDAO = SubjectEntity.builder()
                                .idSubject(id)
                                .build()
        subjectMapper.getSubjectById(id) >> subjectDAO
        when:
        Subject res = subjectRepository.getSubjectById(id)
        then:
        res == subject
    }

    def "get subject by name and year" () {
        given:
        String name = "name"
        String year = "2019"
        Subject subject = Subject.builder()
                .name(name)
                .year(year)
                .build()
        SubjectEntity subjectDAO = SubjectEntity.builder()
                .name(name)
                .year(year)
                .build()
        subjectMapper.getSubjectByNameYear(name, year) >> subjectDAO
        when:
        Subject res = subjectRepository.getSubjectByNameYear(name, year)
        then:
        res == subject
    }

    def "save subject" () {
        given:
        Subject subject = Subject.builder()
                .name("poo")
                .year("2019")
                .build()
        when:
        subjectRepository.saveSubject(subject)
        then:
        noExceptionThrown()
    }

    def "save subject with exception" () {
        given:
        Subject subject = Subject.builder()
                .name("poo")
                .year("2019")
                .build()
        subjectMapper.saveSubject(_ as String, _ as String) >> {throw new PersistenceException()}
        when:
        subjectRepository.saveSubject(subject)
        then:
        thrown(Exception)
    }

    def "update subject" () {
        given:
        Subject subject = Subject.builder()
                .name("poo")
                .year("2019")
                .build()
        when:
        subjectRepository.updateSubject(subject)
        then:
        noExceptionThrown()
    }

    def "update subject with exception" () {
        given:
        Subject subject = Subject.builder()
                .id_subject("2")
                .name("poo")
                .year("2019")
                .build()
        subjectMapper.updateSubject(_ as String,  _ as String, _ as String) >> {throw new PersistenceException()}
        when:
        subjectRepository.updateSubject(subject)
        then:
        thrown(Exception)
    }

    def "delete subject by id" () {
        given:
        String id = "1"
        when:
        subjectRepository.deleteSubjectById(id)
        then:
        noExceptionThrown()
    }
}
