package es.upm.frameworkeducativosubject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Group {
    private String id_subject;
    private String id_group;
    private String name;
}
