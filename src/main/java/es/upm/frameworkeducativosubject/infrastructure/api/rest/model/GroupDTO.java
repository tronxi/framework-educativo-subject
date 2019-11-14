package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GroupDTO {
    private String id_subject;
    private String id_group;
    private String name;

    @JsonCreator
    public GroupDTO(@JsonProperty("id_subject") String id_subject,
                    @JsonProperty("id_group") String id_group,
                    @JsonProperty("name") String name) {
        this.id_subject = id_subject;
        this.id_group = id_group;
        this.name = name;
    }
}
