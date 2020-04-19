package es.upm.frameworkeducativosubject.domain.port.primary;

import es.upm.frameworkeducativosubject.domain.model.User;

import java.util.List;

public interface GetStudent {
    List<User> getStudent(String idSubject, String idGroup, String header);
}
