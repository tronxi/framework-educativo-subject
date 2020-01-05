package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.SubjectMapperInfrastructure;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "subject-service/subject")
@PreAuthorize("authenticated")
public class SubjectController {

    private SubjectMapperInfrastructure subjectMapperInfrastructure;

    @Autowired
    public SubjectController(SubjectMapperInfrastructure subjectMapperInfrastructure) {
        this.subjectMapperInfrastructure = subjectMapperInfrastructure;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity loadSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectMapperInfrastructure.subjectLoadAdapter(subjectDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
    public ResponseEntity<SubjectDTO> getSubject(@RequestParam String id) {
        return subjectMapperInfrastructure.getSubjectByIdAdapter(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"name", "year"})
    public ResponseEntity<SubjectDTO> getSubject(@RequestParam String name, @RequestParam String year) {
        return subjectMapperInfrastructure.getSubjectByNameYear(name, year);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{idSubject}/teacher/{ident}")


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectMapperInfrastructure.updateSubjectAdapter(subjectDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{idSubject}/teacher/{ident}")
    public ResponseEntity setTeacher(@PathVariable String idSubject, @PathVariable String ident,
                                     @RequestHeader("authorization") String header) {
        return subjectMapperInfrastructure.setTeacher(idSubject, ident, header);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity deleteSubject(@RequestParam String id) {
        return subjectMapperInfrastructure.deleteSubjectById(id);
    }
}