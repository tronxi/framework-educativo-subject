package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "subject-service/subject")
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectMapperInfrastructure subjectMapperInfrastructure;
    private final UserMapperInfrastructure userMapperInfrastructure;
    private final LoadSubject loadSubject;
    private final FindSubject findSubject;
    private final UpdateSubject updateSubject;
    private final DeleteSubject deleteSubject;
    private final TeacherService teacherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity loadSubject(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = subjectMapperInfrastructure.subjectDTOToSubject(subjectDTO);
        try {
            loadSubject.loadSubject(subject);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
    public ResponseEntity<SubjectDTO> getSubject(@RequestParam String id) {
        Subject subject = findSubject.findSubjectById(id);
        SubjectDTO subjectDTO = subjectMapperInfrastructure.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"name", "year"})
    public ResponseEntity<SubjectDTO> getSubject(@RequestParam String name, @RequestParam String year) {
        Subject subject = findSubject.findSubjectByNameYear(name, year);
        SubjectDTO subjectDTO = subjectMapperInfrastructure.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = subjectMapperInfrastructure.subjectDTOToSubject(subjectDTO);
        try {
            updateSubject.updateSubject(subject);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{idSubject}/teacher/{ident}")
    public ResponseEntity setTeacher(@PathVariable String idSubject, @PathVariable String ident,
                                     @RequestHeader("authorization") String header) {
        try {
            teacherService.setTeacher(idSubject, ident, header);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{idSubject}/teacher/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String idSubject, @PathVariable String id) {
        try {
            teacherService.deleteTeacher(idSubject, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{idSubject}/teacher")
    public ResponseEntity<UserDTO> getTeacher(@PathVariable String idSubject,
                                              @RequestHeader("authorization") String header) {
        try {
            List<UserDTO> list = teacherService.getTeacher(idSubject, header).stream()
                    .map(userMapperInfrastructure::userToUserDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity deleteSubject(@RequestParam String id) {
        try {
            deleteSubject.deleteSubject(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}