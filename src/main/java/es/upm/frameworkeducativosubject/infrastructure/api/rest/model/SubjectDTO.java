package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.upm.frameworkeducativosubject.domain.model.Group;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class SubjectDTO {
    private String idSubject;
    private String name;
    private String year;
    private List<Group> groups;

    @JsonCreator
    public SubjectDTO(@JsonProperty("idSubject")String idSubject,
                      @JsonProperty("name") String name,
                      @JsonProperty("year") String year,
                      @JsonProperty("groups") List<Group> groups) {
        this.idSubject = idSubject;
        this.name = name;
        this.year = year;
        this.groups = groups;
    }
}
