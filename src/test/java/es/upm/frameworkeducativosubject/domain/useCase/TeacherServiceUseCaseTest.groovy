package es.upm.frameworkeducativosubject.domain.useCase

import es.upm.frameworkeducativosubject.domain.model.User
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService
import es.upm.frameworkeducativosubject.infrastructure.repository.TeacherRepositoryAdapter
import es.upm.frameworkeducativosubject.infrastructure.repository.UserRepositoryAdapter
import spock.lang.Shared
import spock.lang.Specification

class TeacherServiceUseCaseTest extends Specification {
    @Shared
    es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository userRepository

    @Shared
    es.upm.frameworkeducativosubject.domain.port.secundary.TeacherRepository teacherRepository

    @Shared
    TeacherService teacherService

    def setup() {
        userRepository = Mock(UserRepositoryAdapter)
        teacherRepository = Mock(TeacherRepositoryAdapter)

        teacherService = new TeacherServiceUseCase(teacherRepository, userRepository)
    }

    def "set teacher with role teacher" () {
        given:
        String idSubject = "1"
        String ident = "ident"
        String header = "header"
        List<String> roles = new ArrayList<>();
        roles.add("TEACHER")
        User user = User.builder()
                    .ident(ident)
                    .roles(roles)
                    .build()
        userRepository.getUserByIdent(ident, header) >> user
        when:
        teacherService.setTeacher(idSubject, ident, header)
        then:
        noExceptionThrown()
    }

    def "set teacher without role teacher" () {
        given:
        String idSubject = "1"
        String ident = "ident"
        String header = "header"
        List<String> roles = new ArrayList<>();
        User user = User.builder()
                .ident(ident)
                .roles(roles)
                .build()
        userRepository.getUserByIdent(ident, header) >> user
        when:
        teacherService.setTeacher(idSubject, ident, header)
        then:
        thrown(Exception)
    }

    def "set teacher with role teacher with exception" () {
        given:
        String idSubject = "1"
        String ident = "ident"
        String header = "header"
        userRepository.getUserByIdent(ident, header) >> {throw new Exception()}
        when:
        teacherService.setTeacher(idSubject, ident, header)
        then:
        thrown(Exception)
    }

    def "delete teacher" () {
        given:
        String idSubject = "1"
        String ident = "ident"
        when:
        teacherService.deleteTeacher(idSubject, ident)
        then:
        noExceptionThrown()
    }

    def "delete teacher with exception" () {
        given:
        String idSubject = "1"
        String ident = "ident"
        teacherRepository.deleteTeacher(idSubject, ident) >> {throw new Exception()}
        when:
        teacherService.deleteTeacher(idSubject, ident)
        then:
        thrown(Exception)
    }
}
