package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class GroupDTO {
    private String id_subject;
    private String id_group;
    private String name;
}
