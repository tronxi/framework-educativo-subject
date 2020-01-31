package es.upm.frameworkeducativosubject.infrastructure.repository.mappers

import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserEntity
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class UserMapperTest extends Specification {
    @Shared
    UserMapper userMapper

    def setup() {
        userMapper = new UserMapper.UserMapperFallback()
    }

    def "user mapper fallback" () {
        given:
        ResponseEntity<UserEntity> responseEntity = ResponseEntity.ok(UserEntity.builder().build())
        when:
        ResponseEntity<UserEntity> res = userMapper.getUserByIdent(_ as String, _ as String)
        then:
        res == responseEntity
    }
}
