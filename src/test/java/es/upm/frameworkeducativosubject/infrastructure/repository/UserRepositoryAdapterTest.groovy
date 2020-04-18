package es.upm.frameworkeducativosubject.infrastructure.repository

import es.upm.frameworkeducativosubject.domain.model.User
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.DeleteUserMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.mappers.UserMapper
import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserEntity
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class UserRepositoryAdapterTest extends Specification {
    @Shared
    UserMapper userMapper

    @Shared
    DeleteUserMapper deleteUserMapper

    @Shared
    UserRepositoryAdapter userRepository

    def setup() {
        userMapper = Mock(UserMapper)
        deleteUserMapper = Mock(DeleteUserMapper)
        userRepository = new UserRepositoryAdapter(userMapper, deleteUserMapper)
    }

    def "get user by ident" () {
        given:
        String ident = "ident"
        String header = "header"
        UserEntity userDAO = UserEntity.builder()
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
