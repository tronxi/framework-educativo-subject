package es.upm.frameworkeducativosubject.domain.port.primary;

import java.util.List;

public interface LoadStudentGroup {
    void loadStudentGroup(String idSubject, String idGroup, List<String> idStudents, String header );
}
