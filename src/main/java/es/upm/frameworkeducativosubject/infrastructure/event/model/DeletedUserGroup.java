package es.upm.frameworkeducativosubject.infrastructure.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeletedUserGroup {
    String studentId;
    String groupId;
}
