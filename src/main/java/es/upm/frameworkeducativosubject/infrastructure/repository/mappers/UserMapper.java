package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "user", fallback = UserMapper.UserMapperFallback.class)
public interface UserMapper {

    @GetMapping("/user-service/user")
    ResponseEntity<UserEntity> getUserByIdent(@RequestParam String ident,
                                              @RequestHeader("authorization") String header);

    @GetMapping("/user-service/user/teacher")
    ResponseEntity<List<UserEntity>> getUserListById(@PathVariable List<String> idUser,
                                                 @RequestHeader("authorization") String header);

    @Component
    class UserMapperFallback implements UserMapper {

        @Override
        public ResponseEntity<UserEntity> getUserByIdent(String ident, String header) {
            return ResponseEntity.ok(UserEntity.builder().build());
        }

        @Override
        public ResponseEntity<List<UserEntity>> getUserListById(List<String> idUser, String header) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

}
