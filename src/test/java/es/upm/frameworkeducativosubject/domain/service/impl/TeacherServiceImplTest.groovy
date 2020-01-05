package es.upm.frameworkeducativosubject.domain.service.impl

import es.upm.frameworkeducativosubject.domain.model.User
import es.upm.frameworkeducativosubject.domain.port.primary.TeacherService
import es.upm.frameworkeducativosubject.domain.port.secundary.ITeacherRepository
import es.upm.frameworkeducativosubject.domain.port.secundary.IUserRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.TeacherRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.UserRepository
import spock.lang.Shared
import spock.lang.Specification

class TeacherServiceImplTest extends Specification {
    @Shared
    IUserRepository userRepository

    @Shared
    ITeacherRepository teacherRepository

    @Shared
    TeacherService teacherService

    def setup() {
        userRepository = Mock(UserRepository)
        teacherRepository = Mock(TeacherRepository)

        teacherService = new TeacherServiceImpl(teacherRepository, userRepository)
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
