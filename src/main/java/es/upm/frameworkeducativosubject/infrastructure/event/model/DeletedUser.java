package es.upm.frameworkeducativosubject.infrastructure.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeletedUser {
    private String id_user;
    private List<String > roles;
}
