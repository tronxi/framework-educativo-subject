package es.upm.frameworkeducativosubject.domain.port.primary;

import java.util.List;

public interface DeleteUser {

    void deleteUserByIdent(String ident, List<String> roles);

}
