package es.upm.frameworkeducativosubject.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class GroupEntity {
    private String id_group;
    private String id_subject;
    private String name;
}
