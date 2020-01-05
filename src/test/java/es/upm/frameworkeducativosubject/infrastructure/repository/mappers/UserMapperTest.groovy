package es.upm.frameworkeducativosubject.infrastructure.repository.mappers

import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserDAO
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
        ResponseEntity<UserDAO> responseEntity = ResponseEntity.ok(UserDAO.builder().build())
        when:
        ResponseEntity<UserDAO> res = userMapper.getUserByIdent(_ as String, _ as String)
        then:
        res == responseEntity
    }
}
