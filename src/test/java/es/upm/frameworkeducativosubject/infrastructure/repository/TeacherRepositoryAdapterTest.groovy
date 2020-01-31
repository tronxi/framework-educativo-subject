package es.upm.frameworkeducativosubject.infrastructure.repository


import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.SubjectUserMapper
import org.apache.ibatis.exceptions.PersistenceException
import spock.lang.Shared
import spock.lang.Specification

class TeacherRepositoryAdapterTest extends Specification {
    @Shared
    SubjectUserMapper subjectUserMapper

    @Shared
    TeacherRepositoryAdapter teacherRepository

    def setup() {
        subjectUserMapper = Mock(SubjectUserMapper)
        teacherRepository = new TeacherRepositoryAdapter(subjectUserMapper)
    }

    def "set teacher" () {
        given:
        String idSubject = "1"
        String idTeacher = "2"
        when:
        teacherRepository.setTeacher(idSubject, idTeacher)
        then:
        noExceptionThrown()
    }

    def "set teacher with exception" () {
        given:
        String idSubject = "1"
        String idTeacher = "2"
        subjectUserMapper.setTeacher(idSubject, idTeacher) >> {throw new PersistenceException()}
        when:
        teacherRepository.setTeacher(idSubject, idTeacher)
        then:
        thrown(Exception)
    }

    def "delete teacher" () {
        given:
        String idSubject = "1"
        String idTeacher = "2"
        when:
        teacherRepository.deleteTeacher(idSubject, idTeacher)
        then:
        noExceptionThrown()
    }

    def "delete teacher with exception" () {
        given:
        String idSubject = "1"
        String idTeacher = "2"
        subjectUserMapper.deleteTeacher(idSubject, idTeacher) >> {throw new PersistenceException()}
        when:
        teacherRepository.deleteTeacher(idSubject, idTeacher)
        then:
        thrown(Exception)
    }
}
