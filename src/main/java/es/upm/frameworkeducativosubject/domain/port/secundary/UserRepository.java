package es.upm.frameworkeducativosubject.domain.port.secundary;

import es.upm.frameworkeducativosubject.domain.model.User;

public interface UserRepository {
    User getUserByIdent(String ident, String header);
    User getUserByIdUser(String idUser, String header);
}
