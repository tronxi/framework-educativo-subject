package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.infrastructure.api.rest.adapter.SubjectAdapter;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subject")
@PreAuthorize("authenticated")
public class SubjectController {

    @Autowired
    private SubjectAdapter subjectAdapter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity loadSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectAdapter.subjectLoadAdapter(subjectDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> getSubject(@RequestParam String id) {
        return subjectAdapter.getSubjectByIdAdapter(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ResponseEntity updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectAdapter.updateSubjectAdapter(subjectDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity deleteSubject(@RequestParam String id) {
        return subjectAdapter.deleteSubjectById(id);
    }
}