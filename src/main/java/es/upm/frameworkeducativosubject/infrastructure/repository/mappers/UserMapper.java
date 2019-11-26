package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", fallback = UserMapper.UserMapperFallback.class)
public interface UserMapper {

    @GetMapping("/user-service/user")
    ResponseEntity<UserDAO> getUserByIdent(@RequestParam String ident,
                                           @RequestHeader("authorization") String header);

    @Component
    class UserMapperFallback implements UserMapper {

        @Override
        public ResponseEntity<UserDAO> getUserByIdent(String ident, String header) {
            return ResponseEntity.ok(UserDAO.builder().build());
        }
    }

}
