package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private String idSubject;
    private String name;
    private String year;
    private List<GroupDTO> groups;

}
