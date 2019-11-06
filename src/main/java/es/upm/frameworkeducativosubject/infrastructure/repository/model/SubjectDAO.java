package es.upm.frameworkeducativosubject.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class SubjectDAO {
    private String idSubject;
    private String name;
    private String year;
}
