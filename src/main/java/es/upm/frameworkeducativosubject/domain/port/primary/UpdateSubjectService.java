package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.Subject;
import org.springframework.http.HttpStatus;

public interface UpdateSubjectService {
    HttpStatus updateSubject(Subject subject);
}