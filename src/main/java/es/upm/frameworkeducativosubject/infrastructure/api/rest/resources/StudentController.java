package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.domain.port.primary.LoadStudentGroup;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "subject-service/subject")
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class StudentController {

    private final LoadStudentGroup loadStudentGroup;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{idSubject}/group/{idGroup}")
    public ResponseEntity loadStudent(@PathVariable String idSubject, @PathVariable String idGroup,
                                      @RequestBody List<String> idStudents,
                                      @RequestHeader("authorization") String header) {
        try {
            loadStudentGroup.loadStudentGroup(idSubject, idGroup, idStudents, header);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
