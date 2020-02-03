package es.upm.frameworkeducativosubject.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SubjectUserEntity {
    private String idSubject;
    private String idTeacher;
}
