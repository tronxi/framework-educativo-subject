package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.domain.port.primary.DeleteStudent;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubject;
import es.upm.frameworkeducativosubject.domain.port.primary.GetStudent;
import es.upm.frameworkeducativosubject.domain.port.primary.LoadStudentGroup;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.SubjectMapperInfrastructure;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.UserMapperInfrastructure;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "subject-service/subject")
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class StudentController {

    private final LoadStudentGroup loadStudentGroup;
    private final DeleteStudent deleteStudent;
    private final GetStudent getStudent;
    private final FindSubject findSubject;
    private final UserMapperInfrastructure userMapperInfrastructure;
    private final SubjectMapperInfrastructure subjectMapperInfrastructure;

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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{idSubject}/group/{idGroup}/student/{idStudent}")
    public ResponseEntity deleteStudent(@PathVariable String idSubject, @PathVariable String idGroup,
                                        @PathVariable String idStudent) {
        try {
            deleteStudent.deleteStudent(idSubject, idGroup, idStudent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{idSubject}/group/{idGroup}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getStudent(@PathVariable String idSubject,
                                                    @PathVariable String idGroup,
                                                    @RequestHeader("authorization") String header) {
        try {
            List<User> users = getStudent.getStudent(idSubject, idGroup, header);
            List<UserDTO> usersDTO = userMapperInfrastructure.userListTOUserDTOList(users);
            return new ResponseEntity<>(usersDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN')")
    @GetMapping(value = "/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDTO>> getSubjectByStudent(@PathVariable String studentId) {
        List<Subject> subjects = findSubject.findSubjectByStudentId(studentId);
        List<SubjectDTO> subjectDTOS = subjects.stream()
                .map(subjectMapperInfrastructure::subjectToSubjectDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(subjectDTOS);
    }

}
