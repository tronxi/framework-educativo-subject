package es.upm.frameworkeducativosubject.infrastructure.repository

import es.upm.frameworkeducativosubject.domain.model.User
import es.upm.frameworkeducativosubject.domain.port.secundary.IUserRepository
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserDAO
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class UserRepositoryTest extends Specification {
    @Shared
    UserMapper userMapper

    @Shared
    IUserRepository userRepository

    def setup() {
        userMapper = Mock(UserMapper)
        userRepository = new UserRepository(userMapper)
    }

    def "get user by ident" () {
        given:
        String ident = "ident"
        String header = "header"
        UserDAO userDAO = UserDAO.builder()
                            .ident(ident)
                            .build()
        User user = User.builder().ident(ident).build()
        userMapper.getUserByIdent(ident, header) >> ResponseEntity.ok(userDAO)
        when:
        User res = userRepository.getUserByIdent(ident, header)
        then:
        res == user

    }
}
