package es.upm.frameworkeducativosubject.domain.service

import es.upm.frameworkeducativosubject.domain.port.primary.DeleteUser
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository
import spock.lang.Shared
import spock.lang.Specification

class DeleteUserUseCaseTest extends Specification {

    @Shared
    UserRepository userRepository

    @Shared
    DeleteUser deleteUser

    def setup() {
        userRepository = Mock(UserRepository)
        deleteUser = new DeleteUserUseCase(userRepository)
    }

    def "should delete only student"() {
        given:
        String id = "id"
        List<String> roles = Arrays.asList("STUDENT")
        when:
        deleteUser.deleteUserById(id, roles)
        then:
        0 * userRepository.deleteTeacher(id)
        1 * userRepository.deleteStudent(id)
    }

    def "should delete only teacher"() {
        given:
        String id = "id"
        List<String> roles = Arrays.asList("TEACHER")
        when:
        deleteUser.deleteUserById(id, roles)
        then:
        1 * userRepository.deleteTeacher(id)
        0 * userRepository.deleteStudent(id)
    }

    def "should delete student and teacher"() {
        given:
        String id = "id"
        List<String> roles = Arrays.asList("TEACHER", "STUDENT")
        when:
        deleteUser.deleteUserById(id, roles)
        then:
        1 * userRepository.deleteTeacher(id)
        1 * userRepository.deleteStudent(id)
    }
}
