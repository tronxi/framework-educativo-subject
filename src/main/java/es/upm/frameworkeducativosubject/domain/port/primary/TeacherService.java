package es.upm.frameworkeducativosubject.domain.port.primary;

import org.springframework.http.HttpStatus;

public interface TeacherService {
    HttpStatus setTeacher(String idSubject, String ident);
    HttpStatus deleteTeacher(String idSubject, String ident);
}
