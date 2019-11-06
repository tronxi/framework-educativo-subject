package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubjectDTO {
    private String idSubject;
    private String name;
    private String year;

    @JsonCreator
    public SubjectDTO(@JsonProperty("idSubject")String idSubject,
                      @JsonProperty("name") String name,
                      @JsonProperty("year") String year) {
        this.idSubject = idSubject;
        this.name = name;
        this.year = year;
    }
}
