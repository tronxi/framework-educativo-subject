package es.upm.frameworkeducativosubject.domain.service

import es.upm.frameworkeducativosubject.domain.model.User
import es.upm.frameworkeducativosubject.domain.port.primary.GetStudent
import es.upm.frameworkeducativosubject.domain.port.secundary.GroupRepository
import es.upm.frameworkeducativosubject.domain.port.secundary.UserRepository
import spock.lang.Shared
import spock.lang.Specification

class GetStudentUseCaseTest extends Specification {

    @Shared
    UserRepository userRepository

    @Shared
    GroupRepository groupRepository

    @Shared
    GetStudent getStudent

    def setup() {
        userRepository = Mock(UserRepository)
        groupRepository = Mock(GroupRepository)
        getStudent = new GetStudentUseCase(userRepository, groupRepository)
    }

    def "should get student by subject id and group id"() {
        given:
        String idSubject = "idSubject"
        String idGroup = "idGroup"
        String header = "header"
        String idUser = "1"
        List<String> idUserList = Arrays.asList(idUser)
        List<User> expected = Arrays.asList(User.builder().id_user(idUser).build())
        groupRepository.getIdUser(idGroup) >> idUserList
        userRepository.getUserListByIdUser(idUserList, header) >> expected
        when:
        List<User> userList = getStudent.getStudent(idSubject, idGroup, header)
        then:
        expected == userList
    }
}
