package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subject")
public class test {

    @Value("${server.port:entorno por defecto}")
    private String port;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String inicio() {
        return "Puerto " + port;
    }
}
