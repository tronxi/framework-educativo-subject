package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private String id_subject;
    private String id_group;
    private String name;

//    @JsonCreator
//    public GroupDTO(@JsonProperty("id_subject") String id_subject,
//                    @JsonProperty("id_group") String id_group,
//                    @JsonProperty("name") String name) {
//        this.id_subject = id_subject;
//        this.id_group = id_group;
//        this.name = name;
//    }
}
