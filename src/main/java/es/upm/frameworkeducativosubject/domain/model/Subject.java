package es.upm.frameworkeducativosubject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Subject {
    private String id_subject;
    private String name;
    private String year;
    private List<Group> groups;
}