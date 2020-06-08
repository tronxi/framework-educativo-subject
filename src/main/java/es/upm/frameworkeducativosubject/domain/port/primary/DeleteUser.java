package es.upm.frameworkeducativosubject.domain.port.primary;

import java.util.List;

public interface DeleteUser {

    void deleteUserById(String id, List<String> roles);

}
