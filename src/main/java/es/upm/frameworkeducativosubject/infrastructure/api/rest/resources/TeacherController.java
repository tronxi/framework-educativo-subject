package es.upm.frameworkeducativosubject.infrastructure.api.rest.resources;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import es.upm.frameworkeducativosubject.domain.port.primary.FindSubject;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper.SubjectMapperInfrastructure;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "subject-service/subject")
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class TeacherController {

    private final FindSubject findSubject;
    private final SubjectMapperInfrastructure subjectMapperInfrastructure;


    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    @GetMapping(value = "/teacher/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDTO>> getSubjectByStudent(@PathVariable String teacherId) {
        List<Subject> subjects = findSubject.findSubjectByTeacherId(teacherId);
        List<SubjectDTO> subjectDTOS = subjects.stream()
                .map(subjectMapperInfrastructure::subjectToSubjectDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(subjectDTOS);
    }

}
