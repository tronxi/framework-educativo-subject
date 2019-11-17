package es.upm.frameworkeducativosubject.infrastructure.repository.mappers;

import es.upm.frameworkeducativosubject.infrastructure.repository.model.UserDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER")
public interface UserMapper {

    @GetMapping("/user")
    UserDAO getUserByIdent(@RequestParam String ident);
}
